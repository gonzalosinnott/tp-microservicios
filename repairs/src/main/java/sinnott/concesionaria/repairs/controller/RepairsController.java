package sinnott.concesionaria.repairs.controller;

import java.time.LocalDate;
import java.util.List;

import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Qualifier;
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
import sinnott.concesionaria.repairs.entities.RepairDTO;
import sinnott.concesionaria.repairs.entities.RepairSummaryDTO;
import sinnott.concesionaria.repairs.service.IVehicleRepairService;

@RestController
@RequestMapping("/repairs")
@Tag(name = "Services", description = "API para la gestión de reparaciones de vehículos")
public class RepairsController {

    private final IVehicleRepairService vehicleRepairService;

    public RepairsController(@Qualifier("vehicleRepairService") IVehicleRepairService vehicleRepairService) {
        this.vehicleRepairService = vehicleRepairService;
    }

    @GetMapping("")
    @Operation(summary = "Obtener todos los servicios de reparación")
    public ResponseEntity<List<RepairSummaryDTO>> getAllVehicleRepairs() {
        return ResponseEntity.ok(vehicleRepairService.getAllVehicleRepairs());
    }

    @GetMapping("/{id}")
    @Operation(summary = "Obtener un servicio de reparación por ID")
    public ResponseEntity<RepairSummaryDTO> getVehicleRepairById(@PathVariable Integer id) {
        return ResponseEntity.ok(vehicleRepairService.getVehicleRepairById(id));
    }

    @PostMapping("")
    @Operation(summary = "Crear un servicio de reparación")
    public ResponseEntity<RepairSummaryDTO> createVehicleRepair(@RequestBody RepairDTO repairsDTO) {
        return ResponseEntity.ok(vehicleRepairService.createVehicleRepair(repairsDTO));
    }

    @DeleteMapping("/{id}")
    @Operation(summary = "Eliminar un servicio de reparación")
    public ResponseEntity<Void> deleteVehicleRepair(@PathVariable Integer id) {
        vehicleRepairService.deleteVehicleRepair(id);
        return ResponseEntity.noContent().build();
    }

    @GetMapping("/search")
    @Operation(summary = "Buscar servicios de reparación por cualquier combinación de atributos")
    public ResponseEntity<List<RepairSummaryDTO>> searchVehicleRepairs(@RequestParam(required = false) Integer employeeId,
                                                                       @RequestParam(required = false) Integer saleId,
                                                                       @RequestParam(required = false) LocalDate repairDate) {
        return ResponseEntity.ok(vehicleRepairService.searchVehicleRepairs(employeeId, saleId, repairDate));
    }
}
