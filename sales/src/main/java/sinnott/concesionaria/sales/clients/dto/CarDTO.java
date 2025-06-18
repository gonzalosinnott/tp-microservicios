package sinnott.concesionaria.sales.clients.dto;

import lombok.Data;

@Data
public class CarDTO {
    private Integer id;
    private String brand;
    private String model;
    private Integer fabricationYear;
    private CarType type;
} 
