package sinnott.concesionaria.sales.entities;

import java.time.LocalDate;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class BillingDTO {
    private Integer id;
    private String client;
    private String employee;
    private String branch;
    private String car;
    private Double amount;
    private LocalDate saleDate;
    private LocalDate deliveryDate;
}
