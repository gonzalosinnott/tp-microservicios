package sinnott.concesionaria.employees.service;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.stereotype.Service;
import org.springframework.http.HttpStatus;
import org.springframework.web.server.ResponseStatusException;

import sinnott.concesionaria.employees.clients.BranchClient;
import sinnott.concesionaria.employees.entitites.EmployeeDTO;
import sinnott.concesionaria.employees.entitites.EmployeeRepository;
import sinnott.concesionaria.employees.models.Employee;
import sinnott.concesionaria.employees.models.enums.EmployeeRole;
import sinnott.concesionaria.employees.utils.EmployeeMapper;

@Service
public class EmployeeService {

    private final EmployeeRepository employeeRepository;
    private final BranchClient branchClient;

    public EmployeeService(EmployeeRepository employeeRepository, 
                           BranchClient branchClient) {
        this.employeeRepository = employeeRepository;
        this.branchClient = branchClient;
    }

    public List<EmployeeDTO> getEmployees() {
        return employeeRepository.findAll()
                                 .stream()
                                 .map(EmployeeMapper::toDTO)
                                 .collect(Collectors.toList());
    }

    public EmployeeDTO getEmployeeById(Integer id) {
        return EmployeeMapper.toDTO(employeeRepository.findById(id)
            .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Empleado no encontrado")));
    }

    public EmployeeDTO saveEmployee(EmployeeDTO employeeDTO) {
        if (!branchClient.existsBranch(employeeDTO.getBranchId().intValue())) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Sucursal no encontrada");
        }
        return EmployeeMapper.toDTO(employeeRepository.save(EmployeeMapper.toEntity(employeeDTO)));
    }

    public EmployeeDTO updateEmployee(Integer id, EmployeeDTO employeeDTO) {
        Employee employee = employeeRepository.findById(id)
            .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Empleado no encontrado"));
        employee.setName(employeeDTO.getName());
        employee.setSurname(employeeDTO.getSurname());
        employee.setRole(employeeDTO.getRole());
        employee.setBranchId(employeeDTO.getBranchId());
        return EmployeeMapper.toDTO(employeeRepository.save(employee));
    }

    public void deleteEmployee(Integer id) {
        if (!employeeRepository.existsById(id)) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Empleado no encontrado");
        }
        employeeRepository.deleteById(id);
    }

    public List<EmployeeDTO> search(String name, String surname, EmployeeRole role, Long branchId) {
        return employeeRepository.search(name, surname, role, branchId).stream()
                                                                       .map(EmployeeMapper::toDTO)
                                                                       .collect(Collectors.toList());
    }
}
