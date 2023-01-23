package domain.controllers;

import domain.models.entities.producto.TipoDePersonalizacion;
import domain.repositories.TipoDePersonalizacionRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/tipos")
public class TipoController {

    @Autowired
    private TipoDePersonalizacionRepository tipoRepository;

    @GetMapping({"", "/"})
    public ResponseEntity<?> tipos(){
        try{
            return ResponseEntity.ok(tipoRepository.findAll());
        } catch (Exception e){
            return ResponseEntity.badRequest().body("No se encontraron listas de tipos");
        }
    }

    @GetMapping("/{id}")
    public ResponseEntity<?> tipo(@PathVariable(name = "id") Integer id){
        try{
            return ResponseEntity.ok(tipoRepository.findById(id));
        } catch (Exception e){
            return ResponseEntity.badRequest().body("No se encontró el tipo");
        }
    }

    @PostMapping({"", "/"})
    public ResponseEntity<String> agregarTipo(@RequestBody TipoDePersonalizacion tipo){
        try{
            tipo.setFechaCreacion(LocalDateTime.now());
            tipoRepository.save(tipo);
            return ResponseEntity.ok("Tipo agregado");
        }
        catch (Exception e){
            return ResponseEntity.badRequest().body("No se pudo agregar el tipo");
        }
    }

    @PutMapping("/{id}")
    public ResponseEntity<?> modificarTipo(@PathVariable(name = "id") Integer id, @RequestBody TipoDePersonalizacion tipo){
        try{
            tipo.setId(id);
            tipoRepository.save(tipo);
            return ResponseEntity.ok("Tipo modificado");
        } catch (Exception e){
            return ResponseEntity.badRequest().body("No se pudo modificar el tipo");
        }
    }

}
