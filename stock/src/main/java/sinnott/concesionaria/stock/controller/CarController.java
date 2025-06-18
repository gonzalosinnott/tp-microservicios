package sinnott.concesionaria.stock.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import sinnott.concesionaria.stock.entities.car.CarDTO;
import sinnott.concesionaria.stock.entities.car.enums.CarType;
import sinnott.concesionaria.stock.service.ICarService;
import java.util.List;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Qualifier;

@RestController
@RequestMapping("/stock")
@Tag(name = "Catalogo Autos", description = "API para la gestion del catalogo de Autos")
public class CarController {
    private final ICarService carService;

    public CarController(@Qualifier("carService") ICarService carService) {
        this.carService = carService;
    }

    @GetMapping("/catalog/cars")
    @Operation(summary = "Obtener todos los autos")
    public ResponseEntity<List<CarDTO>> getAllCars() {
        return ResponseEntity.ok(carService.getAllCars());
    }

    @GetMapping("/catalog/cars/{id}")
    @Operation(summary = "Obtener un auto por ID")
    public ResponseEntity<CarDTO> getCarById(@PathVariable Integer id) {
        return ResponseEntity.ok( carService.getCarById(id));
    }

    @PostMapping("/catalog/cars")
    @Operation(summary = "Crear un auto")
    public ResponseEntity<CarDTO> createCar(@RequestBody CarDTO carDTO) {
        return ResponseEntity.ok(carService.saveCar(carDTO));    
    }

    @PutMapping("/catalog/cars/{id}")
    @Operation(summary = "Actualizar un auto")
    public ResponseEntity<CarDTO> updateCar(@PathVariable Integer id, @RequestBody CarDTO carDTO) {
        return ResponseEntity.ok(carService.updateCar(id, carDTO));
    }

    @DeleteMapping("/catalog/cars/{id}")
    @Operation(summary = "Eliminar un auto")
    public ResponseEntity<Void> deleteCar( @PathVariable Integer id) {
        carService.deleteCar(id);
        return ResponseEntity.noContent().build();
    }

    @GetMapping("/catalog/cars/search")
    @Operation(summary = "Buscar autos por cualquier combinación de atributos")
    public ResponseEntity<List<CarDTO>> searchCars(@RequestParam(required = false) String brand,
                                                   @RequestParam(required = false) String model,
                                                   @RequestParam(required = false) Integer year,
                                                   @RequestParam(required = false) CarType type) {
            return ResponseEntity.ok(carService.search(brand, model, year, type));
    }    
} 