package domain.models.entities.venta;

import domain.models.Persona;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.Entity;
import javax.persistence.Table;
import java.time.LocalDateTime;

@Entity
@Table(name = "gestores")
@Setter
@Getter
public class Gestor extends Persona {

    public Gestor() {
        super();
    }

    public Gestor(String nombre, String apellido, String dni, String email, LocalDateTime fechaCreacion) {
        super(nombre, apellido, dni, email, fechaCreacion);
    }
}
