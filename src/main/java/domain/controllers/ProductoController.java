package domain.controllers;

import domain.models.DTO.PosiblePersonalizacionDTO;
import domain.models.DTO.ProductoDTO;
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
    @GetMapping({"/", ""})
    public List<Producto> traerTodos() {
        return entityManager.createQuery(
                        "SELECT p FROM Producto p WHERE p.fechaBaja IS NULL", Producto.class)
                .getResultList();
    }

    //Traigo un producto en particular
    @GetMapping("/{id}")
    public ResponseEntity<Producto> traerPorId(@PathVariable Integer id) {
        try {
            Producto producto = productoRepository.findById(id).get();
            return new ResponseEntity<>(producto, HttpStatus.valueOf(producto == null ? 404 : 200));
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    //Borro un producto, colocando la fecha de baja
    @Transactional
    @DeleteMapping("/{id}")
    public ResponseEntity<String> eliminar(@PathVariable Integer id) {
        try {
            Producto producto = productoRepository.findById(id).get();
            producto.setFechaBaja(LocalDateTime.now());
            return ResponseEntity.ok().body("Producto eliminado");
        } catch (Exception e) {
            return ResponseEntity.notFound().build();
        }
    }

    //Creo un producto a partir de un DTO producto
    @PostMapping({"/", ""})
    public ResponseEntity<String> crearProducto(@RequestBody @Valid ProductoDTO producto, BindingResult bindingResult) {
        if (!bindingResult.hasErrors()) {
            try {
                Categoria categoria = categoriaRepository.findById(producto.getCategoriaId()).get();
                Gestor gestor = gestorRepository.findById(producto.getGestorId()).get();
                Producto productoNuevo = new Producto(producto.getNombre(), producto.getColor(), producto.getPrecio(),
                        producto.getTiempoDeFabricacion(), categoria, gestor, LocalDateTime.now());
                productoRepository.save(productoNuevo);
                return ResponseEntity.created(null).body("Producto creado");
            } catch (Exception e) {
                return ResponseEntity.badRequest().body("No se a encontrado la categoria o el gestor en la base " +
                        "de datos");
            }
        } else {
            return ResponseEntity.badRequest().body("No se a enviado un producto valido");
        }
    }

//     //Modifico un producto a partir de un DTO producto
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
    @Transactional
    @PatchMapping("/{id}/posiblePersonalizacion/{idPosiblePersonalizacion}")
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
                    return ResponseEntity.badRequest().body("No se a encontrado el tipo en la base " +
                            "de datos");
                }

                //traer el area
                AreaDePersonalizacion area;
                if (areaRepository.existsById(posiblePersonalizacionDTO.getAreaId())) {
                    area = areaRepository.findById(posiblePersonalizacionDTO.getAreaId()).get();
                } else {
                    return ResponseEntity.badRequest().body("No se a encontrado el area en la base " +
                            "de datos");
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
    @Transactional
    @PostMapping("/{id}/posiblePersonalizacion")
    public ResponseEntity<String> agregarPosiblePersonalizacion(@PathVariable Integer id, @RequestBody
    PosiblePersonalizacionDTO posiblePersonalizacionDTO) {
        try {
            Producto productoEncontrado = productoRepository.findById(id).get();

            //traer el tipo
            TipoDePersonalizacion tipo;
            if (tipoDePersonalizacionRepository.existsById(posiblePersonalizacionDTO.getTipoId())) {
                tipo = tipoDePersonalizacionRepository.findById(posiblePersonalizacionDTO.getTipoId()).get();
            } else {
                return ResponseEntity.badRequest().body("No se a encontrado el tipo en la base " +
                        "de datos");
            }

            //traer el area
            AreaDePersonalizacion area;
            if (areaRepository.existsById(posiblePersonalizacionDTO.getAreaId())) {
                area = areaRepository.findById(posiblePersonalizacionDTO.getAreaId()).get();
            } else {
                return ResponseEntity.badRequest().body("No se a encontrado el area en la base " +
                        "de datos");
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
