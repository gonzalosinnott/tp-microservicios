package sinnott.concesionaria.employees.models;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.Id;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import sinnott.concesionaria.employees.models.enums.EmployeeRole;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
public class Employee {
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;    
    
    private String name;
    
    private String surname;

    @Column(unique = true)
    private Integer identityId;
   
    @Enumerated(EnumType.STRING)
    private EmployeeRole role;

   
    private Long branchId;

    public Employee(String name, String surname, Integer identityId, EmployeeRole role, Long branchId) {
        this.name = name;
        this.surname = surname;
        this.identityId = identityId;
        this.role = role;
        this.branchId = branchId;
    }
}
