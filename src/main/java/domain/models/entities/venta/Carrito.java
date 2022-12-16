package domain.models.entities.venta;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import domain.models.entities.producto.Categoria;
import lombok.Getter;
import lombok.Setter;
import javax.persistence.*;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import domain.models.Persistence;


@Entity
@Table(name = "carritos")
@Setter
@Getter
public class Carrito extends Persistence {

    @Column(name = "precioTotal")
    private Float precioTotal;


    /*RELACION Lista de carritos (publicaciones)*/
    @JsonManagedReference
    @OneToMany(mappedBy = "carrito", cascade = {CascadeType.MERGE, CascadeType.PERSIST}, fetch = FetchType.LAZY)
    private List<ItemCarrito> items;

    public Carrito() {
        super();
        this.items = new ArrayList<>();
        this.precioTotal = 0f;
    }

    public void agregarItemCarrito(ItemCarrito itemCarrito) {
        items.add(itemCarrito);
        itemCarrito.setCarrito(this);
    }

    public Carrito(LocalDateTime fechaCreacion) {
        super(fechaCreacion);
        items = new ArrayList<>();
        this.precioTotal = 0f;
    }

    public void calcularTotalCarrito(){
        Float total = 0F;
        for (ItemCarrito item: items) {
            total += item.getSubTotal();
        }
        this.precioTotal=total;
    }

 public void removerItemCarrito(ItemCarrito itemARemover){
        items.remove(itemARemover);
        itemARemover.setCarrito(null);
 }



}
