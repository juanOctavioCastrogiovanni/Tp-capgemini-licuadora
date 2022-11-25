package domain.models.entities.producto;

import domain.models.PersistenceId;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;


@Entity
@Table(name = "categorias")
@Setter
@Getter
public class Categoria extends PersistenceId {

    @Column(name = "nombre")
    private String nombre;

    @Column(name = "descripcion")
    private String descripcion;
}
