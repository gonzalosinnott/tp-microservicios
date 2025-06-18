package sinnott.concesionaria.sales.entities;

import java.time.LocalDate;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import sinnott.concesionaria.sales.clients.dto.CarType;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class SaleSummaryDTO {
    private Integer id;
    private String client;
    private String employee;
    private String branch;
    private String car;
    private CarType type;
    private Double amount;
    private LocalDate saleDate;
}
