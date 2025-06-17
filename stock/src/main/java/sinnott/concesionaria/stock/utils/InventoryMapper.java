package sinnott.concesionaria.stock.utils;

import sinnott.concesionaria.stock.entities.inventory.Inventory;
import sinnott.concesionaria.stock.entities.inventory.InventoryDTO;

public class InventoryMapper {
    public static Inventory toEntity(InventoryDTO dto) {
        return new Inventory(dto.getCarId(),
                             dto.getBranchId(),
                             dto.getStock());
    }

    public static InventoryDTO toDTO(Inventory entity) {
        return new InventoryDTO(entity.getId(),
                                entity.getCarId(),
                                entity.getBranchId(),
                                entity.getStock());
    }
} 