package domain.models.entities.producto;

import domain.models.PersistenceId;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;

@Entity
@Table(name = "tipos_de_personalizacion")
@Getter
@Setter

public class TipoDePersonalizacion extends PersistenceId {

    @Column(name = "nombre")
    private String nombre;

    //Relacion lista de posibles personalizaciones - anulo por unidireccionalidad

}
