package sinnott.concesionaria.stock.entities;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import sinnott.concesionaria.stock.models.Stock;

@Repository
public interface StockRepository extends JpaRepository<Stock, Integer> {
} 