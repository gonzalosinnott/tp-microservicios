package sinnott.concesionaria.sales.entities.repair;

import java.time.LocalDate;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
public class Repair {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    private Integer employeeId;
    private Integer clientId;
    private Integer saleId;
    private Double vehicleKm;
    private LocalDate repairDate;

    public Repair(Integer employeeId, Integer clientId, Integer saleId, Double vehicleKm, LocalDate repairDate) {
        this.employeeId = employeeId;
        this.clientId = clientId;
        this.saleId = saleId;
        this.vehicleKm = vehicleKm;
        this.repairDate = repairDate;
    }
}
