package sinnott.concesionaria.sales.clients.dto;  

import lombok.Data;

@Data
public class EmployeeDTO {
    private Integer id;
    private String name;
    private String lastName;
    private Integer identityId;
    private EmployeeRole role;
    private Integer branchId;
} 