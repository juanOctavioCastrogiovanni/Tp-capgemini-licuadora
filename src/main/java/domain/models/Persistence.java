package domain.models;

import domain.models.converters.LocalDateTimeAttributeConverter;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.time.LocalDateTime;


@MappedSuperclass
public abstract class Persistence {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Getter
    @Setter
    private Integer id;

    @Column(name = "fecha_creacion")
    @Getter
    @Setter
    @Convert(converter = LocalDateTimeAttributeConverter.class)
    protected LocalDateTime fechaCreacion;

    @Column(name = "fecha_modificacion")
    @Getter
    @Setter
    @Convert(converter = LocalDateTimeAttributeConverter.class)
    private LocalDateTime fechaModificacion;

    @Column(name = "fecha_baja")
    @Getter
    @Setter
    @Convert(converter = LocalDateTimeAttributeConverter.class)
    private LocalDateTime fechaBaja;

    public Persistence(LocalDateTime fechaCreacion) {
        this.fechaCreacion = fechaCreacion;
    }

    public Persistence() {

    }

}
