package domain.controllers;

import domain.models.entities.venta.Venta;
import domain.repositories.VentaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/ventas")
public class VentaController {
    @Autowired
    private VentaRepository ventaRepository;

    @CrossOrigin(origins = "http://localhost:4200")
    @GetMapping("/{id}")
    public ResponseEntity<Venta> buscarVenta(@PathVariable(name = "id") Integer id){
        try {
            if (ventaRepository.findById(id).isPresent()) {
                return ResponseEntity.ok(ventaRepository.findById(id).get());
            } else {
                return ResponseEntity.notFound().build();
            }
        } catch (Exception e) {
            return ResponseEntity.badRequest().build();
        }
    }
}
