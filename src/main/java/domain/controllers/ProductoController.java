package domain.controllers;

import domain.models.entities.producto.Producto;
import domain.repositories.ProductoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;

@RestController
@RequestMapping("/productos")
public class ProductoController {

    @Autowired
    private ProductoRepository productoRepository;

    @GetMapping({"", "/"})
    public Object productos(){
        return productoRepository.findAll();
    }

    @GetMapping("/{id}")
    public Producto producto(@PathVariable(name = "id") Integer id){
        return productoRepository.findById(id).get() == null ? null : productoRepository.findById(id).get();
    }

    @PostMapping({"", "/"})
    public Producto agregarProducto(@RequestBody Producto producto){
        producto.setFechaCreacion(LocalDateTime.now());
        productoRepository.save(producto);
        return producto;
    }

    @DeleteMapping("/{id}")
    public void eliminarProducto(@PathVariable(name = "id") Integer id){
        productoRepository.deleteById(id);
    }

    @PutMapping("/{id}")
    public Producto modificarProducto(@PathVariable(name = "id") Integer id, @RequestBody Producto producto){
        producto.setId(id);
        productoRepository.save(producto);
        return producto;
    }

}
