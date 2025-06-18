package sinnott.concesionaria.repairs.entities;

import java.time.LocalDate;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class RepairDTO {
    
    @Schema(hidden = true)
    private Integer id;
    
    @NotNull(message = "El ID del empleado no puede ser nulo")
    @Min(value = 1, message = "El ID del empleado debe ser mayor a 0")
    @Schema(example = "1")
    private Integer employeeId;
    
    @NotNull(message = "El ID del cliente no puede ser nulo")
    @Min(value = 1, message = "El ID del cliente debe ser mayor a 0")
    @Schema(example = "1")
    private Integer clientId;
    
    @NotNull(message = "El ID de la venta no puede ser nulo")
    @Min(value = 1, message = "El ID de la venta debe ser mayor a 0")
    @Schema(example = "1")
    private Integer saleId;
    
    @NotNull(message = "El kilometraje del vehículo no puede ser nulo")
    @Schema(example = "10000.0")
    private Double vehicleKm;
    
    @NotNull(message = "La fecha de reparación no puede ser nula")
    @Schema(example = "2024-01-10")
    private LocalDate repairDate;
}
