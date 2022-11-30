package domain.controllers;

import domain.models.entities.venta.Vendedor;
import domain.repositories.VendedorRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;


@RestController
@RequestMapping("/vendedores")
public class VendedorController {

    @Autowired
    private VendedorRepository vendedorRepository;

    @GetMapping({"", "/"})
    public Object vendedores(){
        return vendedorRepository.findAll();
    }

    @GetMapping("/{id}")
    public Object vendedor(@PathVariable(name = "id") Integer id){
        return vendedorRepository.findById(id);
    }

    @PostMapping({"", "/"})
    public Vendedor agregarVendedor(@RequestBody Vendedor vendedor){
        vendedorRepository.save(vendedor);
        return vendedor;
    }
}
