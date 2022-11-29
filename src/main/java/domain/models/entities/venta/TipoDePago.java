package domain.models.entities.venta;

import domain.models.Persistence;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "tipos_de_pagos")
@Setter
@Getter
public class TipoDePago extends Persistence {

    @Column(name = "tipo")
    private String tipo;

    //RELACION MUCHOS A MUCHOS Vendedores
    @ManyToMany(mappedBy = "tiposDePagos")
    private List<Vendedor> vendedores;

    /* RELACION una venta */


    public TipoDePago() {
        super();
        vendedores = new ArrayList<>();

    }

    public TipoDePago(String tipo, LocalDateTime fechaCreacion) {
        super(fechaCreacion);
        this.tipo = tipo;
        vendedores = new ArrayList<>();
    }
}
