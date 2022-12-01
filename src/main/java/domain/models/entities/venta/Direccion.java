package domain.models.entities.venta;

import lombok.Getter;
import lombok.Setter;
import domain.models.Persistence;
import javax.persistence.*;
import java.time.LocalDateTime;

@Entity
@Table(name = "direcciones")
@Setter
@Getter
public class Direccion extends Persistence {

    @Column(name = "calle")
    private String calle;

    @Column(name = "altura")
    private String altura;

    @Column(name = "piso")
    private String piso;

    @Column(name = "depto")
    private String depto;

    @Column(name = "localidad")
    private String localidad;

    @Column(name = "provincia")
    private String provincia;

    /*RELACION un cliente*/
    @OneToOne
    @JoinColumn(name = "cliente_id", referencedColumnName = "id")
    private Cliente cliente;

    public Direccion() {
        super();
    }

    public Direccion(String calle, String altura, String piso, String depto, String localidad, String provincia, Cliente cliente, LocalDateTime fechaCreacion) {
        super(fechaCreacion);
        this.calle = calle;
        this.altura = altura;
        this.piso = piso;
        this.depto = depto;
        this.localidad = localidad;
        this.provincia = provincia;
        this.cliente = cliente;
    }


}
