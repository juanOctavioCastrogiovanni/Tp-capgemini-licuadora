package domain.models.entities;

import domain.models.EstadoPublicacion;
import domain.models.PersistenceId;
import domain.models.entities.productos.ProductoPersonalizado;
import lombok.Getter;
import lombok.Setter;
import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;


@Entity
@Table(name = "publicaciones")
@Setter
@Getter
public class Publicacion extends PersistenceId {


    @Column(name = "nombre")
    private String nombre;

    @Column(name = "imagen")
    private String imagen;

    @Column(name = "stock")
    private Integer stock;

    @Enumerated(EnumType.STRING)
    @Column(name = "estado")
    private EstadoPublicacion estado;

    /*RELACION un vendedor*/
    @ManyToOne
    @JoinColumn(name = "vendedor_id", referencedColumnName = "id")
    private Vendedor vendedor;

    /*RELACION un producto personalizado*/
    @OneToOne(mappedBy = "publicacion")
    private ProductoPersonalizado productoPersonalizado;

    /* RELACION Lista de carritos (ventas) */
    @OneToMany(mappedBy = "publicacion")
    private List<Carrito> carritos;

    public Publicacion() {
        carritos = new ArrayList<>();
    }

    public void agregarCarrito(Carrito carrito) {
        carritos.add(carrito);
        carrito.setPublicacion(this);
    }
}

