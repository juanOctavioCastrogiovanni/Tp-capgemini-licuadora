package domain.models.entities.venta;

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
@Table(name = "ventas")
@Setter
@Getter
public class Venta extends Persistence {

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
    @ManyToOne
    @JoinColumn(name = "cliente_id", referencedColumnName = "id")
    private Cliente cliente;

    /*RELACION Lista de carritos (publicaciones)*/
    @OneToMany(mappedBy = "venta")
    private List<Carrito> carritos;

    public Venta() {
        super();
        carritos = new ArrayList<>();
    }

    public void agregarCarrito(Carrito carrito) {
        carritos.add(carrito);
        carrito.setVenta(this);
    }

    public Venta(LocalDate fecha, LocalTime hora, Float precioTotal, String estado, TipoDePago tipoDePago, Cliente cliente, LocalDateTime fechaCreacion) {
        super(fechaCreacion);
        this.fecha = fecha;
        this.hora = hora;
        this.precioTotal = precioTotal;
        this.estado = estado;
        this.pago = tipoDePago;
        this.cliente = cliente;
        carritos = new ArrayList<>();
    }

}
