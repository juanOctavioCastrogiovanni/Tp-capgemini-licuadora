package domain.models.entities.producto;

import lombok.Data;

import java.util.List;

@Data
public class DTOProductoPersonalizado {
    private Integer id;
    private String nombre;
    private Float precioBase;

    private String categoria;
    private List<DTOPosiblePersonalizacion> posiblesPersonalizaciones;

    private List<DTOPersonalizaciones> personalizaciones;

    private Float precioTotal;

    public DTOProductoPersonalizado(ProductoPersonalizado producto) {
        this.id = producto.getId();
        this.nombre = producto.getProducto().getNombre();
        this.precioBase = producto.getProducto().getPrecioBase();
        this.categoria = producto.getProducto().getCategoria().getNombre();
        this.posiblesPersonalizaciones = producto.getProducto().getPosiblesPersonalizacionesDTO();
        this.personalizaciones = producto.getPersonalizacionesDTO();
        this.precioTotal = producto.getPrecio();
    }








}
