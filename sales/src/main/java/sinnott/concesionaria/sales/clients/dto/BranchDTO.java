package sinnott.concesionaria.sales.clients.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import java.time.LocalDate;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class BranchDTO {
    private Integer id;
    private String name;
    private Country country;
    private String province;
    private String city;
    private String address;
    private LocalDate openingDate;
    private Integer deliveryTimeFromCentralWarehouse;
    private Integer deliveryTimeFromBranch;
} 