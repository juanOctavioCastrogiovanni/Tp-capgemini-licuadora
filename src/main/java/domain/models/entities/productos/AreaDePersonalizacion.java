package domain.models.entities.productos;

import domain.models.PersistenceId;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "areas_de_personalizacion")
@Setter
@Getter
public class AreaDePersonalizacion extends PersistenceId {

    @Column(name = "nombre")
    private String nombre;

    //RELACION un producto
    @ManyToOne
    @JoinColumn(name = "producto_id", referencedColumnName = "id")
    private Producto producto;

    //RELACION muchos tipos de personalizacion
    @OneToMany(mappedBy = "area",cascade=CascadeType.ALL, fetch=FetchType.LAZY)
    private List<TipoDePersonalizacion> tipos;

    //RELACION muchas personalizaciones


    public AreaDePersonalizacion() {
        tipos = new ArrayList<>();
    }

    public void agregarTipo(TipoDePersonalizacion tipo) {
        tipos.add(tipo);
        tipo.setArea(this);
    }
}

