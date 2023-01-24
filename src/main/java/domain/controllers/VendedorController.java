package domain.controllers;

import domain.models.entities.venta.Vendedor;
import domain.repositories.VendedorRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;


@RestController
@RequestMapping("/vendedores")
public class VendedorController {

    @Autowired
    private VendedorRepository vendedorRepository;

    @CrossOrigin(origins = "http://localhost:4200")
    @GetMapping({"", "/"})
    public ResponseEntity<?> vendedores(){

        try {
            return ResponseEntity.ok(vendedorRepository.findAll());
        }
        catch (Exception e){
            return ResponseEntity.badRequest().body("No se ha encontrado vendedores");
        }
    }

    @GetMapping("/{id}")
    public ResponseEntity<?> vendedor(@PathVariable(name = "id") Integer id){
        try {
            Optional<Vendedor> vendedor = vendedorRepository.findById(id);
            if(vendedor.isPresent()){
                return ResponseEntity.ok(vendedor.get());
            }
            else{
                return ResponseEntity.badRequest().body("No se ha encontrado el vendedor");
            }
        }
        catch (Exception e){
            return ResponseEntity.badRequest().body("Error al traer vendedores");
        }
    }

    @PostMapping({"", "/"})
    public ResponseEntity<String> agregarVendedor(@RequestBody Vendedor vendedor){
        try{
            vendedor.setFechaCreacion(LocalDateTime.now());
            vendedorRepository.save(vendedor);
            return ResponseEntity.ok("Vendedor agregado");
        } catch (Exception e){
            return ResponseEntity.badRequest().body("Error al agregar vendedor");
        }

    }

    @PutMapping("/{id}")
    public ResponseEntity<String> modificarVendedor(@PathVariable(name = "id") Integer id, @RequestBody Vendedor vendedor){
        try{
            vendedor.setId(id);
            vendedorRepository.save(vendedor);
            return ResponseEntity.ok("Vendedor modificado");
        } catch (Exception e){
            return ResponseEntity.badRequest().body("Error al modificar vendedor");
        }
    }
}
