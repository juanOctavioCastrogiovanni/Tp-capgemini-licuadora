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
import java.util.stream.Collectors;


@Entity
@Table(name = "productos_personalizados")
@Setter
@Getter
public class ProductoPersonalizado extends Persistence {



    /*RELACION publicacion a sociada - anulo porque no importa*/


    /*RELACION un producto*/
    @ManyToOne
    @JoinColumn(name = "producto_base_id", referencedColumnName = "id", nullable = false)
    private Producto producto;

    /*RELACION un vendedor*/

    @ManyToOne
    @JoinColumn(name = "vendedor_id", referencedColumnName = "id", nullable = false)
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

    public List<Personalizacion> getPersonalizaciones() {
        return new ArrayList<>(personalizaciones.stream().filter(p -> p.getFechaBaja() == null).collect(Collectors.toList()));
    }

    public List<DTOPersonalizaciones> getPersonalizacionesDTO() {
        return new ArrayList<>(personalizaciones.stream().filter(p -> p.getFechaBaja() == null).map(p -> new DTOPersonalizaciones(p.getPosiblePersonalizacion(), p.getContenido(), p.getPrecioXPersonalizacion(), p.getId() )).collect(Collectors.toList()));
    }

    public List<Personalizacion> obtenerPersonalizaciones(){
        return new ArrayList<>(personalizaciones);
    }

    public void quitarPersonalizacion(Personalizacion personalizacion) {
        personalizaciones.remove(personalizacion);
        personalizacion.setProductoPersonalizado(null);
    }

   public ProductoPersonalizado(Producto producto, Vendedor vendedor, Float precio, LocalDateTime fechaCreacion) {
        super(fechaCreacion);
        this.producto = producto;
        this.vendedor = vendedor;
        this.precio = precio;
        personalizaciones = new ArrayList<>();
    }

}
