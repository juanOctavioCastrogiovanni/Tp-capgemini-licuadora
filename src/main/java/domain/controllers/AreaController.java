package domain.controllers;

import domain.models.entities.producto.AreaDePersonalizacion;
import domain.repositories.AreaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/areas")
public class AreaController {

    @Autowired
    private AreaRepository areaRepository;

    @GetMapping({"", "/"})
    public ResponseEntity<?> areas(){
        try {
            List<AreaDePersonalizacion> areas = areaRepository.findAll();
            return ResponseEntity.ok(areas);
        } catch (Exception e) {
            return ResponseEntity.badRequest().body("No se encontraron lista de Areas para personalizar");
        }
    }

    @GetMapping("/{id}")
    public ResponseEntity<?> area(@PathVariable(name = "id") Integer id){
        try {
            AreaDePersonalizacion area = areaRepository.findById(id).get();
            return ResponseEntity.ok(area);
        } catch (Exception e) {
            return ResponseEntity.badRequest().body("No se encontraron lista de Areas para personalizar");
        }
    }

    @PostMapping({"", "/"})
    public ResponseEntity<String> agregarArea(@RequestBody AreaDePersonalizacion area){
          try {
                area.setFechaCreacion(LocalDateTime.now());
                areaRepository.save(area);
                return ResponseEntity.ok("Area creada con exito");
          } catch (Exception e) {
                 return ResponseEntity.badRequest().body("No se pudo agregar el area");
          }
    }


    @PutMapping("/{id}")
    public ResponseEntity<String> modificarArea(@PathVariable(name = "id") Integer id, @RequestBody AreaDePersonalizacion area){
        try {
            area.setId(id);
            area.setFechaModificacion(LocalDateTime.now());
            areaRepository.save(area);
            return ResponseEntity.ok("Area modificada con exito");
        } catch (Exception e) {
            return ResponseEntity.badRequest().body("No se pudo modificar el area");
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<String> eliminarArea(@PathVariable(name = "id") Integer id){
        try {
            AreaDePersonalizacion areaAEliminar = areaRepository.findById(id).get();
            areaAEliminar.setFechaBaja(LocalDateTime.now());
            areaRepository.save(areaAEliminar);
            return ResponseEntity.ok("Area eliminada con exito");
        } catch (Exception e) {
            return ResponseEntity.badRequest().body("No se pudo eliminar el area");
        }
    }


}
