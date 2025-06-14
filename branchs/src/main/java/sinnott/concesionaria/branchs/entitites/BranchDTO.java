package sinnott.concesionaria.branchs.entitites;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import sinnott.concesionaria.branchs.models.enums.Country;

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
    private Integer deliveryTimeFromCentralWarehouse;
    private Integer deliveryTimeFromBranch;
} 