package domain.models.entities;

import lombok.Getter;
import lombok.Setter;
import javax.persistence.*;


@Entity
@Table(name = "publicaciones")
@Setter
@Getter
public class Publicacion {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer id;

    @Column(name = "nombre")
    private String nombre;

    @Column(name = "imagen")
    private String imagen;

    @Column(name = "stock")
    private Integer stock;

    @Column(name = "estado")
    private String estado;

    /*RELACION un vendedor*/

    /*RELACION un producto personalizado*/

    /* RELACION Lista de carritos (ventas) */

}
