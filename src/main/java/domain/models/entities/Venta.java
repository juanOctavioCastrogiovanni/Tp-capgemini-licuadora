package domain.models.entities;

import lombok.Getter;
import lombok.Setter;
import javax.persistence.*;
import java.time.LocalDate;


@Entity
@Table(name = "ventas")
@Setter
@Getter
public class Venta {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer id;

    @Column(name = "fecha_de_venta")
    private LocalDate fecha;

    @Column(name = "precioTotal")
    private Float precioTotal;

    @Column(name = "estado")
    private String estado;

    /*RELACION un Tipo de pago*/

    /*RELACION un cliente*/

    /*RELACION Lista de carritos (publicaciones)*/

}
