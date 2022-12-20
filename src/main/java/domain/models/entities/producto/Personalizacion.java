package domain.models.entities.producto;

import com.fasterxml.jackson.annotation.JsonBackReference;
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
    @ManyToOne
    @JoinColumn(name = "posible_personalizacion_id", referencedColumnName = "id", nullable = false)
    private PosiblePersonalizacion posiblePersonalizacion;

    //atributo contenido
    @Column(name = "contenido")
    private String contenido;

    //atributo Precio
    @Column(name = "precio")
    private Float precioXPersonalizacion;

    @JsonBackReference
    @ManyToOne
    @JoinColumn(name = "producto_personalizado_id", referencedColumnName = "id")
    private ProductoPersonalizado productoPersonalizado;

    public Personalizacion() {
        super();
    }

    public Personalizacion(PosiblePersonalizacion posiblePersonalizacion, String contenido, Float precioXPersonalizacion, LocalDateTime fechaCreacion) {
        super(fechaCreacion);
        this.posiblePersonalizacion = posiblePersonalizacion;
        this.contenido = contenido;
        this.precioXPersonalizacion = precioXPersonalizacion;
    }

    public DTOPosiblePersonalizacion getPosiblePersonalizacion() {
        return new DTOPosiblePersonalizacion(posiblePersonalizacion.getArea().getNombre(), posiblePersonalizacion.getTipo().getNombre(), posiblePersonalizacion.getId());
    }
}
