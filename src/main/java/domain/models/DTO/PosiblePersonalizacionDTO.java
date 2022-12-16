package domain.models.DTO;

import javax.validation.constraints.NotNull;

public class PosiblePersonalizacionDTO {
    @NotNull
    private Integer areaId;
    @NotNull
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
