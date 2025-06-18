package sinnott.concesionaria.stock.entities.car;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotEmpty;
import lombok.Data;
import sinnott.concesionaria.stock.entities.car.enums.CarType;

@Data
public class CarDTO {

    @Schema(hidden = true)
    private Integer id;

    @NotEmpty(message = "La marca es requerida")
    @Schema(example = "Chevrolet")
    private String brand;

    @NotEmpty(message = "El modelo es requerido")
    @Schema(example = "Corsa")
    private String model;

    @NotEmpty(message = "El año de fabricación es requerido")
    @Schema(example = "2018")
    private Integer fabricationYear;

    @NotEmpty(message = "El tipo es requerido")
    @Schema(example = "USED")
    private CarType type;

    public CarDTO(Integer id, String brand, String model, Integer fabricationYear, CarType type) {
        this.id = id;
        this.brand = brand;
        this.model = model;
        this.fabricationYear = fabricationYear;
        this.type = type;
    }
} 
