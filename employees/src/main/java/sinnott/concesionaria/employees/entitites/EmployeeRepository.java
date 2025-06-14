package sinnott.concesionaria.employees.entitites;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import sinnott.concesionaria.employees.models.Employee;
import sinnott.concesionaria.employees.models.enums.EmployeeRole;

import java.util.List;
import java.util.Optional;

@Repository
public interface EmployeeRepository extends JpaRepository<Employee, Integer> {

    @Query("SELECT e FROM Employee e " +
       "WHERE (:name IS NULL OR e.name = :name) " +
       "AND (:surname IS NULL OR e.surname = :surname) " +
       "AND (:role IS NULL OR e.role = :role) " +
       "AND (:branchId IS NULL OR e.branchId = :branchId)")
    List<Employee> search(@Param("name") String name, 
                          @Param("surname") String surname, 
                          @Param("role") EmployeeRole role, 
                          @Param("branchId") Long branchId);
}
