package sinnott.concesionaria.sales.entities.sale;

import java.time.LocalDate;

import lombok.Data;

@Data
public class BillingDTO {
    private Integer saleID;
    private String client;
    private String employee;
    private String branch;
    private String car;
    private Double amount;
    private LocalDate saleDate;
    private LocalDate deliveryDate;
}
