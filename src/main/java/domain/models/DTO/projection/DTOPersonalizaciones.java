package domain.models.DTO.projection;

import lombok.Data;

@Data
public class DTOPersonalizaciones {
    private Integer id;

    private DTOPosiblePersonalizacion posiblePersonalizacionElegida;

    private String contenido;

    private Float valor;

    public DTOPersonalizaciones(DTOPosiblePersonalizacion posiblePersonalizacion, String contenido, Float valor, Integer id) {
        this.posiblePersonalizacionElegida = posiblePersonalizacion;
        this.contenido = contenido;
        this.valor = valor;
        this.id = id;
    }

}
