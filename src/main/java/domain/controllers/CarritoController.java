package domain.controllers;

import domain.models.DTO.VentaDTO;
import domain.models.entities.venta.*;
import domain.repositories.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.transaction.Transactional;
import javax.validation.Valid;
import java.time.LocalDateTime;

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

    // This method retrieves all the carritos from the database and returns them in the response. 
    // Use crossOrigin to allow the front to access the data because exist incompatibility in cors.

    // Este método recupera todos los carritos de la base de datos y los devuelve en la respuesta.
    // Use crossOrigin para permitir que el front acceda a los datos porque existe incompatibilidad en cors.
    @CrossOrigin(origins = "https://capgemini-tp-licuadora.web.app")
    @GetMapping("/{id}")
    public ResponseEntity<?> obtenerCarrito(@PathVariable(name = "id") Integer id){
        Carrito carrito = repoCarrito.findById(id).get();
        if(carrito != null){
            return ResponseEntity.ok(carrito);
        }
        return new ResponseEntity<>("No se encontro el carrito", HttpStatus.NOT_FOUND);
    }

    // This method creates a new carrito and returns it in the response.
    // Este método crea un nuevo carrito y lo devuelve en la respuesta.
    @CrossOrigin(origins = "https://capgemini-tp-licuadora.web.app")
    @PostMapping({"/", ""})
    public ResponseEntity<?> crearCarrito() {
        try {
            Carrito carrito = new Carrito(LocalDateTime.now());
            repoCarrito.save(carrito);
            return new ResponseEntity<>(carrito.getId(), HttpStatus.CREATED);
        } catch (Exception e) {
            return new ResponseEntity<>("No se ha podido crear el carrito", HttpStatus.BAD_REQUEST);
        }
    }

    // This method adds a new item to the cart and returns it in the response.
    // Este método agrega un nuevo elemento al carrito y lo devuelve en la respuesta.
    @CrossOrigin(origins = "https://capgemini-tp-licuadora.web.app")
    @Transactional
    @PostMapping("/{carritoId}/publicacion/{publicacionId}")
    public ResponseEntity<String> agregar(@PathVariable Integer carritoId, @PathVariable Integer publicacionId) {

        // exist validations in the case that there is no stock of the publication, that there is no cart or publication, that you cannot add a product from another seller
        // Existe validaciones en el caso de que no haya stock de la publicacion, que no exista el carrito o la publicacion, que no se pueda agregar un producto de otro vendedor


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
            //If the publication is not there, add it to the cart

            if (!carritoActual.getItems().stream().anyMatch(item -> item.getPublicacion().getId().equals(publicacionId))) {
                ItemCarrito itemAAgregar = new ItemCarrito(publicacionAAgregar, 1, publicacionAAgregar.getProductoPersonalizado().getPrecio(), 0F, LocalDateTime.now());
                itemAAgregar.calcularSubTotal();
                carritoActual.agregarItemCarrito(itemAAgregar);
                repoItemCarrito.save(itemAAgregar);
            } else {
                //Si esta la publicacion, aumenta la cantidad
                //If the publication is there, increase the quantity
                ItemCarrito itemAAumentar = carritoActual.getItems().stream().filter(item -> item.getPublicacion().getId() == publicacionId).findFirst().get();
                itemAAumentar.setCantidad(itemAAumentar.getCantidad() + 1);
                itemAAumentar.calcularSubTotal();
            }

            //calcula el total y lo modifica en la base de datos
            //calculate the total and modify it in the database
            carritoActual.calcularTotalCarrito();


            return ResponseEntity.created(null).body("Se agrego el producto al carrito");

        } catch (Exception e) {
            return ResponseEntity.badRequest().body("Error al agregar publicacion al carrito");
        }
    }

    // Este método elimina un elemento del carrito y lo devuelve en la respuesta.
    // This method removes an item from the cart and returns it in the response.
    @CrossOrigin(origins = "https://capgemini-tp-licuadora.web.app")
    @Transactional
    @DeleteMapping("/{carritoId}/item/{itemId}")
    public ResponseEntity<String> eliminarItem(@PathVariable Integer carritoId, @PathVariable Integer itemId) {
        try {
            if (!repoCarrito.existsById(carritoId) || !repoItemCarrito.existsById(itemId)) {
                return new ResponseEntity<>("No existe el carrito o el item", HttpStatus.NOT_FOUND);
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

    // Este metodo a diferencia del anterior no elimina el item lo que hace es restar en uno su cantidad excepto que su cantidad 
    // sea 1 en ese caso lo elimina

    // This method, unlike the previous one, does not delete the item, what it does is subtract one from its quantity except that its quantity
    // is 1 in that case it deletes it

    @CrossOrigin(origins = "https://capgemini-tp-licuadora.web.app")
    @Transactional
    @DeleteMapping("/{carritoId}/restar-publicacion/{publicacionId}")
    public ResponseEntity<String> remover(@PathVariable Integer carritoId, @PathVariable Integer publicacionId) {
        try {
            if (!repoCarrito.existsById(carritoId) || !repoPublicacion.existsById(publicacionId)) {
                return ResponseEntity.badRequest().body("No existe el carrito o la publicacion");
            }

            Carrito carritoActual = repoCarrito.findById(carritoId).get();

            //recorre la lista de publicaciones dentro del carrito a ver si alguna tiene el id que viene por parametro 
            //walks through the list of publications within the cart to see if any have the id that comes by parameter
            if (!carritoActual.getItems().stream().anyMatch(item -> item.getPublicacion().getId().equals(publicacionId))) {
                return ResponseEntity.badRequest().body("No se puede remover un producto que no esta en el carrito");
            }

            //si la cantidad es mayor a 1, resta en uno la cantidad y calcula el subtotal sino lo elimina
            //if the quantity is greater than 1, subtract one from the quantity and calculate the subtotal otherwise it deletes it
            ItemCarrito itemARemover = carritoActual.getItems().stream().filter(item -> item.getPublicacion().getId() == publicacionId).findFirst().get();
            if (itemARemover.getCantidad() > 1) {
                itemARemover.setCantidad(itemARemover.getCantidad() - 1);
                itemARemover.calcularSubTotal();
            } else {
                carritoActual.removerItemCarrito(itemARemover);
                repoItemCarrito.delete(itemARemover);
            }

            carritoActual.calcularTotalCarrito();

            return ResponseEntity.ok("Se removio correctamente");
        } catch (Exception e) {
            return ResponseEntity.badRequest().body("Error al remover publicacion del carrito");
        }

    }


    // Este metodo ejecuta la venta cuando el comprador llena sus datos y elije el tipo de pago, crea distintas instancias de los modelos relacionales de la 
    // base de datos como por ejemplo la direccion, el tipo de pago, el cliente, etc. 
    // La particularidad es que si no existe un usuario logueado lo crea en el momento, si la direccion no existe, tambien la crea en el momento, entre otras 
    // validaciones que realiza.

    // This method executes the sale when the buyer fills in their data and chooses the type of payment, it creates different instances of the relational
    // models of the database such as the address, the type of payment, the client, etc.
    // The peculiarity is that if there is no logged in user, it creates it at the moment, if the address does not exist, it also creates it at the moment, 
    // among other validations that it performs.
    @CrossOrigin(origins = "https://capgemini-tp-licuadora.web.app")
    @PostMapping("/{carritoId}/venta")
    public  ResponseEntity<String> ventaRealizada(
            @PathVariable(name="carritoId") Integer carritoId,
            @Valid @RequestBody VentaDTO venta,
            BindingResult bindingResult
    ) {

      if (!bindingResult.hasErrors()) {
          if (!repoCarrito.existsById(carritoId)) {
              return new ResponseEntity<>("No existe el carrito", HttpStatus.NOT_FOUND);
          }
          Carrito carrito = repoCarrito.findById(carritoId).get();
          if (carrito.getItems().size() == 0) {
              return new ResponseEntity<>("No hay items en el carrito", HttpStatus.NOT_FOUND);
          }
          if (!repoTipoDePago.existsById(venta.getPagoId())) {
              return new ResponseEntity<>("No existe el tipo de pago", HttpStatus.NOT_FOUND);
          }
          TipoDePago tipoDePago = repoTipoDePago.findById(venta.getPagoId()).get();




          Direccion direccion;

          if(venta.getDireccionId()==null){
              direccion = repoDireccion.save(new Direccion(venta.getCalle(), venta.getAltura(), venta.getPiso(), venta.getDepartamento(), venta.getLocalidad(), venta.getProvincia(), LocalDateTime.now()));
          } else if(!repoDireccion.existsById(venta.getDireccionId())){
                return new ResponseEntity<>("No existe la direccion", HttpStatus.NOT_FOUND);
          } else {
              direccion = repoDireccion.findById(venta.getDireccionId()).get();
          }




          Cliente cliente;

          if(venta.getClienteId()==null){
              cliente = repoCliente.save(new Cliente(venta.getNombreCliente(), venta.getApellidoCliente(), venta.getEmailCliente(), direccion, LocalDateTime.now()));
          } else if (!repoCliente.existsById(venta.getClienteId())) {
                return new ResponseEntity<>("No existe el cliente", HttpStatus.NOT_FOUND);
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



}
