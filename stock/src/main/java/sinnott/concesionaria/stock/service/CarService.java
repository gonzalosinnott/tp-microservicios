package sinnott.concesionaria.stock.service;

import org.springframework.stereotype.Service;

import sinnott.concesionaria.stock.entities.CarDTO;
import sinnott.concesionaria.stock.models.Car;
import sinnott.concesionaria.stock.entities.CarRepository;
import sinnott.concesionaria.stock.utils.CarMapper;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class CarService {
    private final CarRepository carRepository;

    public CarService(CarRepository carRepository) {
        this.carRepository = carRepository;
    }

    public List<CarDTO> getAllCars() {
        return carRepository.findAll().stream().map(CarMapper::toDTO).collect(Collectors.toList());
    }

    public CarDTO getCarById(Integer id) {
        return carRepository.findById(id).map(CarMapper::toDTO).orElseThrow(() -> new RuntimeException("Car not found"));
    }

    public CarDTO saveCar(CarDTO carDTO) {
        Car car = CarMapper.toEntity(carDTO);
        return CarMapper.toDTO(carRepository.save(car));
    }

    public CarDTO updateCar(Integer id, CarDTO carDTO) {
        Car car = carRepository.findById(id).orElseThrow(() -> new RuntimeException("Car not found"));
        car.setBrand(carDTO.getBrand());
        car.setModel(carDTO.getModel());
        car.setFabricationYear(carDTO.getFabricationYear());
        car.setPrice(carDTO.getPrice());
        car.setColor(carDTO.getColor());
        car.setWarranty(carDTO.getWarranty());
        return CarMapper.toDTO(carRepository.save(car));
    }

    public void deleteCar(Integer id) {
        carRepository.deleteById(id);
    }

    public List<CarDTO> search(String brand, String model, Integer year, Double price, String color, Integer warranty) {
        return carRepository.findAll().stream()
            .filter(car -> brand == null || car.getBrand().equalsIgnoreCase(brand))
            .filter(car -> model == null || car.getModel().equalsIgnoreCase(model))
            .filter(car -> year == null || car.getFabricationYear().equals(year))
            .filter(car -> price == null || car.getPrice().equals(price))
            .filter(car -> color == null || car.getColor().equalsIgnoreCase(color))
            .filter(car -> warranty == null || car.getWarranty().equals(warranty))
            .map(CarMapper::toDTO)
            .collect(Collectors.toList());
    }
} 