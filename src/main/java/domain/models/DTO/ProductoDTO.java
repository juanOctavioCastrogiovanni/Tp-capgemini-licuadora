package domain.models.DTO;

public class ProductoDTO {
    @javax.validation.constraints.NotNull
    private String nombre;

    @javax.validation.constraints.NotNull
    private String color;
    @javax.validation.constraints.NotNull
    private Float precio;
    @javax.validation.constraints.NotNull
    private Integer tiempoDeFabricacion;
    @javax.validation.constraints.NotNull
    private Integer categoriaId;
    @javax.validation.constraints.NotNull
    private Integer gestorId;

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getColor() {
        return color;
    }

    public void setColor(String color) {
        this.color = color;
    }

    public Float getPrecio() {
        return precio;
    }

    public void setPrecio(Float precio) {
        this.precio = precio;
    }

    public Integer getTiempoDeFabricacion() {
        return tiempoDeFabricacion;
    }

    public void setTiempoDeFabricacion(Integer tiempoDeFabricacion) {
        this.tiempoDeFabricacion = tiempoDeFabricacion;
    }

    public Integer getCategoriaId() {
        return categoriaId;
    }

    public void setCategoriaId(Integer categoriaId) {
        this.categoriaId = categoriaId;
    }

    public Integer getGestorId() {
        return gestorId;
    }

    public void setGestorId(Integer gestorId) {
        this.gestorId = gestorId;
    }
}
