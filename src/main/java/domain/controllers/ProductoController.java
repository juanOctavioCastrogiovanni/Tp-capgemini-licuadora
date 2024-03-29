package domain.controllers;

import domain.models.DTO.PosiblePersonalizacionDTO;
import domain.models.DTO.ProductoDTO;
import domain.models.DTO.projection.DTOProducto;
import domain.models.entities.producto.*;
import domain.models.entities.venta.Gestor;
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
@RequestMapping("/productos")
public class ProductoController {

    @Autowired
    private ProductoRepository productoRepository;

    @Autowired
    private CategoriaRepository categoriaRepository;

    @Autowired
    private GestorRepository gestorRepository;

    @Autowired
    private TipoDePersonalizacionRepository tipoDePersonalizacionRepository;

    @Autowired
    private AreaRepository areaRepository;

    @PersistenceContext
    private EntityManager entityManager;

    @Autowired
    private PersonalizacionPosibleRepository personalizacionPosibleRepository;


    //Traigo todos los productos que no esten borrados
    //Get all products that are not deleted
    @GetMapping({"/", ""})
    public ResponseEntity<?> traerTodos() {

        TypedQuery<DTOProducto> dtoProductoIds = entityManager.createQuery(
                        "SELECT new domain.models.DTO.projection.DTOProducto(p) FROM Producto p WHERE p.fechaBaja IS NULL", DTOProducto.class);

        List<DTOProducto> listaProductos = dtoProductoIds.getResultList();

        if (listaProductos.isEmpty()) {
            return new ResponseEntity<>("No se encontro la lista de Productos",HttpStatus.NO_CONTENT);
        }

        return ResponseEntity.ok(listaProductos);
    }

    //Traigo un producto en particular
    //Get a specific product
    @GetMapping("/{id}")
    public ResponseEntity<?> traerPorId(@PathVariable Integer id) {
        TypedQuery<DTOProducto> dtoProductoIds = entityManager.createQuery(
                "SELECT new domain.models.DTO.projection.DTOProducto(p) FROM Producto p WHERE p.fechaBaja IS NULL AND p.id = " + id, DTOProducto.class);

        if (dtoProductoIds.getResultList().isEmpty()) {
            return new ResponseEntity<>("No encontrado el producto con el id " + id, HttpStatus.NOT_FOUND);
        }
        DTOProducto productoEncontrado = dtoProductoIds.getResultList().get(0);

        return ResponseEntity.ok(productoEncontrado);
    }

    //Borro un producto, colocando la fecha de baja, tambien a sus posibles personalizaciones
    //Delete a product, setting the deletion date, also to its possible personalizations
    @Transactional
    @DeleteMapping("/{id}")
    public ResponseEntity<String> eliminar(@PathVariable Integer id) {

            Producto producto = productoRepository.findById(id).get();
            if (producto == null) {
                return new ResponseEntity<>("No se encontro el producto", HttpStatus.NOT_FOUND);
            }

            producto.setFechaBaja(LocalDateTime.now());
            for (PosiblePersonalizacion p : producto.obtenerPosiblesPersonalizaciones()) {
                p.setFechaBaja(LocalDateTime.now());
            }
            return ResponseEntity.ok().body("Producto eliminado");

    }

    //Borro una personalizacion de un producto
    //Delete a product's personalization
    @Transactional
    @DeleteMapping("/{id}/posible-personalizacion/{idPersonalizacion}")
    public ResponseEntity<String> eliminarPersonalizacion(@PathVariable Integer id, @PathVariable Integer idPersonalizacion) {
        try {
            Producto producto = productoRepository.findById(id).get();
            PosiblePersonalizacion posiblePersonalizacion = personalizacionPosibleRepository.findById(idPersonalizacion).get();
            if(producto.obtenerPosiblesPersonalizaciones().contains(posiblePersonalizacion)){
                posiblePersonalizacion.setFechaBaja(LocalDateTime.now());
            } else {
                return new ResponseEntity<>("La posible personalizacion no pertenece al producto", HttpStatus.BAD_REQUEST);
            }
            return ResponseEntity.ok().body("Personalizacion eliminada");
        } catch (Exception e) {
            return ResponseEntity.badRequest().body("Error al eliminar el producto");
        }
    }

    //Creo un producto a partir de un DTO producto
    //Create a product from a DTO product
    @PostMapping({"/", ""})
    public ResponseEntity<String> crearProducto(@RequestBody @Valid ProductoDTO producto, BindingResult bindingResult) {
        if (!bindingResult.hasErrors()) {
            try {
                if (!categoriaRepository.existsById(producto.getCategoriaId())){
                    return new ResponseEntity<>("La categoria no existe", HttpStatus.NOT_FOUND);
                }
                if (!gestorRepository.existsById(producto.getGestorId())){
                    return new ResponseEntity<>("El gestor no existe", HttpStatus.NOT_FOUND);
                }
                Categoria categoria = categoriaRepository.findById(producto.getCategoriaId()).get();
                Gestor gestor = gestorRepository.findById(producto.getGestorId()).get();
                Producto productoNuevo = new Producto(producto.getNombre(), producto.getColor(), producto.getPrecio(),
                        producto.getTiempoDeFabricacion(), categoria, gestor, LocalDateTime.now());
                productoRepository.save(productoNuevo);
                return ResponseEntity.created(null).body("Producto creado");
            } catch (Exception e) {
                return ResponseEntity.badRequest().body("Error al crear el producto");
            }
        } else {
            return ResponseEntity.badRequest().body("No se a enviado un producto valido");
        }
    }

