package sinnott.concesionaria.stock.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import sinnott.concesionaria.stock.entities.inventory.InventoryDTO;
import sinnott.concesionaria.stock.service.InventoryService;
import java.util.List;

@RestController
@RequestMapping("/stock")
@Tag(name = "Inventario", description = "API para la gestion de inventario")
public class InventoryController {
    private final InventoryService inventoryService;

    public InventoryController(InventoryService inventoryService) {
        this.inventoryService = inventoryService;
    }

    @GetMapping("/inventory")
    @Operation(summary = "Obtener todo el inventario")
    public ResponseEntity<List<InventoryDTO>> getAllInventory() {
        return ResponseEntity.ok(inventoryService.getAllInventory());
    }

    @GetMapping("/inventory/{id}")
    @Operation(summary = "Obtener un inventario por ID")
    public ResponseEntity<InventoryDTO> getInventoryById(@PathVariable Integer id) {
        return ResponseEntity.ok(inventoryService.getInventoryById(id));
    }

    @PostMapping("/inventory")
    @Operation(summary = "Crear un inventario")
    public ResponseEntity<InventoryDTO> createInventory(@RequestBody InventoryDTO inventoryDTO) {
        return ResponseEntity.ok(inventoryService.saveInventory(inventoryDTO));
    }

    @PutMapping("/inventory/{id}")
    @Operation(summary = "Actualizar un inventario")
    public ResponseEntity<InventoryDTO> updateInventory(@PathVariable Integer id, @RequestBody InventoryDTO inventoryDTO) {
        return ResponseEntity.ok(inventoryService.updateInventory(id, inventoryDTO));
    }

    @DeleteMapping("/inventory/{id}")
    @Operation(summary = "Eliminar un inventario")
    public ResponseEntity<Void> deleteInventory(@PathVariable Integer id) {
        inventoryService.deleteInventory(id);
        return ResponseEntity.noContent().build();
    }

    @GetMapping("/inventory/search")
    @Operation(summary = "Buscar Inventario por cualquier combinación de atributos")
    public ResponseEntity<List<InventoryDTO>> searchInventory(@RequestParam(required = false) Integer carId,
                                                              @RequestParam(required = false) Integer branchId,
                                                              @RequestParam(required = false) Integer stock) {
        return ResponseEntity.ok(inventoryService.search(carId, branchId, stock));
    }
} 