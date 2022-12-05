package domain.controllers;

import com.sun.xml.internal.ws.developer.StreamingAttachment;
import domain.models.DTO.CarritoDTO;
import domain.models.entities.venta.*;
import domain.repositories.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.naming.Binding;
import javax.transaction.Transactional;
import javax.validation.Valid;
import java.time.LocalDateTime;
import java.util.List;

@RestController
@RequestMapping("/carrito")
public class CarritoController {

    @Autowired
    private PublicacionRepository repoPublicacion;

    @Autowired
    private CarritoRepository repoCarrito;

    @Autowired
    private ItemCarritoRepository repoItemCarrito;

    @Autowired
    private DireccionRepository repoDireccion;

    @Autowired
    private TipoDePagoRepository repoTipoDePago;

    @Autowired
    private ClienteRepository repoCliente;

    @Autowired
    private VentaRepository repoVenta;

    @GetMapping({"/", ""})
    public List<Carrito> getAll() {
        return repoCarrito.findAll();
    }

    @Transactional
    @PostMapping({"/", ""})
    public ResponseEntity<String> createCarrito(@RequestBody @Valid CarritoDTO carritoEntrante,
                                                BindingResult bindingResult) {
        try {
                if (!bindingResult.hasErrors()) {
                    Cliente cliente;
                        System.out.println(carritoEntrante.getClienteId());

                    if (carritoEntrante.getNombre() != null && carritoEntrante.getApellido() != null && carritoEntrante.getMail() != null && carritoEntrante.getClienteId() == null){
                        cliente = repoCliente.save(new Cliente(carritoEntrante.getNombre(), carritoEntrante.getApellido(), null , carritoEntrante.getMail() , null, LocalDateTime.now()));
                    } else if (repoCliente.existsById(carritoEntrante.getClienteId())) {
                        cliente = repoCliente.findById(carritoEntrante.getClienteId()).get();
                    } else {
                        return ResponseEntity.badRequest().body("El cliente no existe");
                    }

                    if (!repoTipoDePago.existsById(carritoEntrante.getTipoDePagoId())) {
                        return ResponseEntity.badRequest().body("El tipo de pago no existe");
                    }

                    TipoDePago pago = repoTipoDePago.findById(carritoEntrante.getTipoDePagoId()).get();

                    Direccion direccion;

                    if (carritoEntrante.getDireccionId()!=null && !repoDireccion.existsById(carritoEntrante.getDireccionId())){

                        return ResponseEntity.badRequest().body("La direccion no existe");

                    } else if (carritoEntrante.getDireccionId()==null){

                        direccion = repoDireccion.save(new Direccion(carritoEntrante.getCalle(),
                                carritoEntrante.getAltura(), carritoEntrante.getPiso(),
                                carritoEntrante.getDepartamento(),
                                carritoEntrante.getLocalidad(),
                                carritoEntrante.getProvincia(),
                                cliente, LocalDateTime.now()));
                    } else {

                        direccion = repoDireccion.findById(carritoEntrante.getDireccionId()).get();

                    }

                    Carrito carrito = new Carrito(carritoEntrante.getPrecioTotal(), LocalDateTime.now());

                    carritoEntrante.getItems().forEach(item -> {
                        if (!repoPublicacion.existsById(item.getPublicacionId())) {
                            throw new RuntimeException("La publicacion no existe");
                        }
                        Publicacion publicacionActual = repoPublicacion.findById(item.getPublicacionId()).get();
                        ItemCarrito itemActual = new ItemCarrito(publicacionActual, item.getCantidad(), item.getPrecioUnitario(), item.getSubTotal(), LocalDateTime.now());
                        repoItemCarrito.save(itemActual);
                        carrito.agregarItemCarrito(itemActual);
                        repoCarrito.save(carrito);
                    });

                    repoVenta.save(new Venta(carrito, direccion, pago, cliente, LocalDateTime.now()));



                    return ResponseEntity.ok("Carrito creado con exito");
                } else {
                    return ResponseEntity.badRequest().body("Error en los datos del carrito");
                }
            } catch (Exception e) {
                return ResponseEntity.badRequest().body("Error al crear el carrito");
            }
    }
}
