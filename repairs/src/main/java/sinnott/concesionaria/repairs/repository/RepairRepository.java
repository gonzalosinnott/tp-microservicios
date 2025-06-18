package sinnott.concesionaria.repairs.repository;

import java.time.LocalDate;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import sinnott.concesionaria.repairs.entities.Repair;

@Repository
public interface RepairRepository extends JpaRepository<Repair, Integer> {

    @Query("SELECT r FROM Repair r " +
           "WHERE (:employeeId IS NULL OR r.employeeId = :employeeId) " +
           "AND (:saleId IS NULL OR r.saleId = :saleId) " +
           "AND (:repairDate IS NULL OR r.repairDate = :repairDate)")
    List<Repair> search(@Param("employeeId") Integer employeeId,
                        @Param("saleId") Integer saleId,
                        @Param("repairDate") LocalDate repairDate);
}