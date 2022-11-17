package domain.models.entities;

import lombok.Getter;
import lombok.Setter;
import javax.persistence.*;


@Entity
@Table(name = "productos_personalizados")
@Setter
@Getter
public class ProductoPersonalizado {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer id;

    @Column(name = "precio")
    private Float precioBase;

    @Column(name = "TiempoDeFabricacion")
    private Integer TiempoDeFabricacion;

    /*RELACION MUCHOS A MUCHOS Lista de Areas personalizadas*/

    /*RELACION publicacion a sociada*/

}
