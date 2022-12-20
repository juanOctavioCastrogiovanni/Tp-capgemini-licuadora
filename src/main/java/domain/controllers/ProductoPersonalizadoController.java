package domain.controllers;

import domain.models.DTO.PersonalizacionDTO;
import domain.models.DTO.ProductoPersonalizadoDTO;
import domain.models.DTO.projection.DTOProductoPersonalizado;
import domain.models.entities.producto.*;
import domain.models.entities.venta.Vendedor;
import domain.repositories.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;
import javax.transaction.Transactional;
import javax.validation.Valid;
import java.time.LocalDateTime;
import java.util.List;

@RestController
@RequestMapping("/productos-personalizados")
public class ProductoPersonalizadoController {

    @Autowired
    private ProductoPersonalizadoRepository prodPers;

    @Autowired
    private ProductoRepository prod;

    @Autowired
    private VendedorRepository vendedorRepository;

    @Autowired
    private PersonalizacionRepository repoPersonalizacion;

    @Autowired
    private PersonalizacionPosibleRepository repoPersonalizacionPosible;

    @PersistenceContext
    private EntityManager entityManager;

    //Traigo todos los productos personalizados que no esten borrados
    @GetMapping({"/", ""})
    public ResponseEntity<?> traerTodos() {
        TypedQuery<DTOProductoPersonalizado> dtoProductoIds = entityManager.createQuery(
                "SELECT new domain.models.DTO.projection.DTOProductoPersonalizado(p) FROM ProductoPersonalizado p WHERE p.fechaBaja IS NULL", DTOProductoPersonalizado.class);

        List<DTOProductoPersonalizado> listaProductos = dtoProductoIds.getResultList();

        if (listaProductos.isEmpty()) {
            return new ResponseEntity<>("No se ha encontrado productos personalizados",HttpStatus.NOT_FOUND);
        }

        return ResponseEntity.ok(listaProductos);
    }

    @GetMapping("/{id}")
    public ResponseEntity<?> traerPorId(@PathVariable Integer id) {
        TypedQuery<DTOProductoPersonalizado> dtoProductoIds = entityManager.createQuery(
                "SELECT new domain.models.DTO.projection.DTOProductoPersonalizado(p) FROM ProductoPersonalizado p WHERE p.fechaBaja IS NULL AND p.id = " + id, DTOProductoPersonalizado.class);

        if (dtoProductoIds.getResultList().isEmpty()) {
            return new ResponseEntity<>("No se ha encontrado el producto personalizado con el id = " + id,HttpStatus.NOT_FOUND);
        }

        DTOProductoPersonalizado producto = dtoProductoIds.getResultList().get(0);
        return ResponseEntity.ok(producto);
    }

    //Borro un producto, colocando la fecha de baja
    @Transactional
    @DeleteMapping("/{id}")
    public ResponseEntity<String> eliminar(@PathVariable Integer id) {
        try {
            ProductoPersonalizado producto = prodPers.findById(id).get();
            producto.setFechaBaja(LocalDateTime.now());
            for (Personalizacion personalizacion : producto.obtenerPersonalizaciones()) {
                personalizacion.setFechaBaja(LocalDateTime.now());
            }
            return ResponseEntity.ok().body("Producto eliminado");
        } catch (Exception e) {
            return new ResponseEntity<>("No se ha encontrado el producto personalizado con el id = " + id,HttpStatus.NO_CONTENT);
        }
    }

    @Transactional
    @DeleteMapping("{id}/personalizar/{idPersonalizacion}")
    public ResponseEntity<String> eliminarPersonalizacion(@PathVariable Integer id, @PathVariable Integer idPersonalizacion) {
        try {
            ProductoPersonalizado producto = prodPers.findById(id).get();
            Personalizacion personalizacion = repoPersonalizacion.findById(idPersonalizacion).get();
            if (producto.obtenerPersonalizaciones().contains(personalizacion)) {
                personalizacion.setFechaBaja(LocalDateTime.now());
                producto.setPrecio(producto.getPrecio() - personalizacion.getPrecioXPersonalizacion());
                return ResponseEntity.ok().body("Personalizacion eliminada");
            } else {
                return new ResponseEntity<>("No se puede borrar una personalizacion que no pertenece a este producto personalizado",HttpStatus.NO_CONTENT);
            }
        } catch (Exception e) {
            return new ResponseEntity<>("No se ha podido eliminar la personalizacion", HttpStatus.NO_CONTENT);
        }
    }

