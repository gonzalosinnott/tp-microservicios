package sinnott.concesionaria.branchs.utils;

import sinnott.concesionaria.branchs.entitites.Branch;
import sinnott.concesionaria.branchs.entitites.BranchDTO;

public class BranchMapper {
    public static Branch toEntity(BranchDTO dto) {
        return new Branch(dto.getName(),
                          dto.getCountry(),
                          dto.getProvince(),
                          dto.getCity(),
                          dto.getAddress(),
                          dto.getOpeningDate(),
                          dto.getDeliveryTimeFromCentralWarehouse(),
                          dto.getDeliveryTimeFromBranch());
    }

    public static BranchDTO toDTO(Branch entity) {
        return new BranchDTO(entity.getId(),
                             entity.getName(),
                             entity.getCountry(),
                             entity.getProvince(),
                             entity.getCity(),
                             entity.getAddress(),
                             entity.getOpeningDate(),
                             entity.getDeliveryTimeFromCentralWarehouse(),
                             entity.getDeliveryTimeFromBranch());
    }
} 