package domain.models.entities.producto;

import domain.models.PersistenceId;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;

@Entity
@Table(name = "areas_de_personalizacion")
@Setter
@Getter
public class AreaDePersonalizacion extends PersistenceId {

    @Column(name = "nombre")
    private String nombre;

    //Relacion lista de posibles personalizaciones - anulo por unidireccionalidad


}

