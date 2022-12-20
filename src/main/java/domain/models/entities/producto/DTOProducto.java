package domain.models.entities.producto;

import lombok.Data;

import java.util.List;
@Data
public class DTOProducto {
    private Integer id;
    private String nombre;
    private final String color;
    private final Float precio;

    private Integer tiempoDeFabricacion;

    private String categoria;

    private String gestor;
    private List<DTOPosiblePersonalizacion> posiblePersonalizaciones;


        public DTOProducto(Producto producto) {
            this.id = producto.getId();
            this.nombre = producto.getNombre();
            this.color = producto.getColor();
            this.precio = producto.getPrecioBase();
            this.tiempoDeFabricacion = producto.getTiempoDeFabricacion();
            this.categoria = producto.getCategoria().getNombre();
            this.gestor = producto.getGestor().getNombre() + " " + producto.getGestor().getApellido();
            this.posiblePersonalizaciones = producto.getPosiblesPersonalizacionesDTO();

        }

}