package sinnott.concesionaria.sales.utils;

import sinnott.concesionaria.sales.entities.Sale;
import sinnott.concesionaria.sales.entities.SaleDTO;

public class SaleMapper {

    public static Sale toEntity(SaleDTO saleDTO) {
        return new Sale(saleDTO.getEmployeeId(),
                        saleDTO.getCarId(),
                        saleDTO.getClientId(),
                        saleDTO.getAmount(),
                        saleDTO.getSaleDate());
    }
} 