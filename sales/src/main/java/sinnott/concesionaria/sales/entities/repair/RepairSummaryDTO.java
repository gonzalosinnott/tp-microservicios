package sinnott.concesionaria.sales.entities.repair;

import java.time.LocalDate;

import lombok.Data;

@Data
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
