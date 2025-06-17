package sinnott.concesionaria.stock.service;

import org.springframework.stereotype.Service;
import org.springframework.http.HttpStatus;
import org.springframework.web.server.ResponseStatusException;

import sinnott.concesionaria.stock.entities.car.Car;
import sinnott.concesionaria.stock.entities.car.CarDTO;
import sinnott.concesionaria.stock.entities.car.enums.CarType;
import sinnott.concesionaria.stock.repository.CarRepository;
import sinnott.concesionaria.stock.repository.InventoryRepository;
import sinnott.concesionaria.stock.utils.CarMapper;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class CarService {
    private final CarRepository carRepository;
    private final InventoryRepository inventoryRepository;

    public CarService(CarRepository carRepository, InventoryRepository inventoryRepository) {
        this.carRepository = carRepository;
        this.inventoryRepository = inventoryRepository;
    }

    public List<CarDTO> getAllCars() {
        return carRepository.findAll()
                            .stream()
                            .map(CarMapper::toDTO)
                            .collect(Collectors.toList());
    }

    public CarDTO getCarById(Integer id) {
        return carRepository.findById(id).map(CarMapper::toDTO)
            .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Auto no encontrado"));
    }

    public CarDTO saveCar(CarDTO carDTO) {
        Car car = CarMapper.toEntity(carDTO);
        return CarMapper.toDTO(carRepository.save(car));
    }

    public CarDTO updateCar(Integer id, CarDTO carDTO) {
        Car car = carRepository.findById(id)
            .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Auto no encontrado"));
        car.setBrand(carDTO.getBrand());
        car.setModel(carDTO.getModel());
        car.setFabricationYear(carDTO.getFabricationYear());
        car.setType(carDTO.getType());
        return CarMapper.toDTO(carRepository.save(car));
    }

    public void deleteCar(Integer id) {
        if (!carRepository.existsById(id)) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Auto no encontrado");
        }
        if (inventoryRepository.search(id, null, null).size() > 0) {    
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Auto en inventario");
        }
        carRepository.deleteById(id);
    }

    public List<CarDTO> search(String brand, String model, Integer year, CarType type) {
        return carRepository.search(brand, model, year, type)
                            .stream()
                            .map(CarMapper::toDTO)
                            .collect(Collectors.toList());
    }
} 