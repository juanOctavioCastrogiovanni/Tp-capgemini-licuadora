package domain.models.DTO;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Positive;

public class PersonalizacionDTO {
    @NotNull
    private Integer posiblePersonalizacionId;

    @NotNull
    @NotBlank
    private String contenido;

    @NotNull
    @Positive
    private Float precioPersonalizacion;

    public Integer getPosiblePersonalizacionId() {
        return posiblePersonalizacionId;
    }

    public void setPosiblePersonalizacionId(Integer posiblePersonalizacionId) {
        this.posiblePersonalizacionId = posiblePersonalizacionId;
    }

    public String getContenido() {
        return contenido;
    }

    public void setContenido(String contenido) {
        this.contenido = contenido;
    }

    public Float getPrecioPersonalizacion() {
        return precioPersonalizacion;
    }

    public void setPrecioPersonalizacion(Float precioPersonalizacion) {
        this.precioPersonalizacion = precioPersonalizacion;
    }
}
