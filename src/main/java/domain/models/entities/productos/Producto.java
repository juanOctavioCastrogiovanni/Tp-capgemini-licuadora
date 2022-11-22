package domain.models.entities.productos;

import com.fasterxml.jackson.annotation.JsonManagedReference;
import domain.models.PersistenceId;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;


@Entity
@Table(name = "productos")
@Setter
@Getter
public class Producto extends PersistenceId {

    @Column(name = "nombre")
    private String nombre;

    @Column(name = "color")
    private String color;

    @Column(name = "precio")
    private Float precioBase;


    /*RELACION Una categoria */

    @ManyToOne
    @JoinColumn(name = "categoria_id", referencedColumnName = "id")
    private Categoria categoria;

    /*RELACION Lista de Areas */

    @OneToMany(mappedBy = "producto",cascade=CascadeType.ALL, fetch=FetchType.LAZY)
    private List<AreaDePersonalizacion> areas;

    //RELACION muchos productos personalizados
    @OneToMany(mappedBy = "producto")
    private List<ProductoPersonalizado> productosPersonalizados;

    public Producto() {
        areas = new ArrayList<>();
        productosPersonalizados = new ArrayList<>();
    }

    public void agregarArea(AreaDePersonalizacion area) {
        areas.add(area);
        area.setProducto(this);
    }

    public void agregarProductoPersonalizado(ProductoPersonalizado productoPersonalizado) {
        productosPersonalizados.add(productoPersonalizado);
        productoPersonalizado.setProducto(this);
    }



}
