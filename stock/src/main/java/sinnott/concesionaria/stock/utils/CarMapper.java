package sinnott.concesionaria.stock.utils;

import sinnott.concesionaria.stock.entities.CarDTO;
import sinnott.concesionaria.stock.models.Car;

public class CarMapper {
    public static Car toEntity(CarDTO dto) {
        return new Car(
            dto.getId(),
            dto.getBrand(),
            dto.getModel(),
            dto.getFabricationYear(),
            dto.getPrice(),
            dto.getColor(),
            dto.getWarranty()
        );
    }

    public static CarDTO toDTO(Car entity) {
        return new CarDTO(
            entity.getId(),
            entity.getBrand(),
            entity.getModel(),
            entity.getFabricationYear(),
            entity.getPrice(),
            entity.getColor(),
            entity.getWarranty()
        );
    }
} 