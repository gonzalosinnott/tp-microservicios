package sinnott.concesionaria.sales.repository;

import java.time.LocalDate;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import sinnott.concesionaria.sales.entities.sale.Sale;


@Repository
public interface SaleRepository extends JpaRepository<Sale, Integer> {

    @Query("SELECT s FROM Sale s " +
           "WHERE (:employeeId IS NULL OR s.employeeId = :employeeId) " +
           "AND (:carId IS NULL OR s.carId = :carId) " +
           "AND (:clientId IS NULL OR s.clientId = :clientId) " +
           "AND (:saleDate IS NULL OR s.saleDate = :saleDate)")
    List<Sale> search(@Param("employeeId") Integer employeeId,
                      @Param("carId") Integer carId,
                      @Param("clientId") Integer clientId,
                      @Param("saleDate") LocalDate saleDate);
} 