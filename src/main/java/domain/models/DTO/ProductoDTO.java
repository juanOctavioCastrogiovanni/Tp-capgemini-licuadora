package domain.models.DTO;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Positive;

public class ProductoDTO {
    @NotNull
    @NotBlank
    private String nombre;
    @NotNull
    @NotBlank
    private String color;
    @NotNull
    @Positive

    private Float precio;
    @NotNull
    @Positive

    private Integer tiempoDeFabricacion;

    @NotNull
    @Positive
    private Integer categoriaId;
    @NotNull
    @Positive
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
