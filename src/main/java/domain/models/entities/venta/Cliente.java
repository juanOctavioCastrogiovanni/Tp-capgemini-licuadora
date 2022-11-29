package domain.models.entities.venta;

import domain.models.Persona;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "cliente")
@Setter
@Getter
public class Cliente extends Persona{

    @Column(name = "password")
    private String password;

    /*RELACION una direccion*/
    @OneToOne(mappedBy = "cliente")
    private Direccion direccion;

    /*RELACION Lista de ventas asociadas*/
    @OneToMany(mappedBy = "cliente")
    private List<Venta> ventas;

    public Cliente() {
        super();
        ventas = new ArrayList<>();
    }

    public void agregarVenta(Venta venta) {
        ventas.add(venta);
        venta.setCliente(this);
    }

    public Cliente(String nombre, String apellido, String dni, String email, String password, Direccion direccion, LocalDateTime fechaCreacion) {
        super(nombre, apellido, dni, email, fechaCreacion);
        this.password = password;
        this.direccion = direccion;
        ventas = new ArrayList<>();
    }


}
