package sinnott.concesionaria.sales.clients.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class InventorySaleDTO {
    private Integer carId;
    private Integer branchId;
    private Integer quantity;
} 