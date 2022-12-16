package domain.models.entities.producto;

import domain.models.Persistence;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.time.LocalDateTime;


@Entity
@Table(name = "categorias")
@Setter
@Getter
public class Categoria extends Persistence {

    @Column(name = "nombre")
    private String nombre;

    public Categoria() {
        super();
    }

    public Categoria(String nombre, LocalDateTime fechaCreacion) {
        super(fechaCreacion);
        this.nombre = nombre;
    }

}
