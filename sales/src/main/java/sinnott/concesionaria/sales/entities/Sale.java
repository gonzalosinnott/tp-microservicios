package sinnott.concesionaria.sales.entities;

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
public class Sale {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    
    private Integer employeeId;
    
    private Integer carId;
    
    private Integer clientId;

    private Double amount;
    
    private LocalDate saleDate;

    public Sale(Integer employeeId, Integer carId, Integer clientId, Double amount, LocalDate saleDate) {
        this.employeeId = employeeId;
        this.carId = carId;
        this.clientId = clientId;
        this.amount = amount;
        this.saleDate = saleDate;
    }
} 