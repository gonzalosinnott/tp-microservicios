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
public class CarService implements ICarService {
    private final CarRepository carRepository;
    private final InventoryRepository inventoryRepository;

    public CarService(CarRepository carRepository, InventoryRepository inventoryRepository) {
        this.carRepository = carRepository;
        this.inventoryRepository = inventoryRepository;
    }

    @Override
    public List<CarDTO> getAllCars() {
        try {
            return carRepository.findAll()
                .stream()
                .map(CarMapper::toDTO)
                .collect(Collectors.toList());
        } catch (Exception ex) {
            throw new ResponseStatusException(HttpStatus.INTERNAL_SERVER_ERROR, "Error al obtener autos", ex);
        }
    }

    @Override
    public CarDTO getCarById(Integer id) {
        try {
            return carRepository.findById(id).map(CarMapper::toDTO)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Auto no encontrado"));
        } catch (Exception ex) {
            throw new ResponseStatusException(HttpStatus.INTERNAL_SERVER_ERROR, "Error al obtener auto", ex);
        }
    }

    @Override
    public CarDTO saveCar(CarDTO carDTO) {
        try {
            Car car = CarMapper.toEntity(carDTO);
            return CarMapper.toDTO(carRepository.save(car));
        } catch (Exception ex) {
            throw new ResponseStatusException(HttpStatus.INTERNAL_SERVER_ERROR, "Error al guardar auto", ex);
        }
    }

    @Override
    public CarDTO updateCar(Integer id, CarDTO carDTO) {
        try {
            Car car = carRepository.findById(id)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Auto no encontrado"));
            car.setBrand(carDTO.getBrand());
            car.setModel(carDTO.getModel());
            car.setFabricationYear(carDTO.getFabricationYear());
            car.setType(carDTO.getType());
            return CarMapper.toDTO(carRepository.save(car));
        } catch (Exception ex) {
            throw new ResponseStatusException(HttpStatus.INTERNAL_SERVER_ERROR, "Error al actualizar auto", ex);
        }
    }

    @Override
    public void deleteCar(Integer id) {
        try {
            if (!carRepository.existsById(id)) {
                throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Auto no encontrado");
            }
            if (inventoryRepository.search(id, null, null).size() > 0) {    
                throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Auto en inventario");
            }
            carRepository.deleteById(id);
        } catch (Exception ex) {
            throw new ResponseStatusException(HttpStatus.INTERNAL_SERVER_ERROR, "Error al eliminar auto", ex);
        }
    }

    @Override
    public List<CarDTO> search(String brand, String model, Integer year, CarType type) {
        try {
            return carRepository.search(brand, model, year, type)
                .stream()
                .map(CarMapper::toDTO)
                .collect(Collectors.toList());
        } catch (Exception ex) {
            throw new ResponseStatusException(HttpStatus.INTERNAL_SERVER_ERROR, "Error al buscar autos", ex);
        }
    }
} 