    //Modifico un producto a partir de un DTO producto
    //Modify a product from a DTO product
    @Transactional
    @PatchMapping("/{id}")
    public ResponseEntity<String> modificarProducto(@PathVariable(name = "id") Integer id,
                                                    @RequestBody @Valid ProductoDTO producto, BindingResult bindingResult) {
        if (!bindingResult.hasErrors()) {
            try {
                Producto productoAModificar = productoRepository.findById(id).get();
                if (producto.getNombre() == null || producto.getColor() == null || producto.getPrecio() == null ||
                        producto.getTiempoDeFabricacion() == null) {
                    return ResponseEntity.badRequest().body("No se puede modificar el producto");
                }
                productoAModificar.setFechaModificacion(LocalDateTime.now());
                productoAModificar.setNombre(producto.getNombre());
                productoAModificar.setColor(producto.getColor());
                productoAModificar.setPrecioBase(producto.getPrecio());
                productoAModificar.setTiempoDeFabricacion(producto.getTiempoDeFabricacion());
                return ResponseEntity.ok().body("Producto modificado");
            } catch (Exception e) {
                return ResponseEntity.badRequest().body("No se a encontrado el producto en la base de datos");
            }
        } else {
            return ResponseEntity.badRequest().body("No se a enviado un producto valido");
        }

    }

    //Puedo modificar la categoria del producto
    //I can modify the category of the product
    @Transactional
    @PatchMapping("/{id}/categoria")
    public ResponseEntity<String> modificarCategoria(@PathVariable(name = "id") Integer id,
                                                     @RequestBody Categoria categoria) {
        try {
            Producto productoAModificar = productoRepository.findById(id).get();
            Categoria categoriaACambiar = categoriaRepository.findById(categoria.getId()).get();
            productoAModificar.setFechaModificacion(LocalDateTime.now());
            productoAModificar.setCategoria(categoriaACambiar);
            return ResponseEntity.ok().body("Categoria modificada");
        } catch (Exception e) {
            return ResponseEntity.badRequest().body("No se a encontrado la categoria o el producto en la base de datos");
        }
    }

    //Puedo modificar una posible personalizacion del producto ya creada
    //I can modify a possible customization of the product already created
    @Transactional
    @PatchMapping("/{id}/posible-personalizacion/{idPosiblePersonalizacion}")
    public ResponseEntity<String> editarPosiblePersonalizacion(@PathVariable(name="id") Integer id,
                                                               @PathVariable(name="idPosiblePersonalizacion")
                                                               Integer idPosiblePersonalizacion,
                                                                @RequestBody @Valid PosiblePersonalizacionDTO
                                                                           posiblePersonalizacionDTO,
                                                               BindingResult bindingResult) {
        if (!bindingResult.hasErrors()) {
            try {
                PosiblePersonalizacion posiblePersonalizacion = personalizacionPosibleRepository.findById(idPosiblePersonalizacion).get();

                //traer el tipo
                TipoDePersonalizacion tipo;
                if (tipoDePersonalizacionRepository.existsById(posiblePersonalizacionDTO.getTipoId())) {
                    tipo = tipoDePersonalizacionRepository.findById(posiblePersonalizacionDTO.getTipoId()).get();
                } else {
                    return new ResponseEntity<>("El tipo de personalizacion no existe", HttpStatus.NOT_FOUND);
                }

                //traer el area
                AreaDePersonalizacion area;
                if (areaRepository.existsById(posiblePersonalizacionDTO.getAreaId())) {
                    area = areaRepository.findById(posiblePersonalizacionDTO.getAreaId()).get();
                } else {
                    return new ResponseEntity<>("El area de personalizacion no existe", HttpStatus.NOT_FOUND);
                }

                posiblePersonalizacion.setFechaModificacion(LocalDateTime.now());
                posiblePersonalizacion.setArea(area);
                posiblePersonalizacion.setTipo(tipo);

                return ResponseEntity.ok().body("Posible personalizacion modificada");
            } catch (Exception e) {
                return ResponseEntity.badRequest().body("No se a encontrado la posible personalizacion o el producto en la base de datos");
            }
        } else {
            return ResponseEntity.badRequest().body("No se a enviado una posible personalizacion valida");
        }
    }

    //Creo una posible personalizacion para un producto
    //I create a possible customization for a product
    @Transactional
    @PostMapping("/{id}/posible-personalizacion")
    public ResponseEntity<String> agregarPosiblePersonalizacion(@PathVariable(name="id") Integer id, @RequestBody
    PosiblePersonalizacionDTO posiblePersonalizacionDTO) {
        try {
            Producto productoEncontrado = productoRepository.findById(id).get();

            //traer el tipo
            TipoDePersonalizacion tipo;
            if (tipoDePersonalizacionRepository.existsById(posiblePersonalizacionDTO.getTipoId())) {
                tipo = tipoDePersonalizacionRepository.findById(posiblePersonalizacionDTO.getTipoId()).get();
            } else {
                return new ResponseEntity<>("El tipo de personalizacion no existe", HttpStatus.NOT_FOUND);
            }

            //traer el area
            AreaDePersonalizacion area;
            if (areaRepository.existsById(posiblePersonalizacionDTO.getAreaId())) {
                area = areaRepository.findById(posiblePersonalizacionDTO.getAreaId()).get();
            } else {
                return new ResponseEntity<>("El area de personalizacion no existe", HttpStatus.NOT_FOUND);
            }
            PosiblePersonalizacion posiblePersonalizacion = new PosiblePersonalizacion(area, tipo, LocalDateTime.now());
            productoEncontrado.agregarPosiblesPersonalizaciones(posiblePersonalizacion);
            return ResponseEntity.ok().body("Posible personalizacion agregada");
        } catch (Exception e) {
            return ResponseEntity.badRequest().body("No se a encontrado el producto en la base " +
                    "de datos");
        }
    }
}
