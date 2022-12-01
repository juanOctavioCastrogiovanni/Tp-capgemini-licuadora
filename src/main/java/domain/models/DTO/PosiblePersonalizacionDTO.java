package domain.models.DTO;

public class PosiblePersonalizacionDTO {
    @javax.validation.constraints.NotNull
    private Integer areaId;
    @javax.validation.constraints.NotNull
    private Integer tipoId;

    public Integer getAreaId() {
        return areaId;
    }

    public void setAreaId(Integer areaId) {
        this.areaId = areaId;
    }

    public Integer getTipoId() {
        return tipoId;
    }

    public void setTipoId(Integer tipoId) {
        this.tipoId = tipoId;
    }

}