    @PostMapping({"/", ""})
    public ResponseEntity<String> crear(@RequestBody ProductoPersonalizadoDTO productoPersonalizado) {
        try {
            if(!prod.existsById(productoPersonalizado.getProductoId())){
               return ResponseEntity.badRequest().body("El producto que intenta agregar no existe");
            }

            if(!vendedorRepository.existsById(productoPersonalizado.getVendedorId())){
                return ResponseEntity.badRequest().body("El vendedor que intenta agregar no existe");
            }

            Producto producto = prod.findById(productoPersonalizado.getProductoId()).get();
            Vendedor vendedor = vendedorRepository.findById(productoPersonalizado.getVendedorId()).get();

            //Crear el producto personalizado
            ProductoPersonalizado productoPersonalizado1 = new ProductoPersonalizado( producto, vendedor, producto.getPrecioBase(), LocalDateTime.now());
            prodPers.save(productoPersonalizado1);
            return ResponseEntity.ok().body("Producto creado");
        } catch (Exception e) {
            return ResponseEntity.badRequest().body("Error al crear el producto");
        }
    } 

    @Transactional
    @PostMapping("/{id}/personalizar")
    public ResponseEntity<String> personalizar(@PathVariable(name="id") Integer id,
                                               @RequestBody @Valid PersonalizacionDTO personalizacionEntrante,
                                               BindingResult bindingResult) {

        if (!bindingResult.hasErrors()) {
            try {
                //Si existe la personalizacion en la base de datos, verifico que pertenezca al producto
                //que se encuentra en producto personalizado
                if (repoPersonalizacionPosible.existsById(personalizacionEntrante.getPosiblePersonalizacionId())){
                    PosiblePersonalizacion posiblePersonalizacionDB = repoPersonalizacionPosible.findById(personalizacionEntrante.getPosiblePersonalizacionId()).get();
                    ProductoPersonalizado productoPersonalizado = prodPers.findById(id).get();
                    Producto producto = prod.findById(productoPersonalizado.getProducto().getId()).get();

                    /*En este caso verifico que esa personalizacion existente corresponda al producto del producto
                    del producto personalizado*/
                    if(!producto.getPosiblesPersonalizaciones().contains(posiblePersonalizacionDB)){
                        return ResponseEntity.badRequest().body("La personalizacion no pertenece al producto de la posible personalizacion");
                    }

                    //Creo una nueva personalizacion, la agrego al producto personalizado y sumo el precio total
                    Personalizacion nuevaPersonaliacion = new Personalizacion(posiblePersonalizacionDB, personalizacionEntrante.getContenido(), personalizacionEntrante.getPrecioPersonalizacion(), LocalDateTime.now());
                    repoPersonalizacion.save(nuevaPersonaliacion);
                    productoPersonalizado.agregarPersonalizacion(nuevaPersonaliacion);
                    productoPersonalizado.setPrecio(productoPersonalizado.getPrecio() + personalizacionEntrante.getPrecioPersonalizacion());

                    return ResponseEntity.created(null).body("Personalizacion creada");
                } else {
                    return ResponseEntity.badRequest().body("La posible personalizacion directamente no existe");
                }
            } catch (Exception e) {
                return ResponseEntity.badRequest().body("Error al personalizar el producto");
            }
        }
            return ResponseEntity.badRequest().body("Personalizacion , verifique los campos de envio");
    }


