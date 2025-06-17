package sinnott.concesionaria.employees.entitites;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.Id;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import sinnott.concesionaria.employees.entitites.enums.EmployeeRole;
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
    
    private String lastName;

    @Column(unique = true)
    private Integer identityId;
   
    @Enumerated(EnumType.STRING)
    private EmployeeRole role;

   
    private Integer branchId;

    public Employee(String name, String lastName, Integer identityId, EmployeeRole role, Integer branchId) {
        this.name = name;
        this.lastName = lastName;
        this.identityId = identityId;
        this.role = role;
        this.branchId = branchId;
    }
}
