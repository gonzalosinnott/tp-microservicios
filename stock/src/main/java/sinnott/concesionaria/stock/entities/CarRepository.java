package sinnott.concesionaria.stock.entities;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import sinnott.concesionaria.stock.models.Car;

@Repository
public interface CarRepository extends JpaRepository<Car, Integer> {
} 