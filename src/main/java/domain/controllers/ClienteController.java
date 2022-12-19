package domain.controllers;


import domain.models.DTO.InicioSesionDTO;
import domain.models.entities.venta.Carrito;
import domain.models.entities.venta.Cliente;
import domain.models.DTO.ClienteDTO;
import domain.models.entities.venta.Venta;
import domain.repositories.CarritoRepository;
import domain.repositories.ClienteRepository;
import domain.repositories.DireccionRepository;
import domain.repositories.VentaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.web.bind.annotation.*;
import domain.models.entities.venta.Direccion;

import java.security.SecureRandom;
import java.time.LocalDateTime;
import java.util.List;

@RestController
@RequestMapping("/clientes")
public class ClienteController {
    @Autowired
    private ClienteRepository repoCliente;

    @Autowired
    private CarritoRepository repoCarrito;

    @Autowired
    private VentaRepository repoVenta;

    @Autowired
    private DireccionRepository repoDireccion;


    @PostMapping({"/", ""})
    public ResponseEntity<Integer> crearCliente(@RequestBody ClienteDTO clienteEntrante) {
        if(!repoDireccion.existsById(clienteEntrante.getDireccionId())){
            return ResponseEntity.badRequest().build();
        }
        Direccion direccion = repoDireccion.getOne(clienteEntrante.getDireccionId());
        Cliente clienteNuevo = new Cliente(clienteEntrante.getNombre(), clienteEntrante.getApellido(), clienteEntrante.getMail(), direccion, LocalDateTime.now());
        repoCliente.save(clienteNuevo);
        return ResponseEntity.ok(clienteNuevo.getId());
    }
    @CrossOrigin(origins = "http://localhost:4200")
    @GetMapping("/{id}/direccion")
    public ResponseEntity<Direccion> obtenerDireccion(@PathVariable Integer id) {
        if(!repoCliente.existsById(id)){
            return ResponseEntity.notFound().build();
        }
        Cliente cliente = repoCliente.findById(id).get();
        return ResponseEntity.ok(cliente.getDireccion());
    }
    @CrossOrigin(origins = "http://localhost:4200")
    @GetMapping("/{id}")
    public ResponseEntity<Cliente> obtenerCliente(@PathVariable Integer id) {
        if(!repoCliente.existsById(id)){
            return ResponseEntity.notFound().build();
        }
        Cliente cliente = repoCliente.findById(id).get();
        return ResponseEntity.ok(cliente);
    }
    @CrossOrigin(origins = "http://localhost:4200")
    @PostMapping("/iniciar-sesion")
    public ResponseEntity<Cliente> iniciarSesion(@RequestBody InicioSesionDTO clienteEntrante) {
        if(!repoCliente.existsByEmail(clienteEntrante.getEmail())){
            return ResponseEntity.badRequest().build();
        }

        //Traigo el cliente de la base de datos.
        Cliente cliente = repoCliente.findByEmail(clienteEntrante.getEmail());
        //Traiguo instancia de encoder
        BCryptPasswordEncoder encoder = new BCryptPasswordEncoder(10, new SecureRandom());

        //comparo a ver si la contraseña hasheada es la misma que la que vino por formulario.
        if(!encoder.matches(clienteEntrante.getPassword(), cliente.getPassword())){
            return ResponseEntity.badRequest().build();
        }
        return ResponseEntity.ok(cliente);
    }

    @CrossOrigin(origins = "http://localhost:4200")
    @GetMapping("/{id}/carritos")
    public List<Venta> getCarritos(@PathVariable(name = "id") Integer id) {
        Cliente cliente = repoCliente.findById(id).orElse(null);
        return cliente.getCarritos();
    }

    @GetMapping("/{id}/carritos/{idCarrito}")
    public Venta getCarrito(
            @PathVariable(name = "id") Integer id,
            @PathVariable(name = "idCarrito") Integer idCarrito
    ) {
        Cliente cliente = repoCliente.findById(id).orElse(null);
        if (!repoVenta.existsById(idCarrito) || !cliente.getCarritos().contains(repoVenta.findById(idCarrito).orElse(null))) {
            return null;
        }
        return repoVenta.findById(idCarrito).orElse(null);
    }

}
