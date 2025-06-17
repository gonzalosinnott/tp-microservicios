package sinnott.concesionaria.employees.utils;

import sinnott.concesionaria.employees.entitites.Employee;
import sinnott.concesionaria.employees.entitites.EmployeeDTO;

public class EmployeeMapper {

    public static Employee toEntity(EmployeeDTO employeeDTO) {
        return new Employee(employeeDTO.getName(), 
                            employeeDTO.getLastName(),
                            employeeDTO.getIdentityId(),
                            employeeDTO.getRole(), 
                            employeeDTO.getBranchId());
    }

    public static EmployeeDTO toDTO(Employee employee) {
        return new EmployeeDTO(employee.getId(), 
                                employee.getName(), 
                                employee.getLastName(), 
                                employee.getIdentityId(),
                                employee.getRole(), 
                                employee.getBranchId());
    }
}
