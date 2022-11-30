package domain.controllers;

import domain.models.entities.producto.Producto;
import domain.models.entities.producto.ProductoPersonalizado;
import domain.repositories.ProductoPersonalizadoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/productos-personalizados")
public class ProductoPersonalizadoController {

    @Autowired
    private ProductoPersonalizadoRepository prodPers;

    @GetMapping({"", "/"})
    public Object productosPersonalizados(){
        return prodPers.findAll();
    }

    @GetMapping("/{id}")
    public ProductoPersonalizado productoPersonalizado(@PathVariable(name = "id") Integer id){
        return prodPers.findById(id).get();
    }

    @PostMapping({"", "/"})
    public ProductoPersonalizado agregarProductoPersonalizado(@RequestBody ProductoPersonalizado productoPersonalizado){
        prodPers.save(productoPersonalizado);
        return productoPersonalizado;
    }

    @PutMapping("/{id}")
    public ProductoPersonalizado modificarProductoPersonalizado(@PathVariable(name = "id") Integer id, @RequestBody ProductoPersonalizado producto){
        producto.setId(id);
        prodPers.save(producto);
        return producto;
    }

    @DeleteMapping("/{id}")
    public void eliminarProductoPersonalizado(@PathVariable(name = "id") Integer id){
        prodPers.deleteById(id);
    }

}
