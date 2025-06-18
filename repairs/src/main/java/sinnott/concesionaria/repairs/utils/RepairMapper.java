package sinnott.concesionaria.repairs.utils;

import sinnott.concesionaria.repairs.entities.Repair;
import sinnott.concesionaria.repairs.entities.RepairDTO;

public class RepairMapper {

    public static Repair toEntity(RepairDTO repairsDTO) {
        return new Repair(repairsDTO.getEmployeeId(),
                          repairsDTO.getClientId(),
                          repairsDTO.getSaleId(),
                          repairsDTO.getVehicleKm(),
                          repairsDTO.getRepairDate());
    }
}
