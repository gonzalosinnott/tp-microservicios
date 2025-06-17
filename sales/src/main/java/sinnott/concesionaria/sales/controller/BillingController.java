package sinnott.concesionaria.sales.controller;

import java.time.LocalDate;
import java.util.List;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import sinnott.concesionaria.sales.entities.sale.BillingDTO;
import sinnott.concesionaria.sales.entities.sale.SaleDTO;
import sinnott.concesionaria.sales.entities.sale.SaleSummaryDTO;
import sinnott.concesionaria.sales.service.BillingService;

@RestController
@RequestMapping("/sales")
@Tag(name = "Ventas", description = "API para la gestión de ventas")
public class BillingController {

    private final BillingService saleService;

    public BillingController(BillingService saleService) {
        this.saleService = saleService;
    }

    @GetMapping("/billing")
    @Operation(summary = "Obtener todas las ventas")
    public ResponseEntity<List<SaleSummaryDTO>> getBilling() {
        return ResponseEntity.ok(saleService.getSales());
    }

    @GetMapping("/billing/{id}")
    @Operation(summary = "Obtener una venta por ID")
    public ResponseEntity<SaleSummaryDTO> getBillingById(@PathVariable Integer id) {
        return ResponseEntity.ok(saleService.getSaleById(id));
    }

    @PostMapping("/billing")
    @Operation(summary = "Crear una venta")
    public ResponseEntity<BillingDTO> createBilling(@Valid @RequestBody SaleDTO saleDTO) {
        return ResponseEntity.ok(saleService.saveSale(saleDTO));
    }

    @DeleteMapping("/billing/{id}")
    @Operation(summary = "Eliminar una venta")
    public ResponseEntity<Void> deleteBilling(@PathVariable Integer id) {
        saleService.deleteSale(id);
        return ResponseEntity.noContent().build();
    }

    @GetMapping("/billing/search")
    @Operation(summary = "Buscar ventas por cualquier combinación de atributos")
    public ResponseEntity<List<SaleSummaryDTO>> searchBilling(@RequestParam(required = false) Integer employeeId,
                                                              @RequestParam(required = false) Integer carId,
                                                              @RequestParam(required = false) Integer clientId,
                                                              @RequestParam(required = false) LocalDate saleDate) {
        return ResponseEntity.ok(saleService.search(employeeId, carId, clientId, saleDate));
    }
} 