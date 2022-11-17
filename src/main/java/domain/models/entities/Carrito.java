package domain.models.entities;

import lombok.Getter;
import lombok.Setter;
import javax.persistence.*;
import java.time.LocalDate;


@Entity
@Table(name = "carrito")
@Setter
@Getter
public class Carrito {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer id;

    /*RELACION Lista de publicaciones*/

    /*RELACION una venta*/

    @Column(name = "cantidad")
    private Integer cantidad;

    @Column(name = "sub_total")
    private Float subTotal;

}
