package domain.controllers;

import domain.models.DTO.DireccionDTO;
import domain.models.entities.venta.Direccion;
import domain.repositories.DireccionRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.time.LocalDateTime;

@RestController
@RequestMapping("/direccion")
public class DireccionController {
    @Autowired
    private DireccionRepository repoDireccion;

    @PostMapping({"/", ""})
    public ResponseEntity<Integer> crearDireccion(@RequestBody DireccionDTO direccionEntrante) {
        Direccion direccionNueva = new Direccion(direccionEntrante.getCalle(),direccionEntrante.getAltura(), direccionEntrante.getPiso(), direccionEntrante.getDepartamento(), direccionEntrante.getLocalidad(), direccionEntrante.getProvincia(), LocalDateTime.now());
        repoDireccion.save(direccionNueva);
        return ResponseEntity.ok(direccionNueva.getId());
    }

}
