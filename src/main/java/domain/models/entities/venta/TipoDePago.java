package domain.models.entities.venta;

import domain.models.PersistenceId;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.util.List;

@Entity
@Table(name = "tipos_de_pagos")
@Setter
@Getter
public class TipoDePago extends PersistenceId {

    @Column(name = "tipo")
    private String tipo;

    /*RELACION MUCHOS A MUCHOS Vendedores
    @ManyToMany(mappedBy = "tiposDePagos")
    private List<Vendedor> vendedores;*/

    /* RELACION una venta */
    @OneToOne
    @JoinColumn(name = "venta_id", referencedColumnName = "id")
    private Venta venta;
}
