package sinnott.concesionaria.sales.service;

import java.time.LocalDate;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import sinnott.concesionaria.sales.entities.repair.Repair;
import sinnott.concesionaria.sales.entities.repair.RepairDTO;
import sinnott.concesionaria.sales.entities.repair.RepairSummaryDTO;
import sinnott.concesionaria.sales.repository.RepairRepository;
import sinnott.concesionaria.sales.utils.Assistance;
import sinnott.concesionaria.sales.utils.RepairMapper;

@Service
public class VehicleRepairService {

    private final RepairRepository repairRepository;
    private final Assistance assistance;

    public VehicleRepairService(RepairRepository repairRepository, Assistance assistance) {
        this.repairRepository = repairRepository;
        this.assistance = assistance;
    }

    public List<RepairSummaryDTO> getAllVehicleRepairs() {
        return repairRepository.findAll()
                             .stream()
                             .map(assistance::format)
                             .collect(Collectors.toList());
    }

    public RepairSummaryDTO getVehicleRepairById(Integer id) {
        Repair repair = repairRepository.findById(id)
                                      .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Reparación no encontrada"));
        return assistance.format(repair);
    }
    
    public RepairSummaryDTO createVehicleRepair(RepairDTO repairsDTO) {
        if (assistance.checkAssistanceValidations(repairsDTO)) {
            return assistance.format(repairRepository.save(RepairMapper.toEntity(repairsDTO)));
        }
        return null;
    }

    public void deleteVehicleRepair(Integer id) {
        if (!repairRepository.existsById(id)) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Reparación no encontrada");
        }
        repairRepository.deleteById(id);
    }

    public List<RepairSummaryDTO> searchVehicleRepairs(Integer employeeId, Integer saleId, LocalDate repairDate) {
        return repairRepository.search(employeeId, saleId, repairDate)
                               .stream()
                               .map(assistance::format)
                               .collect(Collectors.toList());
    }    
}
