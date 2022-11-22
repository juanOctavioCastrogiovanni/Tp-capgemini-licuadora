package domain.models.entities;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;

@Entity
@Table(name = "direcciones")
@Setter
@Getter
public class Direccion {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer id;

    @Column(name = "calle")
    private String calle;

    @Column(name = "altura")
    private Integer altura;

    @Column(name = "piso")
    private Integer piso;

    @Column(name = "depto")
    private String depto;

    @Column(name = "localidad")
    private String localidad;

    @Column(name = "provincia")
    private Integer provincia;

    /*RELACION un cliente*/
    @OneToOne
    @JoinColumn(name = "cliente_id", referencedColumnName = "id")
    private Cliente cliente;


}
