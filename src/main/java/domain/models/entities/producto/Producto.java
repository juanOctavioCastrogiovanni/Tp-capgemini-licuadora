package domain.models.entities.producto;

import com.fasterxml.jackson.annotation.JsonManagedReference;
import domain.models.DTO.projection.DTOPosiblePersonalizacion;
import domain.models.Persistence;
import domain.models.entities.venta.Gestor;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;


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

    @ManyToOne
    @JoinColumn(name = "categoria_id", referencedColumnName = "id", nullable = false)
    private Categoria categoria;

    //Relacion lista de posibles personalizaciones
    //Relationship with a list of possible personalizations

    @JsonManagedReference
    @OneToMany(mappedBy = "producto", fetch = FetchType.LAZY, cascade = {CascadeType.MERGE, CascadeType.PERSIST})
    private List<PosiblePersonalizacion> posiblesPersonalizaciones;

    //RELACION lista de personalizaciones - anulo por unidireccionalidad
    //Relationship with a list of personalizations - I cancel for unidirectional
    //@OneToMany(mappedBy = "producto", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    //private List<ProductoPersonalizado> productosPersonalizados;

    //RELACION a un gestor
    //Relationship with a manager
    @ManyToOne
    @JoinColumn(name = "gestor_id", referencedColumnName = "id", nullable = false)
    private Gestor gestor;

    public Producto() {
        super();
        this.posiblesPersonalizaciones = new ArrayList<>();

    }

    //Agregar posibles personalizaciones a la lista de posibles personalizaciones y hago una relacion en objetos donde esa posible personalizacion
    // agregada pertenece a este producto
    
    //Add possible personalizations to the list of possible personalizations and I make an object relationship where that added possible personalization
    // belongs to this product
    public void agregarPosiblesPersonalizaciones(PosiblePersonalizacion posiblesPersonalizaciones) {
        this.posiblesPersonalizaciones.add(posiblesPersonalizaciones);
        posiblesPersonalizaciones.setProducto(this);
    }

    // Devuelvo una lista de posibles personalizaciones filtradas por fecha de baja == null
    // Return a list of possible personalizations filtered by date of null discharge
    public List<PosiblePersonalizacion> getPosiblesPersonalizaciones(){
        return new ArrayList<>(posiblesPersonalizaciones.stream().filter(p -> p.getFechaBaja() == null).collect(Collectors.toList()));
    }

    // Devuelvo una lista de posibles personalizaciones filtradas por fecha de baja == null pero con formato DTO
    // Return a list of possible personalizations filtered by date of null discharge but with DTO format
    public List<DTOPosiblePersonalizacion> getPosiblesPersonalizacionesDTO(){
        return new ArrayList<>(posiblesPersonalizaciones.stream().filter(p -> p.getFechaBaja() == null).map(p -> new DTOPosiblePersonalizacion(p.getArea().getNombre(),p.getTipo().getNombre(),p.getId())).collect(Collectors.toList()));
    }

    // Obtengo una lista de posibles personalizaciones pero una copia.
    // Get a list of possible personalizations but a copy.
    public List<PosiblePersonalizacion> obtenerPosiblesPersonalizaciones() {
        return new ArrayList<>(posiblesPersonalizaciones);
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
