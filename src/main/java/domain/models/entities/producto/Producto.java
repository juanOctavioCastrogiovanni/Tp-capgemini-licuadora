package domain.models.entities.producto;

import com.fasterxml.jackson.annotation.JsonManagedReference;
import domain.models.Persistence;
import domain.models.entities.venta.Gestor;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;


@Entity
@Table(name = "productos")
@Setter
@Getter
public class Producto extends Persistence {

    @Column(name = "nombre")
    private String nombre;

    @Column(name = "color")
    private String color;

    @Column(name = "precio")
    private Float precioBase;

    @Column(name = "TiempoDeFabricacion")
    private Integer tiempoDeFabricacion;

    //no utilizo cascada, no necesito crear o modificar categorias, solo asigarlas
    @ManyToOne
    @JoinColumn(name = "categoria_id", referencedColumnName = "id", nullable = false)
    private Categoria categoria;

    //Relacion lista de posibles personalizaciones

    @JsonManagedReference
    @OneToMany(mappedBy = "producto", fetch = FetchType.LAZY, cascade = {CascadeType.MERGE, CascadeType.PERSIST})
    private List<PosiblePersonalizacion> posiblesPersonalizaciones;

    //RELACION lista de personalizaciones - anulo por unidireccionalidad
    //@OneToMany(mappedBy = "producto", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    //private List<ProductoPersonalizado> productosPersonalizados;

    //RELACION a un gestor
    @ManyToOne
    @JoinColumn(name = "gestor_id", referencedColumnName = "id", nullable = false)
    private Gestor gestor;



    public Producto() {
        super();
        this.posiblesPersonalizaciones = new ArrayList<>();

    }

    public void agregarPosiblesPersonalizaciones(PosiblePersonalizacion posiblesPersonalizaciones) {
        this.posiblesPersonalizaciones.add(posiblesPersonalizaciones);
        posiblesPersonalizaciones.setProducto(this);
    }

    public Producto(String nombre, String color, Float precioBase, Integer tiempoDeFabricacion, Categoria categoria, Gestor gestor, LocalDateTime fechaCreacion) {
        super(fechaCreacion);
        this.nombre = nombre;
        this.color = color;
        this.precioBase = precioBase;
        this.tiempoDeFabricacion = tiempoDeFabricacion;
        this.categoria = categoria;
        this.gestor = gestor;
        this.posiblesPersonalizaciones = new ArrayList<>();
    }

}
