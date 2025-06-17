package sinnott.concesionaria.stock.utils;

import sinnott.concesionaria.stock.entities.car.Car;
import sinnott.concesionaria.stock.entities.car.CarDTO;

public class CarMapper {
    public static Car toEntity(CarDTO dto) {
        return new Car(dto.getBrand(),
                       dto.getModel(),
                       dto.getFabricationYear(),
                       dto.getType());
    }

    public static CarDTO toDTO(Car entity) {
        return new CarDTO(entity.getId(),
                          entity.getBrand(),
                          entity.getModel(),
                          entity.getFabricationYear(),
                          entity.getType());
    }
} 