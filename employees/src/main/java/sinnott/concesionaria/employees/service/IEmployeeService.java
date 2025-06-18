package sinnott.concesionaria.employees.service;

import sinnott.concesionaria.employees.entitites.EmployeeDTO;
import sinnott.concesionaria.employees.entitites.enums.EmployeeRole;
import java.util.List;

public interface IEmployeeService {
    List<EmployeeDTO> getEmployees();
    EmployeeDTO getEmployeeById(Integer id);
    EmployeeDTO saveEmployee(EmployeeDTO employeeDTO);
    EmployeeDTO updateEmployee(Integer id, EmployeeDTO employeeDTO);
    void deleteEmployee(Integer id);
    List<EmployeeDTO> search(String name, String lastName, Integer identityId, EmployeeRole role, Integer branchId);
    Boolean existsEmployee(Integer id);
} 