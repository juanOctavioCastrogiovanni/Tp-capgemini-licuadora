package domain.models.DTO;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

public class ProductoPersonalizadoDTO {
    @NotNull
    private Integer productoId;
    @NotNull
    private Integer vendedorId;

    public Integer getProductoId() {
        return productoId;
    }

    public void setProductoId(Integer productoId) {
        this.productoId = productoId;
    }

    public Integer getVendedorId() {
        return vendedorId;
    }

    public void setVendedorId(Integer vendedorId) {
        this.vendedorId = vendedorId;
    }
}
