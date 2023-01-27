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
@Table(name = "posibles_personalizaciones")
public class PosiblePersonalizacion extends Persistence {


    //Relacion con un area de personalizacion
    //Relationship with an area of personalization 
    @ManyToOne
    @JoinColumn(name = "area_de_personalizacion_id", referencedColumnName = "id", nullable = false)
    private AreaDePersonalizacion area;

    //Relacion con un tipo de personalizacion
    //Relationship with a type of personalization
    @ManyToOne
    @JoinColumn(name = "tipo_de_personalizacion_id", referencedColumnName = "id", nullable = false)
    private TipoDePersonalizacion tipo;

    //Relacion con un producto base
    //Relationship with a base product
    @JsonBackReference
    @ManyToOne
    @JoinColumn(name = "producto_base_id", referencedColumnName = "id")
    private Producto producto;


    public PosiblePersonalizacion() {
        super();
    }

    public PosiblePersonalizacion(AreaDePersonalizacion area, TipoDePersonalizacion tipo, LocalDateTime fechaCreacion) {
        super(fechaCreacion);
        this.area = area;
        this.tipo = tipo;

    }


}
