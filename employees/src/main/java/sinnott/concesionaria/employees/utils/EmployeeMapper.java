package sinnott.concesionaria.employees.utils;

import sinnott.concesionaria.employees.entitites.EmployeeDTO;
import sinnott.concesionaria.employees.models.Employee;

public class EmployeeMapper {

    public static Employee toEntity(EmployeeDTO employeeDTO) {
        return new Employee(employeeDTO.getName(), 
                            employeeDTO.getSurname(),
                            employeeDTO.getIdentityId(),
                            employeeDTO.getRole(), 
                            employeeDTO.getBranchId());
    }

    public static EmployeeDTO toDTO(Employee employee) {
        return new EmployeeDTO(employee.getId(), 
                                employee.getName(), 
                                employee.getSurname(), 
                                employee.getIdentityId(),
                                employee.getRole(), 
                                employee.getBranchId());
    }
}
