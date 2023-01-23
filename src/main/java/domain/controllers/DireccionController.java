package domain.controllers;

import domain.models.DTO.DireccionDTO;
import domain.models.entities.venta.Direccion;
import domain.repositories.DireccionRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;

@RestController
@RequestMapping("/direccion")
public class DireccionController {
    @Autowired
    private DireccionRepository repoDireccion;

    @PostMapping({"/", ""})
    public ResponseEntity<?> crearDireccion(@RequestBody DireccionDTO direccionEntrante) {
        try{
            Direccion direccionNueva = new Direccion(direccionEntrante.getCalle(),direccionEntrante.getAltura(), direccionEntrante.getPiso(), direccionEntrante.getDepartamento(), direccionEntrante.getLocalidad(), direccionEntrante.getProvincia(), LocalDateTime.now());
            repoDireccion.save(direccionNueva);
            return ResponseEntity.ok(direccionNueva.getId());
        } catch (Exception e) {
            return ResponseEntity.badRequest().body("Error al crear direccion");
        }
    }
    @GetMapping({"/", ""})
    public ResponseEntity<?> obtenerDirecciones() {
        try{
            return ResponseEntity.ok(repoDireccion.findAll());
        } catch (Exception e) {
            return ResponseEntity.badRequest().body("Error al obtener direcciones");
        }
    }

    @GetMapping("/{id}")
    public ResponseEntity<?> obtenerDireccion(@PathVariable(name = "id") Integer id) {
        try{
            return ResponseEntity.ok(repoDireccion.findById(id));
        } catch (Exception e) {
            return ResponseEntity.badRequest().body("Error al obtener direccion");
        }
    }



}
