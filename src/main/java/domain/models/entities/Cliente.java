package domain.models.entities;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;

@Entity
@Table(name = "cliente")
@Setter
@Getter
public class Cliente {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer id;

    @Column(name = "nombre")
    private String nombre;

    @Column(name = "apellido")
    private String apellido;

    @Column(name = "email")
    private String email;

    @Column(name = "password")
    private String password;

    @Column(name = "dni")
    private Integer dni;

    @Column(name = "registrado")
    private Integer registrado;

    /*RELACION una direccion*/

    /*RELACION Lista de ventas asociadas*/


}
