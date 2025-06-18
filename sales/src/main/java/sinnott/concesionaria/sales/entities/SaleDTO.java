package sinnott.concesionaria.sales.entities;

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
public class SaleDTO {
    @Schema(hidden = true)
    private Integer id;

    @NotNull(message = "El ID del empleado no puede ser nulo")
    @Min(value = 1, message = "El ID del empleado debe ser mayor a 0")
    @Schema(example = "1")
    private Integer employeeId;

    @NotNull(message = "El ID del auto no puede ser nulo")
    @Min(value = 1, message = "El ID del auto debe ser mayor a 0")
    @Schema(example = "2")
    private Integer carId;

    @NotNull(message = "El ID del cliente no puede ser nulo")
    @Min(value = 1, message = "El ID del cliente debe ser mayor a 0")
    @Schema(example = "4")
    private Integer clientId;

    @NotNull(message = "El monto no puede ser nulo")
    @Schema(example = "25000.50")
    private Double amount;

    @NotNull(message = "La fecha no puede ser nula")
    @Schema(example = "2025-06-17")
    private LocalDate saleDate;
} 