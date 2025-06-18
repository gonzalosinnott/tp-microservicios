package sinnott.concesionaria.sales.entities.repair;

import java.time.LocalDate;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class RepairSummaryDTO {
    private Integer repairID;
    private Integer saleID;
    private String client;
    private String employee;
    private String car;
    private Double vehicleKm;
    private LocalDate repairDate;
    private boolean hasWarranty;
}
