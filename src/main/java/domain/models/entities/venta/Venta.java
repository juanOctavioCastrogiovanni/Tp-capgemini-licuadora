package domain.models.entities.venta;

import com.fasterxml.jackson.annotation.JsonBackReference;
import domain.models.Persistence;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.time.LocalDateTime;

@Entity
@Table(name = "ventas")
@Setter
@Getter
public class Venta extends Persistence {

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

    @OneToOne
    @JoinColumn(name = "carrito_id", referencedColumnName = "id", nullable = false)
    private Carrito carrito;

    public Venta() {
        super();
    }

    public Venta(Carrito carrito, Direccion direccion, TipoDePago pago, Cliente cliente, LocalDateTime fechaCreacion) {
        super(fechaCreacion);
        this.direccion = direccion;
        this.pago = pago;
        this.cliente = cliente;
        this.carrito = carrito;
    }

}
