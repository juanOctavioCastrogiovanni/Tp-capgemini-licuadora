package domain.models.entities.venta;

import domain.models.Persistence;
import lombok.Getter;
import lombok.Setter;
import javax.persistence.*;
import java.time.LocalDateTime;


@Entity
@Table(name = "carrito")
@Setter
@Getter
public class Carrito extends Persistence {

    /*RELACION una Publicacion*/
    @ManyToOne
    @JoinColumn(name = "publicacion_id", referencedColumnName = "id")
    private Publicacion publicacion;

    /*RELACION una venta*/
    @ManyToOne
    @JoinColumn(name = "venta_id", referencedColumnName = "id")
    private Venta venta;

    @Column(name = "cantidad")
    private Integer cantidad;

    @Column(name = "sub_total")
    private Float subTotal;

    public Carrito() {
        super();
    }

    public Carrito(Publicacion publicacion, Venta venta, Integer cantidad, Float subTotal, LocalDateTime fechaCreacion) {
        super(fechaCreacion);
        this.publicacion = publicacion;
        this.venta = venta;
        this.cantidad = cantidad;
        this.subTotal = subTotal;
    }
}
