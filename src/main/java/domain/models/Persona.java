package domain.models;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.time.LocalDateTime;

@MappedSuperclass
@Setter
@Getter
public class Persona extends Persistence {

    @Column(name = "nombre")
    private String nombre;

    @Column(name = "apellido")
    private String apellido;

    @Column(name = "dni")
    private String dni;

    @Column(name = "email")
    private String email;

    public Persona() {
        super();
    }

    public Persona(String nombre, String apellido, String dni, String email, LocalDateTime fechaCreacion) {
        super(fechaCreacion);
        this.nombre = nombre;
        this.apellido = apellido;
        this.dni = dni;
        this.email = email;
    }

}
