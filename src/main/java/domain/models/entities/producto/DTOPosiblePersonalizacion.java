package domain.models.entities.producto;

import lombok.Data;

@Data
public class DTOPosiblePersonalizacion {

    private Integer id;
    private String area;

    private String tipo;

    public DTOPosiblePersonalizacion(String area, String tipo, Integer id) {
        this.area = area;
        this.tipo = tipo;
        this.id = id;
    }

}
