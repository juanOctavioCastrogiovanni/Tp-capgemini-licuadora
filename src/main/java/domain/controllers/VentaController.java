package domain.controllers;

import domain.models.entities.venta.Venta;
import domain.repositories.VentaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/ventas")
public class VentaController {
    @Autowired
    private VentaRepository ventaRepository;

    @CrossOrigin(origins = "https://capgemini-tp-licuadora.web.app")
    @GetMapping("/{id}")
    public ResponseEntity<?> buscarVenta(@PathVariable(name = "id") Integer id){
        try {
            if (ventaRepository.findById(id).isPresent()) {
                return ResponseEntity.ok(ventaRepository.findById(id).get());
            } else {
                return new ResponseEntity<>("No se ha encontrado la venta", HttpStatus.NOT_FOUND);
            }
        } catch (Exception e) {
            return ResponseEntity.badRequest().body("Error al buscar venta");
        }
    }
}
