package sinnott.concesionaria.stock.utils;

import sinnott.concesionaria.stock.entities.StockDTO;
import sinnott.concesionaria.stock.models.Stock;

public class StockMapper {
    public static Stock toEntity(StockDTO dto) {
        return new Stock(
            dto.getId(),
            dto.getCarId(),
            dto.getBranchId(),
            dto.getStock()
        );
    }

    public static StockDTO toDTO(Stock entity) {
        return new StockDTO(
            entity.getId(),
            entity.getCarId(),
            entity.getBranchId(),
            entity.getStock()
        );
    }
} 