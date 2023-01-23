package domain.controllers;

import domain.models.entities.producto.Categoria;
import domain.repositories.CategoriaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.util.List;

@RestController
@RequestMapping("/categorias")
public class CategoriaController {
    @Autowired
    private CategoriaRepository categoriaRepository;
    @CrossOrigin(origins = "https://capgemini-tp-licuadora.web.app")
    @GetMapping({"", "/"})
    public ResponseEntity<?> obtenerCategorias(){
      try{
          List<Categoria> categorias = categoriaRepository.findAll();
          return ResponseEntity.ok(categorias);
      } catch (Exception e) {
          return new ResponseEntity<>("Error al obtener categorias", HttpStatus.NOT_FOUND);
      }
    }

    @GetMapping("/{id}")
    public ResponseEntity<?> categoria(@PathVariable(name = "id") Integer id){
      try{
            Categoria categoria = categoriaRepository.findById(id).get();
            return ResponseEntity.ok(categoria);
        } catch (Exception e) {
          return new ResponseEntity<>("Error al obtener categoria", HttpStatus.NOT_FOUND);
        }

    }

    @PostMapping({"", "/"})
    public ResponseEntity<String> agregarCategoria(@RequestBody Categoria categoria){
        try {
            categoria.setFechaCreacion(LocalDateTime.now());
            categoriaRepository.save(categoria);
            return ResponseEntity.ok("Categoria creada con exito");
        } catch (Exception e) {
            return ResponseEntity.badRequest().body("No se pudo agregar la categoria");
        }
    }

}
