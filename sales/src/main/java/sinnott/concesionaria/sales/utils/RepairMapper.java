package sinnott.concesionaria.sales.utils;

import sinnott.concesionaria.sales.entities.repair.Repair;
import sinnott.concesionaria.sales.entities.repair.RepairDTO;

public class RepairMapper {

    public static Repair toEntity(RepairDTO repairsDTO) {
        return new Repair(repairsDTO.getEmployeeId(),
                          repairsDTO.getClientId(),
                          repairsDTO.getSaleId(),
                          repairsDTO.getVehicleKm(),
                          repairsDTO.getRepairDate());
    }
}
