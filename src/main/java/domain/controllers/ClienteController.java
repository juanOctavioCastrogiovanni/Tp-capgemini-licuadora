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
import org.springframework.http.HttpStatus;
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
    private VentaRepository repoVenta;

    @Autowired
    private DireccionRepository repoDireccion;

    //Este crea un nuevo cliente 
    // This creates a new client
    @PostMapping({"/", ""})
    public ResponseEntity<?> crearCliente(@RequestBody ClienteDTO clienteEntrante) {
        try {
            if(!repoDireccion.existsById(clienteEntrante.getDireccionId())){
                return new ResponseEntity<>("La direccion no existe", HttpStatus.BAD_REQUEST);
            }
            Direccion direccion = repoDireccion.getOne(clienteEntrante.getDireccionId());
            Cliente clienteNuevo = new Cliente(clienteEntrante.getNombre(), clienteEntrante.getApellido(), clienteEntrante.getMail(), direccion, LocalDateTime.now());
            repoCliente.save(clienteNuevo);
            return ResponseEntity.ok(clienteNuevo.getId());
        } catch (Exception e) {
            return new ResponseEntity<>("Error al crear el cliente", HttpStatus.BAD_REQUEST);
        }
    }

    //Este metodo devuelve la direccion de un cliente existente
    // This method returns the address of an existing client
    @CrossOrigin(origins = "https://capgemini-tp-licuadora.web.app")
    @GetMapping("/{id}/direccion")
    public ResponseEntity<?> obtenerDireccion(@PathVariable Integer id) {
        try {
            if(!repoCliente.existsById(id)){
                return new ResponseEntity<>("El cliente no existe", HttpStatus.NOT_FOUND);
            }
            Cliente cliente = repoCliente.findById(id).get();
            return ResponseEntity.ok(cliente.getDireccion());
        } catch (Exception e) {
            return new ResponseEntity<>("Error al obtener la direccion", HttpStatus.BAD_REQUEST);
        }
    }

    //Este metodo devuelve el cliente con el id que se le pasa por parametro
    // This method returns the client with the id that is passed as a parameter
    @CrossOrigin(origins = "https://capgemini-tp-licuadora.web.app")
    @GetMapping("/{id}")
    public ResponseEntity<?> obtenerCliente(@PathVariable Integer id) {
        try {
            if(!repoCliente.existsById(id)){
                return new ResponseEntity<>("El cliente no existe", HttpStatus.NOT_FOUND);
            }
            Cliente cliente = repoCliente.findById(id).get();
            return ResponseEntity.ok(cliente);
        } catch (Exception e) {
            return new ResponseEntity<>("Error al obtener el cliente", HttpStatus.BAD_REQUEST);
        }

    }

    //Este metodo valida al cliente con su contraseña y devuelve el cliente
    // This method validates the client with its password and returns the client
    @CrossOrigin(origins = "https://capgemini-tp-licuadora.web.app")
    @PostMapping("/iniciar-sesion")
    public ResponseEntity<?> iniciarSesion(@RequestBody InicioSesionDTO clienteEntrante) {
        if(!repoCliente.existsByEmail(clienteEntrante.getEmail())){
            return new ResponseEntity<>("El cliente no existe", HttpStatus.NOT_FOUND);
        }

        //Traigo el cliente de la base de datos.
        Cliente cliente = repoCliente.findByEmail(clienteEntrante.getEmail());
        //Traiguo instancia de encoder
        BCryptPasswordEncoder encoder = new BCryptPasswordEncoder(10, new SecureRandom());

        //comparo a ver si la contraseña hasheada es la misma que la que vino por formulario.
        if(!encoder.matches(clienteEntrante.getPassword(), cliente.getPassword())){
            return new ResponseEntity<>("Credenciales incorrectas, intente nuevamente", HttpStatus.BAD_REQUEST);
        }
        return ResponseEntity.ok(cliente);
    }

    //Este metodo devuelve todos los clientes del cliente
    // This method returns all the clients of the client
    @CrossOrigin(origins = "https://capgemini-tp-licuadora.web.app")
    @GetMapping("/{id}/carritos")
    public ResponseEntity<?> getCarritos(@PathVariable(name = "id") Integer id) {
        Cliente cliente = repoCliente.findById(id).orElse(null);
        if(cliente == null){
            return new ResponseEntity<>("El cliente no existe", HttpStatus.NOT_FOUND);
        }
        if (cliente.getCarritos().isEmpty()) {
            return new ResponseEntity<>("No ha hecho ninguna compra", HttpStatus.BAD_REQUEST);
        }
        return ResponseEntity.ok(cliente.getCarritos());
    }

    //Este metodo devuelve un carrito del cliente
    // This method returns a cart from the client
    @GetMapping("/{id}/carritos/{idCarrito}")
    public ResponseEntity<?> getCarrito(
            @PathVariable(name = "id") Integer id,
            @PathVariable(name = "idCarrito") Integer idCarrito
    ) {
        Cliente cliente = repoCliente.findById(id).orElse(null);
        if (!repoVenta.existsById(idCarrito) || !cliente.getCarritos().contains(repoVenta.findById(idCarrito).orElse(null))) {
            return new ResponseEntity<>("El carrito no existe o el cliente no existe", HttpStatus.NOT_FOUND);
        }
        return ResponseEntity.ok(repoVenta.findById(idCarrito));
    }

}
