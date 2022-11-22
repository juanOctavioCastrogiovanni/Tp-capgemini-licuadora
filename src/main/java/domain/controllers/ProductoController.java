package domain.controllers;

import domain.models.entities.productos.*;
import domain.repositories.AreaRepository;
import domain.repositories.CategoriaRepository;
import domain.repositories.ProductoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import org.springframework.web.bind.annotation.*;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.swing.text.html.parser.Entity;
import javax.transaction.Transactional;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/productos")

public class ProductoController {
    @Autowired
    private ProductoRepository productoRepository;
    
    @PersistenceContext
    private EntityManager entityManager;

    @Autowired
    private AreaRepository areaRepository;

    @GetMapping({"", "/"})
    public Page<Producto> obtenerProductos(Pageable page){
        return productoRepository.findAll(page);
    }

    /*@GetMapping("/{id}")
    public Producto producto(@PathVariable(name = "id") Integer id){
        Optional<Producto> producto = productoRepository.findById(id);
        if(producto.isPresent()){
            return producto.get();
        }
        return null;
    }

     */



    @Transactional
    @PostMapping({"", "/"})
    public String agregarUnNuevoProducto(@RequestBody Producto producto) {
/*        TipoDePersonalizacion tipoNuevo;

        for (int i = 0; i < producto.getAreas().size(); i++) {
            areaNueva = new AreaDePersonalizacion();
            areaNueva.setNombre(producto.getAreas().get(i).getNombre());
            producto.agregarArea(areaNueva);

            for (int j = 0; j < producto.getAreas().get(i).getTipos().size(); j++) {
                tipoNuevo = new TipoDePersonalizacion();
                tipoNuevo.setNombre(producto.getAreas().get(i).getTipos().get(j).getNombre());
                producto.getAreas().get(i).agregarTipo(tipoNuevo);
            }
        }
*/

        entityManager.persist(producto);
        return "Producto agregado";
    }

}
