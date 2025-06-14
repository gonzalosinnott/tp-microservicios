package sinnott.concesionaria.stock.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import sinnott.concesionaria.stock.entities.StockDTO;
import sinnott.concesionaria.stock.service.StockService;
import java.util.List;

@RestController
@RequestMapping("/stock")
@Tag(name = "Inventory", description = "API for managing inventory entries")
public class StockController {
    private final StockService stockService;

    public StockController(StockService stockService) {
        this.stockService = stockService;
    }

    @GetMapping("/inventory")
    @Operation(summary = "Get all stock entries")
    public ResponseEntity<List<StockDTO>> getAllStocks() {
        return ResponseEntity.ok(stockService.getAllStocks());
    }

    @GetMapping("/inventory/{id}")
    @Operation(summary = "Get a stock entry by ID")
    public ResponseEntity<StockDTO> getStockById(@PathVariable Integer id) {
        return ResponseEntity.ok(stockService.getStockById(id));
    }

    @PostMapping("/inventory")
    @Operation(summary = "Create a new stock entry")
    public ResponseEntity<StockDTO> createStock(@RequestBody StockDTO stockDTO) {
        return ResponseEntity.ok(stockService.saveStock(stockDTO));
    }

    @PutMapping("/inventory/{id}")
    @Operation(summary = "Update a stock entry")
    public ResponseEntity<StockDTO> updateStock(@PathVariable Integer id, @RequestBody StockDTO stockDTO) {
        return ResponseEntity.ok(stockService.updateStock(id, stockDTO));
    }

    @DeleteMapping("/inventory/{id}")
    @Operation(summary = "Delete a stock entry")
    public ResponseEntity<Void> deleteStock(@PathVariable Integer id) {
        stockService.deleteStock(id);
        return ResponseEntity.noContent().build();
    }

    @GetMapping("/inventory/search")
    @Operation(summary = "Search stock entries by any combination of attributes")
    public ResponseEntity<List<StockDTO>> searchStocks(
            @RequestParam(required = false) Integer carId,
            @RequestParam(required = false) Integer branchId,
            @RequestParam(required = false) Integer stockValue) {
        return ResponseEntity.ok(stockService.search(carId, branchId, stockValue));
    }
} 