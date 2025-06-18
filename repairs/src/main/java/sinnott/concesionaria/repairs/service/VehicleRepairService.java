package sinnott.concesionaria.repairs.service;

import java.time.LocalDate;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import sinnott.concesionaria.repairs.entities.Repair;
import sinnott.concesionaria.repairs.entities.RepairDTO;
import sinnott.concesionaria.repairs.entities.RepairSummaryDTO;
import sinnott.concesionaria.repairs.repository.RepairRepository;
import sinnott.concesionaria.repairs.utils.Assistance;
import sinnott.concesionaria.repairs.utils.RepairMapper;

@Service
public class VehicleRepairService implements IVehicleRepairService {

    private final RepairRepository repairRepository;
    private final Assistance assistance;

    public VehicleRepairService(RepairRepository repairRepository, Assistance assistance) {
        this.repairRepository = repairRepository;
        this.assistance = assistance;
    }

    @Override
    public List<RepairSummaryDTO> getAllVehicleRepairs() {
        try {
            return repairRepository.findAll()
                .stream()
                .map(assistance::format)
                .collect(Collectors.toList());
        } catch (Exception ex) {
            throw new ResponseStatusException(HttpStatus.INTERNAL_SERVER_ERROR, "Error al obtener reparaciones", ex);
        }
    }

    @Override
    public RepairSummaryDTO getVehicleRepairById(Integer id) {
        try {
            Repair repair = repairRepository.findById(id)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Reparación no encontrada"));
            return assistance.format(repair);
        } catch (Exception ex) {
            throw new ResponseStatusException(HttpStatus.INTERNAL_SERVER_ERROR, "Error al obtener reparación", ex);
        }
    }

    @Override
    public RepairSummaryDTO createVehicleRepair(RepairDTO repairsDTO) {
        try {
            if (assistance.checkAssistanceValidations(repairsDTO)) {
                return assistance.format(repairRepository.save(RepairMapper.toEntity(repairsDTO)));
            }
            return null;
        } catch (Exception ex) {
            throw new ResponseStatusException(HttpStatus.INTERNAL_SERVER_ERROR, "Error al guardar reparación", ex);
        }
    }

    @Override
    public void deleteVehicleRepair(Integer id) {
        try {
            if (!repairRepository.existsById(id)) {
                throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Reparación no encontrada");
            }
            repairRepository.deleteById(id);
        } catch (Exception ex) {
            throw new ResponseStatusException(HttpStatus.INTERNAL_SERVER_ERROR, "Error al eliminar reparación", ex);
        }
    }

    @Override
    public List<RepairSummaryDTO> searchVehicleRepairs(Integer employeeId, Integer saleId, LocalDate repairDate) {
        try {
            return repairRepository.search(employeeId, saleId, repairDate)
                .stream()
                .map(assistance::format)
                .collect(Collectors.toList());
        } catch (Exception ex) {
            throw new ResponseStatusException(HttpStatus.INTERNAL_SERVER_ERROR, "Error al buscar reparaciones", ex);
        }
    }
}
