package domain.models.entities.producto;

import com.fasterxml.jackson.annotation.JsonBackReference;
import domain.models.PersistenceId;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;

@Setter
@Getter
@Entity
@Table(name = "posibles_personalizaciones")
public class PosiblePersonalizacion extends PersistenceId {


    //Relacion con un area de personalizacion
    @ManyToOne
    @JoinColumn(name = "area_de_personalizacion_id", referencedColumnName = "id")
    private AreaDePersonalizacion area;

    //Relacion con un tipo de personalizacion
    @ManyToOne
    @JoinColumn(name = "tipo_de_personalizacion_id", referencedColumnName = "id")
    private TipoDePersonalizacion tipo;

    //Relacion con un producto base
    @JsonBackReference
    @ManyToOne
    @JoinColumn(name = "producto_base_id", referencedColumnName = "id")
    private Producto producto;

    //RELACION Lista de personalizaciones
    //@OneToMany(mappedBy = "posiblePersonalizacion", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    //private List<Personalizaciones> personalizaciones;




}
