package sinnott.concesionaria.stock.entities;

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
    private Double price;
    private String color;
    private Integer warranty;
} 