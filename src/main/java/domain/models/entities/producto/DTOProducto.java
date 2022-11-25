package domain.models.entities.producto;


import org.springframework.data.rest.core.config.Projection;

@Projection(name = "dtoproducto1", types = { Producto.class })
public interface DTOProducto {
    String getNombre();
    String getColor();
    String getPrecioBase();
}
