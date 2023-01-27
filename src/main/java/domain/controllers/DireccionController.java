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

    //Este metodo permite crear una direccion nueva
    // This method allows you to create a new address
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

    //Este metodo devuelve todas las direcciones no se implemento ninguna validacion de seguridad con spring security como JWT en este proyecto de practica
    // This method returns all addresses no security validation with spring security as JWT was implemented in this practice project
    @GetMapping({"/", ""})
    public ResponseEntity<?> obtenerDirecciones() {
        try{
            return ResponseEntity.ok(repoDireccion.findAll());
        } catch (Exception e) {
            return ResponseEntity.badRequest().body("Error al obtener direcciones");
        }
    }

    //Este metodo devuelve una direccion por id
    // This method returns an address by id
    @GetMapping("/{id}")
    public ResponseEntity<?> obtenerDireccion(@PathVariable(name = "id") Integer id) {
        try{
            return ResponseEntity.ok(repoDireccion.findById(id));
        } catch (Exception e) {
            return ResponseEntity.badRequest().body("Error al obtener direccion");
        }
    }



}
