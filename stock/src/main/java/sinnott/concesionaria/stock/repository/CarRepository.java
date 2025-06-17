package sinnott.concesionaria.stock.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import sinnott.concesionaria.stock.entities.car.Car;
import sinnott.concesionaria.stock.entities.car.enums.CarType;

@Repository
public interface CarRepository extends JpaRepository<Car, Integer> {
    @Query("SELECT c FROM Car c WHERE " +
           "(:brand IS NULL OR LOWER(c.brand) LIKE LOWER(CONCAT('%', :brand, '%'))) AND " +
           "(:model IS NULL OR LOWER(c.model) LIKE LOWER(CONCAT('%', :model, '%'))) AND " +
           "(:year IS NULL OR c.fabricationYear = :year) AND " +
           "(:type IS NULL OR c.type = :type)" +
           "ORDER BY c.brand, c.model, c.fabricationYear")
    List<Car> search(String brand, String model, Integer year, CarType type);
} 