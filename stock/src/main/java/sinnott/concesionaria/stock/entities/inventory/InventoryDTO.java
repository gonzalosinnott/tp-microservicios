package sinnott.concesionaria.stock.entities.inventory;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class InventoryDTO {

    @Schema(hidden = true)
    private Integer id;

    @NotNull(message = "El ID del auto no puede ser nulo")
    @Schema(example = "1")
    private Integer carId;

    @NotNull(message = "El ID de la sucursal no puede ser nulo")    
    @Schema(example = "5")
    private Integer branchId;

    @NotNull(message = "El stock no puede ser nulo")
    @Schema(example = "1")
    private Integer stock;
} 