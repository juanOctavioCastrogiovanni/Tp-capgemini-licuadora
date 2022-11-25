package domain.models.entities.venta;

import domain.models.Persona;
import domain.models.entities.producto.ProductoPersonalizado;
import lombok.Getter;
import lombok.Setter;
import javax.persistence.*;
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
    /*RELACION MUCHOS A MUCHOS lista de tipos de pagos aceptados
    @ManyToMany
    @JoinTable(
            name = "vendedores_tipos_de_pagos",
            joinColumns = @JoinColumn(name = "vendedor_id"),
            inverseJoinColumns = @JoinColumn(name = "tipo_de_pago_id")
    )
    private List<TipoDePago> tiposDePagos;
    */
}
