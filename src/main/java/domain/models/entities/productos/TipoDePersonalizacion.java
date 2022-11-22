package domain.models.entities.productos;

import domain.models.PersistenceId;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "tipos_de_personalizacion")
@Getter
@Setter

public class TipoDePersonalizacion extends PersistenceId {

    @Column(name = "nombre")
    private String nombre;

    //RELACION un area
    @ManyToOne
    @JoinColumn(name = "area_id", referencedColumnName = "id")
    private AreaDePersonalizacion area;

    //RELACION muchas personalizaciones
    @OneToMany
    @JoinColumn(name = "personalizaciones_id", referencedColumnName = "id")
    private List<Personalizaciones> personalizaciones;

    public TipoDePersonalizacion() {
        personalizaciones = new ArrayList<>();
    }

    public void agregarPersonalizacion(Personalizaciones personalizacion) {
        personalizaciones.add(personalizacion);
        personalizacion.setTipoDePersonalizacion(this);
    }


}
