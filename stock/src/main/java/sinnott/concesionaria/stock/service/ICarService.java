package sinnott.concesionaria.stock.service;

import sinnott.concesionaria.stock.entities.car.CarDTO;
import sinnott.concesionaria.stock.entities.car.enums.CarType;
import java.util.List;

public interface ICarService {
    List<CarDTO> getAllCars();
    CarDTO getCarById(Integer id);
    CarDTO saveCar(CarDTO carDTO);
    CarDTO updateCar(Integer id, CarDTO carDTO);
    void deleteCar(Integer id);
    List<CarDTO> search(String brand, String model, Integer year, CarType type);
} 