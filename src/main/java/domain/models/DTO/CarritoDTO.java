package domain.models.DTO;

import domain.models.entities.venta.ItemCarrito;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Positive;
import java.util.List;

public class CarritoDTO {
    @NotNull
    @Positive
    private Integer carritoId;

    @NotNull
    @Positive
    private Integer publicacionId;

}
