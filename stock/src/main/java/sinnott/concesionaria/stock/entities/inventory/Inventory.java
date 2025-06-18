package sinnott.concesionaria.stock.entities.inventory;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import jakarta.persistence.UniqueConstraint;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.AllArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(uniqueConstraints = @UniqueConstraint(columnNames = {"carId", "branchId"}))
public class Inventory {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    
    private Integer carId;
    
    private Integer branchId;
    
    private Integer stock;

    public Inventory(Integer carId, Integer branchId, Integer stock) {
        this.carId = carId;
        this.branchId = branchId;
        this.stock = stock;
    }
} 