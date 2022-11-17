package domain.models.entities;

import lombok.Getter;
import lombok.Setter;
import javax.persistence.*;
import java.time.LocalDate;


@Entity
@Table(name = "compras")
@Setter
@Getter
public class Vendedor {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer id;

    @Column(name = "nombre")
    private String nombre;

    @Column(name = "nombreTienda")
    private String nombreTienda;

    @Column(name = "apellido")
    private String apellido;

    @Column(name = "password")
    private String password;

    @Column(name = "email")
    private String email;

    @Column(name = "dni")
    private Long dni;

    /*RELACION Lista de publicaciones*/

    /*RELACION MUCHOS A MUCHOS lista de tipos de pagos aceptados*/

}
