package domain.models.entities.producto;

import domain.models.Persistence;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.time.LocalDateTime;

@Entity
@Table(name = "tipos_de_personalizacion")
@Getter
@Setter

public class TipoDePersonalizacion extends Persistence {

    @Column(name = "nombre")
    private String nombre;

    //Relacion lista de posibles personalizaciones - anulo por unidireccionalidad

    public TipoDePersonalizacion() {

        super();
    }

    public TipoDePersonalizacion(String nombre, LocalDateTime fechaCreacion) {
        super(fechaCreacion);
        this.nombre = nombre;
    }
}
