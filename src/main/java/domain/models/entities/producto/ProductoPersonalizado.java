package domain.models.entities.producto;

import com.fasterxml.jackson.annotation.JsonManagedReference;
import domain.models.PersistenceId;
import domain.models.entities.venta.Vendedor;
import lombok.Getter;
import lombok.Setter;
import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;


@Entity
@Table(name = "productos_personalizados")
@Setter
@Getter
public class ProductoPersonalizado extends PersistenceId {



    /*RELACION publicacion a sociada - anulo porque no importa*/


    /*RELACION un producto*/
    @ManyToOne
    @JoinColumn(name = "producto_base_id", referencedColumnName = "id")
    private Producto producto;

    /*RELACION un vendedor*/
    @ManyToOne
    @JoinColumn(name = "vendedor_id", referencedColumnName = "id")
    private Vendedor vendedor;

    //RELACION lista de personalizaciones
    @JsonManagedReference
    @OneToMany(cascade = {CascadeType.ALL}, mappedBy = "productoPersonalizado", fetch = FetchType.LAZY)
    private List<Personalizacion> personalizaciones;

    @Column(name = "precio")
    private Float precio;

    public ProductoPersonalizado() {
        personalizaciones = new ArrayList<>();
    }

    public void agregarPersonalizacion(Personalizacion personalizacion) {
        personalizaciones.add(personalizacion);
    }



}
