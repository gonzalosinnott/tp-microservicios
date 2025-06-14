package sinnott.concesionaria.stock.entities;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class 
StockDTO {
    private Integer id;
    private Integer carId;
    private Integer branchId;
    private Integer stock;
} 