package domain.models.entities.venta;

import lombok.Getter;
import lombok.Setter;
import javax.persistence.*;


@Entity
@Table(name = "carrito")
@Setter
@Getter
public class Carrito {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer id;

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




}
