package domain.models.entities;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;

@Entity
@Table(name = "areas_de_personalizacion")
@Setter
@Getter
public class AreaDePersonalizacion {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer id;

    @Column(name = "nombre")
    private Float nombre;

    /*RELACION MUCHOS A MUCHOS lista de tipos de personalizaciones*/

    /*RELACION MUCHOS A MUCHOS lista de poductos personalizados*/

}
