package domain.models.entities.producto;

import com.fasterxml.jackson.annotation.JsonManagedReference;
import domain.models.Persistence;
import domain.models.entities.venta.Vendedor;
import lombok.Getter;
import lombok.Setter;
import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;


@Entity
@Table(name = "productos_personalizados")
@Setter
@Getter
public class ProductoPersonalizado extends Persistence {



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
    @OneToMany(mappedBy = "productoPersonalizado", fetch = FetchType.LAZY, cascade = {CascadeType.MERGE, CascadeType.PERSIST})
    private List<Personalizacion> personalizaciones;

    @Column(name = "precio")
    private Float precio;

    public ProductoPersonalizado() {
        super();
        personalizaciones = new ArrayList<>();
    }

    public void agregarPersonalizacion(Personalizacion personalizacion) {
        personalizaciones.add(personalizacion);
        personalizacion.setProductoPersonalizado(this);
    }

   public ProductoPersonalizado(Producto producto, Vendedor vendedor, Float precio, LocalDateTime fechaCreacion) {
        super(fechaCreacion);
        this.producto = producto;
        this.vendedor = vendedor;
        this.precio = precio;
        personalizaciones = new ArrayList<>();
    }

}
