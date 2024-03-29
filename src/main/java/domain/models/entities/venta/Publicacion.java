package domain.models.entities.venta;

import domain.models.enums.EstadoPublicacion;
import domain.models.Persistence;
import domain.models.entities.producto.ProductoPersonalizado;
import lombok.Getter;
import lombok.Setter;
import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;


@Entity
@Table(name = "publicaciones")
@Setter
@Getter
public class Publicacion extends Persistence {


    @Column(name = "nombre")
    private String nombre;

    @Column(name = "categoria")
    private String categoria;

    @Column(name = "vendedor")
    private String vendedor;

    @Column(name = "imagen")
    private String imagen;

    @Column(name = "stock")
    private Integer stock;

    @Enumerated(EnumType.STRING)
    @Column(name = "estado")
    private EstadoPublicacion estado;



    /*RELACION un producto personalizado - anulo bidireccionalidad*/
    @OneToOne
    @JoinColumn(name = "producto_personalizado_id", referencedColumnName = "id", nullable = false)
    private ProductoPersonalizado productoPersonalizado;

    /* RELACION Lista de carritos (ventas) */

    public Publicacion() {
        super();
    }


    public Publicacion(String nombre, String categoria, String vendedor, String imagen, Integer stock, EstadoPublicacion estado, ProductoPersonalizado productoPersonalizado, LocalDateTime fechaCreacion) {
        super(fechaCreacion);
        this.nombre = nombre;
        this.categoria = categoria;
        this.vendedor = vendedor;
        this.imagen = imagen;
        this.stock = stock;
        this.estado = estado;
        this.productoPersonalizado = productoPersonalizado;
    }


}

