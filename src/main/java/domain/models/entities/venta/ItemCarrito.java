package domain.models.entities.venta;

import com.fasterxml.jackson.annotation.JsonBackReference;
import domain.models.Persistence;
import lombok.Getter;
import lombok.Setter;
import javax.persistence.*;
import java.time.LocalDateTime;


@Entity
@Table(name = "items_carritos")
@Setter
@Getter
public class ItemCarrito extends Persistence {

    /*RELACION una Publicacion*/
    @ManyToOne
    @JoinColumn(name = "publicacion_id", referencedColumnName = "id")
    private Publicacion publicacion;

    /*RELACION un carrito*/
    @JsonBackReference
    @ManyToOne
    @JoinColumn(name = "carrito_id", referencedColumnName = "id")
    private Carrito carrito;

    @Column(name = "cantidad")
    private Integer cantidad;

    @Column(name = "sub_total")
    private Float subTotal;

    public ItemCarrito() {
        super();
    }

    public ItemCarrito(Publicacion publicacion, Carrito carrito, Integer cantidad, Float subTotal, LocalDateTime fechaCreacion) {
        super(fechaCreacion);
        this.publicacion = publicacion;
        this.carrito = carrito;
        this.cantidad = cantidad;
        this.subTotal = subTotal;
    }
}
