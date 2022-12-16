package domain.controllers;

import domain.models.entities.producto.Categoria;
import domain.repositories.CategoriaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.util.List;

@RestController
@RequestMapping("/categorias")
public class CategoriaController {
    @Autowired
    private CategoriaRepository categoriaRepository;
    @CrossOrigin(origins = "http://localhost:4200")
    @GetMapping({"", "/"})
    public List<Categoria> obtenerCategorias(){
        return categoriaRepository.findAll();
    }

    @GetMapping("/{id}")
    public Categoria categoria(@PathVariable(name = "id") Integer id){
        return categoriaRepository.findById(id).get();
    }

    @PostMapping({"", "/"})
    public Categoria agregarCategoria(@RequestBody Categoria categoria){
        categoria.setFechaCreacion(LocalDateTime.now());
        categoriaRepository.save(categoria);
        return categoria;
    }

}
