package sinnott.concesionaria.clients.entitites;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import io.swagger.v3.oas.annotations.media.Schema;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class ClientDTO {
    private Integer id;
    @Schema(example = "Juan")
    private String name;
    @Schema(example = "Perez")
    private String surname;
    @Schema(example = "juan.perez@example.com")
    private String email;
    @Schema(example = "+5491133334444")
    private String phone;
    @Schema(example = "Av. 9 de Julio 123, Buenos Aires, Argentina")
    private String address;
} 