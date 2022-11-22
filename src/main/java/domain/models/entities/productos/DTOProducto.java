package domain.models.entities.productos;


import domain.models.entities.productos.Producto;
import org.springframework.data.rest.core.config.Projection;

@Projection(name = "dtoproducto", types = { Producto.class })
public interface DTOProducto {

    String getNombre();
    String getColor();
    String getPrecioBase();


}
