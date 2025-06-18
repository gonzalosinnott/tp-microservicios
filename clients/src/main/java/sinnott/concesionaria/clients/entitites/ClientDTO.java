package sinnott.concesionaria.clients.entitites;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ClientDTO {

    @Schema(hidden = true)
    private Integer id;
    
    @NotBlank(message = "El nombre es requerido")
    @Schema(example = "Juan")
    private String name;
    
    @NotBlank(message = "El apellido es requerido")
    @Schema(example = "Perez")
    private String lastName;

    @NotNull(message = "El ID de la persona no puede ser nulo")
    @Min(value = 1, message = "El ID de la persona debe ser mayor a 0")
    @Schema(example = "1111")
    private Integer identityId;
       
    @NotBlank(message = "El email es requerido")
    @Email(message = "El email debe ser una dirección de correo válida")
    @Schema(example = "juan.perez@example.com")
    private String email;

    @NotBlank(message = "El teléfono es requerido")
    @Schema(example = "+5491133334444")
    private String phone;

    @NotBlank(message = "La dirección es requerida")
    @Schema(example = "Av. 9 de Julio 123, Buenos Aires, Argentina")
    private String address;
} 