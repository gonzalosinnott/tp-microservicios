package sinnott.concesionaria.sales.clients.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class 
InventoryDTO {
    private Integer id;
    private Integer carId;
    private Integer branchId;
    private Integer stock;
} 