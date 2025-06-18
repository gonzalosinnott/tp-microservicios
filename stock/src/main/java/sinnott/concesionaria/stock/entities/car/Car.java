package sinnott.concesionaria.stock.entities.car;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import jakarta.persistence.UniqueConstraint;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import sinnott.concesionaria.stock.entities.car.enums.CarType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.EnumType;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(uniqueConstraints = @UniqueConstraint(columnNames = {"brand", "model", "fabricationYear"}))
public class Car {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    
    private String brand;
    
    private String model;
   
    private Integer fabricationYear;

    @Enumerated(EnumType.STRING)
    private CarType type;
   
    public Car(String brand, String model, Integer fabricationYear, CarType type) {
        this.brand = brand;
        this.model = model;
        this.fabricationYear = fabricationYear;
        this.type = type;
    }
} 