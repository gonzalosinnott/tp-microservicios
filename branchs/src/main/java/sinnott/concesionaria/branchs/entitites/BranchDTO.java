package sinnott.concesionaria.branchs.entitites;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import lombok.Data;
import sinnott.concesionaria.branchs.entitites.enums.Country;
import java.time.LocalDate;

@Data
public class BranchDTO {
    @Schema(hidden = true)
    private Integer id;

    @NotEmpty(message = "El nombre de la sucursal no puede estar vacio")
    @Schema(description = "Nombre de la sucursal")
    private String name;

    @NotNull(message = "El país de la sucursal no puede ser nulo")
    @Schema(description = "País de la sucursal")
    private Country country;

    @NotEmpty(message = "La provincia de la sucursal no puede estar vacia")
    @Schema(description = "Provincia de la sucursal")
    private String province;

    @NotEmpty(message = "La ciudad de la sucursal no puede estar vacia")
    @Schema(description = "Ciudad de la sucursal")
    private String city;

    @NotEmpty(message = "La dirección de la sucursal no puede estar vacia")
    @Schema(description = "Dirección de la sucursal")
    private String address;

    @NotNull(message = "La fecha de apertura no puede ser nula")
    @Schema(description = "Fecha de apertura de la sucursal")
    private LocalDate openingDate;

    @NotNull(message = "El tiempo de entrega desde el almacén central no puede ser nulo")
    @Schema(description = "Tiempo de entrega desde el almacén central")
    private Integer deliveryTimeFromCentralWarehouse;

    @NotNull(message = "El tiempo de entrega desde la sucursal no puede ser nulo")
    @Schema(description = "Tiempo de entrega desde la sucursal")
    private Integer deliveryTimeFromBranch;

    public BranchDTO(Integer id, String name, Country country, String province, String city, String address,
                     LocalDate openingDate, Integer deliveryTimeFromCentralWarehouse, Integer deliveryTimeFromBranch) {
        this.id = id;
        this.name = name;
        this.country = country;
        this.province = province;
        this.city = city;
        this.address = address;
        this.openingDate = openingDate;
        this.deliveryTimeFromCentralWarehouse = deliveryTimeFromCentralWarehouse;
        this.deliveryTimeFromBranch = deliveryTimeFromBranch;
    }

} 