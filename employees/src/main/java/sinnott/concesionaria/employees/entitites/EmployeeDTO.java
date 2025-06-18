package sinnott.concesionaria.employees.entitites;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import lombok.Data;
import sinnott.concesionaria.employees.entitites.enums.EmployeeRole;

@Data
public class EmployeeDTO {
    
    @Schema(hidden = true)
    private Integer id;

    @NotEmpty(message = "El nombre no puede estar vacío")
    @Schema(example = "Pedro")
    private String name;

    @NotEmpty(message = "El apellido no puede estar vacío")
    @Schema(example = "Castaño")
    private String lastName;

    @NotNull(message = "El ID de la persona no puede ser nulo")
    @Min(value = 1, message = "El ID de la persona debe ser mayor a 0")
    @Schema(example = "1111")
    private Integer identityId;

    @NotNull(message = "El rol no puede ser nulo")
    @Schema(example = "SELLER")
    private EmployeeRole role;

    @NotNull(message = "El ID de la sucursal no puede ser nulo")
    @Min(value = 1, message = "El ID de la sucursal debe ser mayor a 0")
    @Schema(example = "1")
    private Integer branchId;

    public EmployeeDTO(Integer id, String name, String lastName, 
                       Integer identityId, EmployeeRole role, Integer branchId) {
        this.id = id;
        this.name = name;
        this.lastName = lastName;
        this.identityId = identityId;
        this.role = role;
        this.branchId = branchId;
    }

}
