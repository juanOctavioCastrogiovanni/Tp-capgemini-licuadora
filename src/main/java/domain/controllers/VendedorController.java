package domain.controllers;

import domain.models.entities.venta.Vendedor;
import domain.repositories.VendedorRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;


@RestController
@RequestMapping("/vendedores")
public class VendedorController {

    @Autowired
    private VendedorRepository vendedorRepository;

    @GetMapping({"", "/"})
    public List<Vendedor> vendedores(){
        return vendedorRepository.findAll();
    }

    @GetMapping("/{id}")
    public Optional<Vendedor> vendedor(@PathVariable(name = "id") Integer id){
        return vendedorRepository.findById(id);
    }

    @PostMapping({"", "/"})
    public Vendedor agregarVendedor(@RequestBody Vendedor vendedor){
        vendedor.setFechaCreacion(LocalDateTime.now());
        vendedorRepository.save(vendedor);
        return vendedor;
    }

    @PutMapping("/{id}")
    public Vendedor modificarVendedor(@PathVariable(name = "id") Integer id, @RequestBody Vendedor vendedor){
        vendedor.setId(id);
        vendedorRepository.save(vendedor);
        return vendedor;
    }
}
