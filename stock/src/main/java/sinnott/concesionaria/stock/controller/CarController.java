package sinnott.concesionaria.stock.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import sinnott.concesionaria.stock.entities.CarDTO;
import sinnott.concesionaria.stock.service.CarService;
import java.util.List;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.tags.Tag;

@RestController
@RequestMapping("/stock")
@Tag(name = "Cars Management", description = "APIs for managing cars catalog")
public class CarController {
    private static final Logger logger = LoggerFactory.getLogger(CarController.class);
    private final CarService carService;

    public CarController(CarService carService) {
        this.carService = carService;
    }

    @Operation(summary = "Get all cars", description = "Retrieves a list of all cars in the dealership")
    @GetMapping("/catalog/cars")
    public ResponseEntity<List<CarDTO>> getAllCars() {
        try {
            logger.debug("Fetching all cars");
            List<CarDTO> cars = carService.getAllCars();
            return ResponseEntity.ok(cars);
        } catch (Exception e) {
            logger.error("Error fetching all cars", e);
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
    }

    @Operation(summary = "Get car by ID", description = "Retrieves a specific car by its ID")
    @GetMapping("/catalog/cars/{id}")
    public ResponseEntity<CarDTO> getCarById(
            @Parameter(description = "ID of the car to retrieve", required = true)
            @PathVariable Integer id) {
        try {
            logger.debug("Fetching car with id: {}", id);
            CarDTO car = carService.getCarById(id);
            return ResponseEntity.ok(car);
        } catch (RuntimeException e) {
            logger.error("Error fetching car with id: {}", id, e);
            return ResponseEntity.notFound().build();
        } catch (Exception e) {
            logger.error("Unexpected error fetching car with id: {}", id, e);
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
    }

    @Operation(summary = "Create new car", description = "Creates a new car in the dealership")
    @PostMapping("/catalog/cars")
    public ResponseEntity<CarDTO> createCar(
            @Parameter(description = "Car details to create", required = true)
            @RequestBody CarDTO carDTO) {
        try {
            logger.debug("Creating new car: {}", carDTO);
            CarDTO savedCar = carService.saveCar(carDTO);
            return ResponseEntity.ok(savedCar);
        } catch (Exception e) {
            logger.error("Error creating car: {}", carDTO, e);
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
    }

    @Operation(summary = "Update car", description = "Updates an existing car's details")
    @PutMapping("/catalog/cars/{id}")
    public ResponseEntity<CarDTO> updateCar(
            @Parameter(description = "ID of the car to update", required = true)
            @PathVariable Integer id,
            @Parameter(description = "Updated car details", required = true)
            @RequestBody CarDTO carDTO) {
        try {
            logger.debug("Updating car with id: {}", id);
            CarDTO updatedCar = carService.updateCar(id, carDTO);
            return ResponseEntity.ok(updatedCar);
        } catch (RuntimeException e) {
            logger.error("Error updating car with id: {}", id, e);
            return ResponseEntity.notFound().build();
        } catch (Exception e) {
            logger.error("Unexpected error updating car with id: {}", id, e);
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
    }

    @Operation(summary = "Delete car", description = "Deletes a car from the dealership")
    @DeleteMapping("/catalog/cars/{id}")
    public ResponseEntity<Void> deleteCar(
            @Parameter(description = "ID of the car to delete", required = true)
            @PathVariable Integer id) {
        try {
            logger.debug("Deleting car with id: {}", id);
            carService.deleteCar(id);
            return ResponseEntity.noContent().build();
        } catch (RuntimeException e) {
            logger.error("Error deleting car with id: {}", id, e);
            return ResponseEntity.notFound().build();
        } catch (Exception e) {
            logger.error("Unexpected error deleting car with id: {}", id, e);
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
    }

    @Operation(summary = "Search cars", description = "Search cars based on various criteria")
    @GetMapping("/catalog/cars/search")
    public ResponseEntity<List<CarDTO>> searchCars(
            @Parameter(description = "Brand of the car")
            @RequestParam(required = false) String brand,
            @Parameter(description = "Model of the car")
            @RequestParam(required = false) String model,
            @Parameter(description = "Year of the car")
            @RequestParam(required = false) Integer year,
            @Parameter(description = "Price of the car")
            @RequestParam(required = false) Double price,
            @Parameter(description = "Color of the car")
            @RequestParam(required = false) String color,
            @Parameter(description = "Warranty period in months")
            @RequestParam(required = false) Integer warranty) {
        try {
            logger.debug("Searching cars with parameters: brand={}, model={}, year={}, price={}, color={}, warranty={}",
                brand, model, year, price, color, warranty);
            List<CarDTO> cars = carService.search(brand, model, year, price, color, warranty);
            return ResponseEntity.ok(cars);
        } catch (Exception e) {
            logger.error("Error searching cars with parameters: brand={}, model={}, year={}, price={}, color={}, warranty={}",
                brand, model, year, price, color, warranty, e);
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
    }
} 