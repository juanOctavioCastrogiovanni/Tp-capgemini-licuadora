package domain.models.entities.producto;

import com.fasterxml.jackson.annotation.JsonBackReference;
import domain.models.PersistenceId;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;

@Setter
@Getter
@Entity
@Table(name = "personalizaciones")
public class Personalizacion extends PersistenceId {

    //RELACION un producto personalizado - anulo porque no importa


    //RELACION lista de posibles personalizaciones
    @ManyToOne
    @JoinColumn(name = "posible_personalizacion_id", referencedColumnName = "id")
    private PosiblePersonalizacion posiblePersonalizacion;

    //atributo contenido
    @Column(name = "contenido")
    private String contenido;

    //atributo Precio
    @Column(name = "precio")
    private Float precioXPersonalizacion;

    @JsonBackReference
    @ManyToOne
    @JoinColumn(name = "producto_personalizado_id", referencedColumnName = "id")
    private ProductoPersonalizado productoPersonalizado;

}
