package domain.models.entities;

import domain.models.PersistenceId;
import lombok.Getter;
import lombok.Setter;
import javax.persistence.*;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;


@Entity
@Table(name = "compras")
@Setter
@Getter
public class Vendedor extends PersistenceId {

    @Column(name = "nombre")
    private String nombre;

    @Column(name = "nombreTienda")
    private String nombreTienda;

    @Column(name = "apellido")
    private String apellido;

    @Column(name = "password")
    private String password;

    @Column(name = "email")
    private String email;

    @Column(name = "dni")
    private Long dni;

    /*RELACION Lista de publicaciones*/
    @OneToMany(mappedBy = "vendedor")
    private List<Publicacion> publicaciones;

    public Vendedor() {
        publicaciones = new ArrayList<>();
    }

    public void agregarPublicacion(Publicacion publicacion) {
        publicaciones.add(publicacion);
        publicacion.setVendedor(this);
    }

    /*RELACION MUCHOS A MUCHOS lista de tipos de pagos aceptados*/
    @ManyToMany
    @JoinTable(
            name = "vendedores_tipos_de_pagos",
            joinColumns = @JoinColumn(name = "vendedor_id"),
            inverseJoinColumns = @JoinColumn(name = "tipo_de_pago_id")
    )
    private List<TipoDePago> tiposDePagos;
}
