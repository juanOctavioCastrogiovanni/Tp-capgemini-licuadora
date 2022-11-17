package domain.models.entities;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;

@Entity
@Table(name = "tipos_de_pagos")
@Setter
@Getter
public class TipoDePago {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer id;

    @Column(name = "tipo")
    private String tipo;

    @Column(name = "clave")
    private Integer clave;

    @Column(name = "fecha_de_vencimiento")
    private String fecha;

    /*RELACION MUCHOS A MUCHOS Vendedores*/

    /* RELACION una venta */

}
