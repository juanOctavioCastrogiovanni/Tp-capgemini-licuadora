package domain.models.DTO;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Positive;


public class VentaDTO {
    @NotNull
    private Integer pagoId;

    @NotNull
    private Integer clienteId;

    @NotNull
    private Integer direccionId;

    public Integer getPagoId() {
        return pagoId;
    }

    public void setPagoId(Integer pagoId) {
        this.pagoId = pagoId;
    }

    public Integer getClienteId() {
        return clienteId;
    }

    public void setClienteId(Integer clienteId) {
        this.clienteId = clienteId;
    }

    public Integer getDireccionId() {
        return direccionId;
    }

    public void setDireccionId(Integer direccionId) {
        this.direccionId = direccionId;
    }
}
