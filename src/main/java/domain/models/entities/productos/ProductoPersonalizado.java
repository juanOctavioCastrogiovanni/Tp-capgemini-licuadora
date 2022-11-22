package domain.models.entities.productos;

import domain.models.PersistenceId;
import domain.models.entities.Publicacion;
import lombok.Getter;
import lombok.Setter;
import javax.persistence.*;


@Entity
@Table(name = "productos_personalizados")
@Setter
@Getter
public class ProductoPersonalizado extends PersistenceId {

    @Column(name = "precio")
    private Float precioBase;

    @Column(name = "TiempoDeFabricacion")
    private Integer TiempoDeFabricacion;

    /*RELACION publicacion a sociada*/
    @OneToOne
    @JoinColumn(name = "publicacion_id", referencedColumnName = "id")
    private Publicacion publicacion;


    /*RELACION un producto*/
    @ManyToOne
    @JoinColumn(name = "producto_id", referencedColumnName = "id")
    private Producto producto;
}
