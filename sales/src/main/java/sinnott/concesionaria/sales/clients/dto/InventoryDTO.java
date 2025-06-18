package sinnott.concesionaria.sales.clients.dto;

import lombok.Data;

@Data
public class InventoryDTO {
    private Integer id;
    private Integer carId;
    private Integer branchId;
    private Integer stock;
} 