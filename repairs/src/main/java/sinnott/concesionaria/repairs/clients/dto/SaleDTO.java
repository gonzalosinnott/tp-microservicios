package sinnott.concesionaria.repairs.clients.dto;

import java.time.LocalDate;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class SaleDTO {
    private Integer id;
    private String client;
    private String employee;
    private String branch;
    private String car;
    private CarType type;
    private Double amount;
    private LocalDate saleDate;
}
