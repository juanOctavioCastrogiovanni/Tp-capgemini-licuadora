package domain.controllers;

import com.sun.xml.internal.ws.developer.StreamingAttachment;
import domain.models.DTO.CarritoDTO;
import domain.models.DTO.DireccionDTO;
import domain.models.DTO.VentaDTO;
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
import java.util.Optional;

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


    @CrossOrigin(origins = "http://localhost:4200")
    @GetMapping("/{id}")
    public ResponseEntity<Carrito> obtenerCarrito(@PathVariable(name = "id") Integer id){
        Optional<Carrito> carrito = repoCarrito.findById(id);
        if(carrito.isPresent()){
            return ResponseEntity.ok(carrito.get());
        }
        return ResponseEntity.notFound().build();
    }


    @CrossOrigin(origins = "http://localhost:4200")
    @PostMapping({"/", ""})
    public @ResponseBody Integer crearCarrito() {
        try {
            Carrito carrito = new Carrito(LocalDateTime.now());
            repoCarrito.save(carrito);
            return carrito.getId();
        } catch (Exception e) {
            return null;
        }
    }

    @CrossOrigin(origins = "http://localhost:4200")
    @Transactional
    @PostMapping("/{carritoId}/publicacion/{publicacionId}")
    public ResponseEntity<String> agregar(@PathVariable Integer carritoId, @PathVariable Integer publicacionId) {
        try {
            if (!repoCarrito.existsById(carritoId) || !repoPublicacion.existsById(publicacionId)) {
                return ResponseEntity.badRequest().body("No existe el carrito o la publicacion");
            }

            Publicacion publicacionAAgregar = repoPublicacion.findById(publicacionId).get();
            Carrito carritoActual = repoCarrito.findById(carritoId).get();

            if (publicacionAAgregar.getStock() == 0) {
                return ResponseEntity.badRequest().body("No hay stock");
            }

            if (carritoActual.getItems().size()>0 && !carritoActual.getItems().get(0).getPublicacion().getVendedor().equals(publicacionAAgregar.getVendedor())) {
                return ResponseEntity.badRequest().body("No se puede agregar un producto de otro vendedor");
            }



            //Si no esta la publicacion agregala al carrito
            if (!carritoActual.getItems().stream().anyMatch(item -> item.getPublicacion().getId().equals(publicacionId))) {
                ItemCarrito itemAAgregar = new ItemCarrito(publicacionAAgregar, 1, publicacionAAgregar.getProductoPersonalizado().getPrecio(), 0F, LocalDateTime.now());
                itemAAgregar.calcularSubTotal();
                carritoActual.agregarItemCarrito(itemAAgregar);
                repoItemCarrito.save(itemAAgregar);
                System.out.println("Se agrego el item al carrito");
            } else {
                //Si esta la publicacion, aumenta la cantidad
                ItemCarrito itemAAumentar = carritoActual.getItems().stream().filter(item -> item.getPublicacion().getId() == publicacionId).findFirst().get();
                itemAAumentar.setCantidad(itemAAumentar.getCantidad() + 1);
                itemAAumentar.calcularSubTotal();
                System.out.println("Se aumento la cantidad del item");
            }

            carritoActual.calcularTotalCarrito();


            return ResponseEntity.created(null).body("Se agrego el producto al carrito");

        } catch (Exception e) {
            return ResponseEntity.badRequest().body("Error al agregar publicacion al carrito");
        }
    }
    @CrossOrigin(origins = "http://localhost:4200")
    @Transactional
    @DeleteMapping("/{carritoId}/item/{itemId}")
    public ResponseEntity<String> eliminarItem(@PathVariable Integer carritoId, @PathVariable Integer itemId) {
        try {
            if (!repoCarrito.existsById(carritoId) || !repoItemCarrito.existsById(itemId)) {
                return ResponseEntity.badRequest().body("No existe el carrito o el item");
            }

            Carrito carritoActual = repoCarrito.findById(carritoId).get();
            ItemCarrito itemABorrar = repoItemCarrito.findById(itemId).get();
            carritoActual.removerItemCarrito(itemABorrar);
            repoItemCarrito.delete(itemABorrar);
            carritoActual.calcularTotalCarrito();
            return ResponseEntity.ok("Se elimino el item del carrito");

        } catch (Exception e) {
            return ResponseEntity.badRequest().body("Error al eliminar item del carrito");
        }
    }


    @CrossOrigin(origins = "http://localhost:4200")
    @Transactional
    @DeleteMapping("/{carritoId}/restar-publicacion/{publicacionId}")
    public ResponseEntity<String> remover(@PathVariable Integer carritoId, @PathVariable Integer publicacionId) {
        try {
            if (!repoCarrito.existsById(carritoId) || !repoPublicacion.existsById(publicacionId)) {
                return ResponseEntity.badRequest().body("No existe el carrito o la publicacion");
            }

            Publicacion publicacionARemover = repoPublicacion.findById(publicacionId).get();
            Carrito carritoActual = repoCarrito.findById(carritoId).get();

            if (!carritoActual.getItems().stream().anyMatch(item -> item.getPublicacion().getId().equals(publicacionId))) {
                return ResponseEntity.badRequest().body("No se puede remover un producto que no esta en el carrito");
            }

            ItemCarrito itemARemover = carritoActual.getItems().stream().filter(item -> item.getPublicacion().getId() == publicacionId).findFirst().get();
            if (itemARemover.getCantidad() > 1) {
                itemARemover.setCantidad(itemARemover.getCantidad() - 1);
                itemARemover.calcularSubTotal();
                System.out.println("Se disminuyo la cantidad del item");
            } else {
                carritoActual.removerItemCarrito(itemARemover);
                repoItemCarrito.delete(itemARemover);
                System.out.println("Se removio el item del carrito");
            }

            carritoActual.calcularTotalCarrito();

            return ResponseEntity.ok("Se removio correctamente");
        } catch (Exception e) {
            return ResponseEntity.badRequest().body("Error al remover publicacion del carrito");
        }

    }

    @CrossOrigin(origins = "http://localhost:4200")
    @PostMapping("/{carritoId}/venta")
    public  ResponseEntity<String> ventaRealizada(
            @PathVariable(name="carritoId") Integer carritoId,
            @Valid @RequestBody VentaDTO venta,
            BindingResult bindingResult
    ) {

      if (!bindingResult.hasErrors()) {
          if (!repoCarrito.existsById(carritoId)) {
              return ResponseEntity.badRequest().body("No existe el carrito");
          }
          Carrito carrito = repoCarrito.findById(carritoId).get();
          if (carrito.getItems().size() == 0) {
              return ResponseEntity.badRequest().body("No hay items en el carrito");
          }
          if (!repoTipoDePago.existsById(venta.getPagoId())) {
              return ResponseEntity.badRequest().body("No existe el tipo de pago");
          }
          TipoDePago tipoDePago = repoTipoDePago.findById(venta.getPagoId()).get();




          Direccion direccion;

          if(venta.getDireccionId()==null){
              direccion = repoDireccion.save(new Direccion(venta.getCalle(), venta.getAltura(), venta.getPiso(), venta.getDepartamento(), venta.getLocalidad(), venta.getProvincia(), LocalDateTime.now()));
          } else if(!repoDireccion.existsById(venta.getDireccionId())){
                return ResponseEntity.badRequest().body("No existe la direccion");
          } else {
              direccion = repoDireccion.findById(venta.getDireccionId()).get();
          }




          Cliente cliente;

          if(venta.getClienteId()==null){
              cliente = repoCliente.save(new Cliente(venta.getNombreCliente(), venta.getApellidoCliente(), venta.getEmailCliente(), direccion, LocalDateTime.now()));
          } else if (!repoCliente.existsById(venta.getClienteId())) {
                return ResponseEntity.badRequest().body("No existe el cliente");
          } else{
              cliente = repoCliente.findById(venta.getClienteId()).get();
          }

          Venta laVenta = (repoVenta.save(new Venta(carrito, direccion, tipoDePago, cliente, LocalDateTime.now())));

          carrito.getItems().forEach(item -> {
              item.getPublicacion().setStock(item.getPublicacion().getStock() - item.getCantidad());
              repoPublicacion.save(item.getPublicacion());
          });

          return ResponseEntity.created(null).body("Se realizo la venta correctamente " + laVenta.getId());


        } else {
            return ResponseEntity.badRequest().body("Error envio de datos");
        }
     }



    /*
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

     */


}
