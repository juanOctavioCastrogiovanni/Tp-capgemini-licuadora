package domain.models.entities.venta;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import lombok.Getter;
import lombok.Setter;
import javax.persistence.*;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.List;
import domain.models.Persistence;


@Entity
@Table(name = "carritos")
@Setter
@Getter
public class Carrito extends Persistence {

    @Column(name = "fecha_de_venta", columnDefinition = "DATE")
    private LocalDate fecha;

    @Column(name = "hora_de_venta", columnDefinition = "TIME")
    private LocalTime hora;

    @Column(name = "precioTotal")
    private Float precioTotal;

    @Column(name = "estado")
    private String estado;

    /*RELACION un Tipo de pago*/

    @OneToOne
    @JoinColumn(name = "venta_id", referencedColumnName = "id")
    private TipoDePago pago;

    /*RELACION un cliente*/
    @JsonBackReference
    @ManyToOne
    @JoinColumn(name = "cliente_id", referencedColumnName = "id")
    private Cliente cliente;

    /*RELACION Lista de carritos (publicaciones)*/
    @JsonManagedReference
    @OneToMany(mappedBy = "carrito")
    private List<ItemCarrito> itemCarritos;

    public Carrito() {
        super();
        itemCarritos = new ArrayList<>();
    }

    public void agregarItemCarrito(ItemCarrito itemCarrito) {
        itemCarritos.add(itemCarrito);
        itemCarrito.setCarrito(this);
    }

    public Carrito(LocalDate fecha, LocalTime hora, Float precioTotal, String estado, TipoDePago tipoDePago, Cliente cliente, LocalDateTime fechaCreacion) {
        super(fechaCreacion);
        this.fecha = fecha;
        this.hora = hora;
        this.precioTotal = precioTotal;
        this.estado = estado;
        this.pago = tipoDePago;
        this.cliente = cliente;
        itemCarritos = new ArrayList<>();
    }

}
