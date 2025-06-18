package sinnott.concesionaria.stock.service;

import sinnott.concesionaria.stock.entities.inventory.InventoryDTO;
import java.util.List;
import sinnott.concesionaria.stock.entities.inventory.InventorySaleDTO;

public interface IInventoryService {
    List<InventoryDTO> getAllInventory();
    InventoryDTO getInventoryById(Integer id);
    InventoryDTO saveInventory(InventoryDTO inventoryDTO);
    InventoryDTO updateInventory(Integer id, InventoryDTO inventoryDTO);
    void deleteInventory(Integer id);
    List<InventoryDTO> search(Integer carId, Integer branchId, Integer stock);
    InventoryDTO updateInventoryForSale(InventorySaleDTO inventorySaleDTO);
} 