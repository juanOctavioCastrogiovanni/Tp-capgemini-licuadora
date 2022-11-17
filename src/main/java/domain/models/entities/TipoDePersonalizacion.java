package domain.models.entities;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;

@Entity
@Table(name = "tipos_de_personalizacion")
@Setter
@Getter
public class TipoDePersonalizacion {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer id;

    @Column(name = "nombre")
    private Float nombre;

    @Column(name = "precio")
    private Float precio;

    /*RELACION MUCHOS A MUCHOS lista de areas de personalizaciones*/



}
