package domain.models.entities.productos;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import domain.models.PersistenceId;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;


@Entity
@Table(name = "categorias")
@Setter
@Getter
public class Categoria extends PersistenceId {

    @Column(name = "nombre")
    private String nombre;

    @Column(name = "descripcion")
    private String descripcion;



    @OneToMany(mappedBy = "categoria",cascade=CascadeType.ALL, fetch=FetchType.LAZY)
    private List<Producto> productos;

    public Categoria() {
        productos = new ArrayList<>();
    }

    public void agregarProducto(Producto producto) {
        productos.add(producto);
        producto.setCategoria(this);
    }
}
