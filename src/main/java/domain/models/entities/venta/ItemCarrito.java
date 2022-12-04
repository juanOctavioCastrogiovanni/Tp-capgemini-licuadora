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
    @JoinColumn(name = "publicacion_id", referencedColumnName = "id", nullable = false)
    private Publicacion publicacion;

    /*RELACION un carrito*/
    @JsonBackReference
    @ManyToOne
    @JoinColumn(name = "carrito_id", referencedColumnName = "id", nullable = false)
    private Carrito carrito;


    @Column(name = "precio_unitario")
    private Float precioUnitario;

    @Column(name = "cantidad")
    private Integer cantidad;

    @Column(name = "sub_total")
    private Float subTotal;

    public ItemCarrito() {
        super();
    }

    public ItemCarrito(Publicacion publicacion, Integer cantidad, Float precioUnitario,Float subTotal, LocalDateTime fechaCreacion) {
        super(fechaCreacion);
        this.publicacion = publicacion;
        this.cantidad = cantidad;
        this.precioUnitario = precioUnitario;
        this.subTotal = subTotal;
    }
}
