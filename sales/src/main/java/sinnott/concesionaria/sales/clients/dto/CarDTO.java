package sinnott.concesionaria.sales.clients.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class CarDTO {
    private Integer id;
    private String brand;
    private String model;
    private Integer fabricationYear;
    private CarType type;
} 
