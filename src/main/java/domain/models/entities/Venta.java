package domain.models.entities;

import lombok.Getter;
import lombok.Setter;
import javax.persistence.*;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.List;


@Entity
@Table(name = "ventas")
@Setter
@Getter
public class Venta {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer id;


    @Column(name = "fecha_de_venta", columnDefinition = "DATE")
    private LocalDate fecha;

    @Column(name = "hora_de_venta", columnDefinition = "TIME")
    private LocalTime hora;

    @Column(name = "precioTotal")
    private Float precioTotal;

    @Column(name = "estado")
    private String estado;

    /*RELACION un Tipo de pago*/
    @OneToOne(mappedBy = "venta")
    private TipoDePago tipoDePago;

    /*RELACION un cliente*/
    @ManyToOne
    @JoinColumn(name = "cliente_id", referencedColumnName = "id")
    private Cliente cliente;

    /*RELACION Lista de carritos (publicaciones)*/
    @OneToMany(mappedBy = "venta")
    private List<Carrito> carritos;

    public Venta() {
        carritos = new ArrayList<>();
    }

    public void agregarCarrito(Carrito carrito) {
        carritos.add(carrito);
        carrito.setVenta(this);
    }

}
