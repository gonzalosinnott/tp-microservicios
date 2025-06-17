package sinnott.concesionaria.sales.controller;

import java.time.LocalDate;
import java.util.List;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import io.swagger.v3.oas.annotations.Operation;
import sinnott.concesionaria.sales.entities.repair.RepairDTO;
import sinnott.concesionaria.sales.entities.repair.RepairSummaryDTO;
import sinnott.concesionaria.sales.service.VehicleRepairService;

@RestController
@RequestMapping("/sales")
public class VehicleRepairController {

    private final VehicleRepairService vehicleRepairService;

    public VehicleRepairController(VehicleRepairService vehicleRepairService) {
        this.vehicleRepairService = vehicleRepairService;
    }

    @GetMapping("/repairs")
    @Operation(summary = "Obtener todos los servicios de reparación")
    public ResponseEntity<List<RepairSummaryDTO>> getAllVehicleRepairs() {
        return ResponseEntity.ok(vehicleRepairService.getAllVehicleRepairs());
    }

    @GetMapping("/repairs/{id}")
    @Operation(summary = "Obtener un servicio de reparación por ID")
    public ResponseEntity<RepairSummaryDTO> getVehicleRepairById(@PathVariable Integer id) {
        return ResponseEntity.ok(vehicleRepairService.getVehicleRepairById(id));
    }

    @PostMapping("/repairs")
    @Operation(summary = "Crear un servicio de reparación")
    public ResponseEntity<RepairSummaryDTO> createVehicleRepair(@RequestBody RepairDTO repairsDTO) {
        return ResponseEntity.ok(vehicleRepairService.createVehicleRepair(repairsDTO));
    }

    @DeleteMapping("/repairs/{id}")
    @Operation(summary = "Eliminar un servicio de reparación")
    public ResponseEntity<Void> deleteVehicleRepair(@PathVariable Integer id) {
        vehicleRepairService.deleteVehicleRepair(id);
        return ResponseEntity.noContent().build();
    }

    @GetMapping("/repairs/search")
    @Operation(summary = "Buscar servicios de reparación por cualquier combinación de atributos")
    public ResponseEntity<List<RepairSummaryDTO>> searchVehicleRepairs(@RequestParam(required = false) Integer employeeId,
                                                                       @RequestParam(required = false) Integer carId,
                                                                       @RequestParam(required = false) LocalDate repairDate) {
        return ResponseEntity.ok(vehicleRepairService.searchVehicleRepairs(employeeId, carId, repairDate));
    }
}
