package domain.models.DTO;

public class ProductoPersonalizadoDTO {
    @javax.validation.constraints.NotNull
    private Integer productoId;
    @javax.validation.constraints.NotNull
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
