package domain.models.entities.producto;

import domain.models.Persistence;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.time.LocalDateTime;

@Entity
@Table(name = "areas_de_personalizacion")
@Setter
@Getter
public class AreaDePersonalizacion extends Persistence {

    @Column(name = "nombre")
    private String nombre;

    //Relacion lista de posibles personalizaciones - anulo por unidireccionalidad


    public AreaDePersonalizacion() {
        super();
    }

    public AreaDePersonalizacion(String nombre, LocalDateTime fechaCreacion) {
        super(fechaCreacion);
        this.nombre = nombre;
    }
}