    @Transactional
    @PatchMapping("/{id}")
    public ResponseEntity<String> modificar(@PathVariable Integer id,
                                            @RequestBody @Valid ProductoPersonalizadoDTO productoPersonalizadoEntrante,
                                            BindingResult bindingResult) {
        if (!bindingResult.hasErrors()) {
            try {
                if (!prodPers.existsById(id)) {
                    return new ResponseEntity<>("Producto con el id "+ id + " no encontrado", HttpStatus.NOT_FOUND);
                }

                ProductoPersonalizado producto = prodPers.findById(id).get();

                if (!prod.existsById(productoPersonalizadoEntrante.getProductoId())) {
                    return new ResponseEntity<>("El producto que intenta agregar no existe", HttpStatus.NOT_FOUND);
                }

                if (!vendedorRepository.existsById(productoPersonalizadoEntrante.getVendedorId())) {
                    return new ResponseEntity<>("El vendedor que intenta agregar no existe", HttpStatus.NOT_FOUND);
                }
                Vendedor vendedor = vendedorRepository.findById(productoPersonalizadoEntrante.getVendedorId()).get();
                Producto productoAAgregar = prod.findById(productoPersonalizadoEntrante.getProductoId()).get();

                Float precioAnterior = producto.getPrecio();
                producto.setProducto(productoAAgregar);
                producto.setVendedor(vendedor);
                producto.setFechaModificacion(LocalDateTime.now());
                producto.setPrecio(producto.getPrecio() - precioAnterior + productoAAgregar.getPrecioBase());

                producto.getPersonalizaciones().forEach(personalizacion -> {
                    System.out.println(personalizacion.getContenido()+ " " + personalizacion.getPrecioXPersonalizacion());
                });

                return ResponseEntity.ok().body("Producto modificado");
            } catch (Exception e) {
                return ResponseEntity.badRequest().body("Error al modificar el producto");
            }
        } else {
            return ResponseEntity.badRequest().body("Problemas con los campos de producto personalizado, verifique" +
                    "y vuelva a mandarlos");
        }
    }


    @Transactional
    @PatchMapping("/{id}/personalizar/{idPersonalizacion}")
    public ResponseEntity<String> modificarPersonalizacion(@PathVariable(name="id") Integer id,
                                                           @PathVariable(name="idPersonalizacion") Integer idPersonalizacion,
                                                           @RequestBody @Valid PersonalizacionDTO personalizacionEntrante,
                                                           BindingResult bindingResult) {
        if (!bindingResult.hasErrors()){
            if (prodPers.existsById(id)&&repoPersonalizacion.existsById(idPersonalizacion)){
                try {
                    ProductoPersonalizado productoPersonalizado = prodPers.findById(id).get();
                    Personalizacion personalizacion = repoPersonalizacion.findById(idPersonalizacion).get();

                    //Verifico que la personalizacion que se quiere modificar pertenezca al producto personalizado
                    if(!productoPersonalizado.getPersonalizaciones().contains(personalizacion)){
                        return ResponseEntity.badRequest().body("La personalizacion no pertenece al producto personalizado");
                    }
                    //Modifico el precio total del producto personalizado
                    productoPersonalizado.setPrecio(productoPersonalizado.getPrecio() - personalizacion.getPrecioXPersonalizacion() + personalizacionEntrante.getPrecioPersonalizacion());

                    //Modifico la personalizacion
                    personalizacion.setContenido(personalizacionEntrante.getContenido());
                    personalizacion.setPrecioXPersonalizacion(personalizacionEntrante.getPrecioPersonalizacion());
                    personalizacion.setFechaModificacion(LocalDateTime.now());
                    productoPersonalizado.setFechaModificacion(LocalDateTime.now());


                    return ResponseEntity.ok().body("Personalizacion modificada");
                } catch (Exception e) {
                    return ResponseEntity.badRequest().body("Error al modificar la personalizacion");
                }
            } else {
                return ResponseEntity.badRequest().body("El producto personalizado o la personalizacion no existen");
            }
        }

        return ResponseEntity.badRequest().body("Personalizacion , verifique los campos de envio");
    }




}
