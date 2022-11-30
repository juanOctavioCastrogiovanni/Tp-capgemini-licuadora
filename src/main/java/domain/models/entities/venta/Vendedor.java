package domain.models.entities.venta;

import com.fasterxml.jackson.annotation.JsonManagedReference;
import domain.models.Persona;
import lombok.Getter;
import lombok.Setter;
import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;


@Entity
@Table(name = "vendedor")
@Setter
@Getter
public class Vendedor extends Persona {

    @Column(name = "nombreTienda")
    private String nombreTienda;

    @Column(name = "logo")
    private String logo;

    /*RELACION Lista de publicaciones*/
    /*@OneToMany(mappedBy = "vendedor")
    private List<ProductoPersonalizado> publicaciones;

    public Vendedor() {
        publicaciones = new ArrayList<>();
    }

    public void agregarPublicacion(ProductoPersonalizado productoPer) {
        publicaciones.add(productoPer);
        productoPer.setVendedor(this);
    }
*/
    //RELACION MUCHOS A MUCHOS lista de tipos de pagos aceptados

    @JsonManagedReference
    @ManyToMany
    @JoinTable(
            name = "vendedores_tipos_de_pagos",
            joinColumns = @JoinColumn(name = "vendedor_id"),
            inverseJoinColumns = @JoinColumn(name = "tipo_de_pago_id")
    )
    private List<TipoDePago> tiposDePagos;

    public void agregarTipoDePago(TipoDePago tipoDePago){
        tiposDePagos.add(tipoDePago);
        tipoDePago.getVendedores().add(this);
    }


    public Vendedor() {
        super();
        tiposDePagos = new ArrayList<>();
    }

    public Vendedor(String nombreTienda, String logo, String nombre, String apellido, String email, String dni, LocalDateTime fechaCreacion) {
        super(nombre, apellido, dni, email, fechaCreacion);
        this.nombreTienda = nombreTienda;
        this.logo = logo;
        tiposDePagos = new ArrayList<>();
    }
}
