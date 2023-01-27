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



    // This method retrieves all the areas from the database and returns them in the response.
    //Este método recupera todas las áreas de la base de datos y las devuelve en la respuesta.
    @GetMapping({"", "/"})
    public ResponseEntity<?> areas(){
        try {
            List<AreaDePersonalizacion> areas = areaRepository.findAll();
            return ResponseEntity.ok(areas);
        } catch (Exception e) {
            return ResponseEntity.badRequest().body("No se encontraron lista de Areas para personalizar");
        }
    }

    // This method retrieves a single area from the database and returns it in the response.
    //Este método recupera una sola área de la base de datos y la devuelve en la respuesta.
    @GetMapping("/{id}")
    public ResponseEntity<?> area(@PathVariable(name = "id") Integer id){
        try {
            AreaDePersonalizacion area = areaRepository.findById(id).get();
            return ResponseEntity.ok(area);
        } catch (Exception e) {
            return ResponseEntity.badRequest().body("No se encontraron lista de Areas para personalizar");
        }
    }

    // This method adds a new area to the database.
    //Este método agrega una nueva área a la base de datos.
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

    // This method updates an existing area in the database.
    //Este método actualiza una área existente en la base de datos.
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

    // This method deletes an existing area from the database.
    //Este método elimina una área existente de la base de datos.
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
