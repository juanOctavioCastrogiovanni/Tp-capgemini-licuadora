package domain.models.DTO;

public class PersonalizacionDTO {
    @javax.validation.constraints.NotNull
    private Integer posiblePersonalizacionId;
    @javax.validation.constraints.NotNull
    private String contenido;
    @javax.validation.constraints.NotNull
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
