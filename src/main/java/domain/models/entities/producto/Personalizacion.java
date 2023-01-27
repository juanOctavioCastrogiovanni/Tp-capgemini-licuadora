package domain.models.entities.producto;

import com.fasterxml.jackson.annotation.JsonBackReference;
import domain.models.DTO.projection.DTOPosiblePersonalizacion;
import domain.models.Persistence;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.time.LocalDateTime;

@Setter
@Getter
@Entity
@Table(name = "personalizaciones")
public class Personalizacion extends Persistence {

    //RELACION un producto personalizado - anulo porque no importa


    //RELACION lista de posibles personalizaciones
    //Relationship: PosiblePersonalizacion - Personalizacion
    @ManyToOne
    @JoinColumn(name = "posible_personalizacion_id", referencedColumnName = "id", nullable = false)
    private PosiblePersonalizacion posiblePersonalizacion;

    //atributo contenido
    @Column(name = "contenido")
    private String contenido;

    //atributo Precio
    @Column(name = "precio")
    private Float precioXPersonalizacion;

    //RELACION un producto personalizado
    //Relationship: ProductoPersonalizado - Personalizacion
    @JsonBackReference
    @ManyToOne
    @JoinColumn(name = "producto_personalizado_id", referencedColumnName = "id")
    private ProductoPersonalizado productoPersonalizado;

    // El constructor llama a su clase padre Persistence que tiene metodos y atributos que lo corpanten 
    // the constructor calls its parent class Persistence which has methods and attributes that it contains
    public Personalizacion() {
        super();
    }

    // Este metodo permite en el bean de la clase principal de la aplicacion cargar una nueva personalizacion a la base de datos pasandole como parametros 
    // posible personalizacion, contenido, precio y fecha de creacion.

    // This method allows in the main class bean of the application to load a new customization to the database by passing as parameters
    // possible customization, content, price and creation date.

    public Personalizacion(PosiblePersonalizacion posiblePersonalizacion, String contenido, Float precioXPersonalizacion, LocalDateTime fechaCreacion) {
        super(fechaCreacion);
        this.posiblePersonalizacion = posiblePersonalizacion;
        this.contenido = contenido;
        this.precioXPersonalizacion = precioXPersonalizacion;
    }

    // Este metodo devuelve una nueva instancia DTO 
    // This method returns a new DTO instance
    public DTOPosiblePersonalizacion getPosiblePersonalizacion() {
        return new DTOPosiblePersonalizacion(posiblePersonalizacion.getArea().getNombre(), posiblePersonalizacion.getTipo().getNombre(), posiblePersonalizacion.getId());
    }
}
