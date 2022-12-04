package domain.controllers;

import domain.models.entities.venta.Carrito;
import domain.repositories.ClienteRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/clientes")
public class ClienteController {
    @Autowired
    private ClienteRepository repoCliente;

    @GetMapping("/{id}/carritos")
    public List<Carrito> getCarritos(@PathVariable(name = "id") Integer id) {
        return repoCliente.findById(id).get().obtenerClientes();
    }


}
