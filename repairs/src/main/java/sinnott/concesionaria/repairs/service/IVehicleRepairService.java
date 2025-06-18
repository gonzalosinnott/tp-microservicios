package sinnott.concesionaria.repairs.service;

import sinnott.concesionaria.repairs.entities.RepairDTO;
import sinnott.concesionaria.repairs.entities.RepairSummaryDTO;
import java.time.LocalDate;
import java.util.List;

public interface IVehicleRepairService {
    List<RepairSummaryDTO> getAllVehicleRepairs();
    RepairSummaryDTO getVehicleRepairById(Integer id);
    RepairSummaryDTO createVehicleRepair(RepairDTO repairsDTO);
    void deleteVehicleRepair(Integer id);
    List<RepairSummaryDTO> searchVehicleRepairs(Integer employeeId, Integer saleId, LocalDate repairDate);
} 