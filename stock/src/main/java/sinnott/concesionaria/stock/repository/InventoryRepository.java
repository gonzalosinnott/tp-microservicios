package sinnott.concesionaria.stock.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import sinnott.concesionaria.stock.entities.inventory.Inventory;

@Repository
public interface InventoryRepository extends JpaRepository<Inventory, Integer> {

    @Query("SELECT i FROM Inventory i WHERE " +
           "(:carId IS NULL OR i.carId = :carId) AND " +
           "(:branchId IS NULL OR i.branchId = :branchId) AND " +
           "(:stock IS NULL OR i.stock = :stock)")
    List<Inventory> search(@Param("carId") Integer carId,
                           @Param("branchId") Integer branchId, 
                           @Param("stock") Integer stock);

    Optional<Inventory> findByCarIdAndBranchId(Integer carId, Integer branchId);
} 