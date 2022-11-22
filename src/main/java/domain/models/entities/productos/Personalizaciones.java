package domain.models.entities.productos;

import domain.models.PersistenceId;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;

@Setter
@Getter
@Entity
@Table(name = "personalizaciones")
public class Personalizaciones extends PersistenceId {

    //RELACION un producto personalizado
    @ManyToOne
    @JoinColumn(name = "producto_personalizado_id", referencedColumnName = "id")
    private ProductoPersonalizado productoPersonalizado;

    //RELACION un tipo de personalizacion
    @ManyToOne
    @JoinColumn(name = "tipo_de_personalizacion_id", referencedColumnName = "id")
    private TipoDePersonalizacion tipoDePersonalizacion;





}
