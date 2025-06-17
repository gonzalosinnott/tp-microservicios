package sinnott.concesionaria.sales.entities.sale;

import java.time.LocalDate;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class SaleSummaryDTO {
    private Integer saleID;
    private String client;
    private String employee;
    private String branch;
    private String car;
    private Double amount;
    private LocalDate saleDate;

}
