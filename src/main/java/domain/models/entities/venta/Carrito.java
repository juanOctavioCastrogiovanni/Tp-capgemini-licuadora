package domain.models.entities.venta;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import domain.models.entities.producto.Categoria;
import lombok.Getter;
import lombok.Setter;
import javax.persistence.*;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.List;
import domain.models.Persistence;


@Entity
@Table(name = "carritos")
@Setter
@Getter
public class Carrito extends Persistence {

    @Column(name = "precioTotal")
    private Float precioTotal;


    //RELACION a una direccion de envio
    @ManyToOne
    @JoinColumn(name = "direccion_id", referencedColumnName = "id", nullable = false)
    private Direccion direccion;

    /*RELACION un Tipo de pago*/

    @OneToOne
    @JoinColumn(name = "pago_id", referencedColumnName = "id", nullable = false)
    private TipoDePago pago;

    /*RELACION un cliente*/
    @JsonBackReference
    @ManyToOne
    @JoinColumn(name = "cliente_id", referencedColumnName = "id", nullable = false)
    private Cliente cliente;

    /*RELACION Lista de carritos (publicaciones)*/
    @JsonManagedReference
    @OneToMany(mappedBy = "carrito", cascade = {CascadeType.MERGE, CascadeType.PERSIST}, fetch = FetchType.LAZY)
    private List<ItemCarrito> itemCarritos;

    public Carrito() {
        super();
        itemCarritos = new ArrayList<>();
    }

    public void agregarItemCarrito(ItemCarrito itemCarrito) {
        itemCarritos.add(itemCarrito);
        itemCarrito.setCarrito(this);
    }

    public Carrito(Float precioTotal, TipoDePago tipoDePago, Cliente cliente,  Direccion direccion, LocalDateTime fechaCreacion) {
        super(fechaCreacion);
        this.precioTotal = precioTotal;
        this.pago = tipoDePago;
        this.cliente = cliente;
        this.direccion = direccion;
        itemCarritos = new ArrayList<>();
    }

}
