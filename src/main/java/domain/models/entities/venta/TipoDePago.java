package domain.models.entities.venta;

import com.fasterxml.jackson.annotation.JsonBackReference;
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

    /* RELACION una venta */



    public TipoDePago() {
        super();


    }

    public TipoDePago(String tipo, LocalDateTime fechaCreacion) {
        super(fechaCreacion);
        this.tipo = tipo;

    }
}
