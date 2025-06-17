package sinnott.concesionaria.sales.utils;

import sinnott.concesionaria.sales.entities.sale.Sale;
import sinnott.concesionaria.sales.entities.sale.SaleDTO;

public class SaleMapper {

    public static Sale toEntity(SaleDTO saleDTO) {
        return new Sale(saleDTO.getEmployeeId(),
                        saleDTO.getCarId(),
                        saleDTO.getClientId(),
                        saleDTO.getAmount(),
                        saleDTO.getSaleDate());
    }
} 