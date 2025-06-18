package sinnott.concesionaria.branchs.entitites;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.Table;
import jakarta.persistence.UniqueConstraint;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import sinnott.concesionaria.branchs.entitites.enums.Country;
import java.time.LocalDate;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(uniqueConstraints = @UniqueConstraint(columnNames = {"province", "city"}))
public class Branch {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    private String name;
    @Enumerated(EnumType.STRING)
    private Country country;
    private String province;
    private String city;
    private String address;
    private LocalDate openingDate;
    private Integer deliveryTimeFromCentralWarehouse;
    private Integer deliveryTimeFromBranch;

    public Branch(String name, Country country, String province, String city, String address, LocalDate openingDate,
                  Integer deliveryTimeFromCentralWarehouse, Integer deliveryTimeFromBranch) {
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