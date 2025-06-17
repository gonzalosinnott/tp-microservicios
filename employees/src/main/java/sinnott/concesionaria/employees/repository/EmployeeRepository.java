package sinnott.concesionaria.employees.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import sinnott.concesionaria.employees.entitites.Employee;
import sinnott.concesionaria.employees.entitites.enums.EmployeeRole;

import java.util.List;

@Repository
public interface EmployeeRepository extends JpaRepository<Employee, Integer> {

    @Query("SELECT e FROM Employee e " +
           "WHERE (:name IS NULL OR LOWER(e.name) LIKE LOWER(CONCAT('%', :name, '%'))) " +
           "AND (:lastName IS NULL OR LOWER(e.lastName) LIKE LOWER(CONCAT('%', :lastName, '%'))) " +
           "AND (:identityId IS NULL OR e.identityId = :identityId) " +
           "AND (:role IS NULL OR e.role = :role) " +
           "AND (:branchId IS NULL OR e.branchId = :branchId)")
    List<Employee> search(@Param("name") String name, 
                          @Param("lastName") String lastName, 
                          @Param("identityId") Integer identityId,
                          @Param("role") EmployeeRole role, 
                          @Param("branchId") Integer branchId);
}