package sinnott.concesionaria.employees.service;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.stereotype.Service;
import org.springframework.http.HttpStatus;
import org.springframework.web.server.ResponseStatusException;

import sinnott.concesionaria.employees.clients.BranchClient;
import sinnott.concesionaria.employees.entitites.Employee;
import sinnott.concesionaria.employees.entitites.EmployeeDTO;
import sinnott.concesionaria.employees.entitites.enums.EmployeeRole;
import sinnott.concesionaria.employees.repository.EmployeeRepository;
import sinnott.concesionaria.employees.utils.EmployeeMapper;
import sinnott.concesionaria.employees.service.IEmployeeService;

@Service
public class EmployeeService implements IEmployeeService {

    private final EmployeeRepository employeeRepository;
    private final BranchClient branchClient;

    public EmployeeService(EmployeeRepository employeeRepository, 
                           BranchClient branchClient) {
        this.employeeRepository = employeeRepository;
        this.branchClient = branchClient;
    }

    @Override
    public List<EmployeeDTO> getEmployees() {
        try {
            return employeeRepository.findAll()
                .stream()
                .map(EmployeeMapper::toDTO)
                .collect(Collectors.toList());
        } catch (Exception ex) {
            throw new ResponseStatusException(HttpStatus.INTERNAL_SERVER_ERROR, "Error al obtener empleados", ex);
        }
    }

    @Override
    public EmployeeDTO getEmployeeById(Integer id) {
        try {
            return EmployeeMapper.toDTO(employeeRepository.findById(id)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Empleado no encontrado")));
        } catch (Exception ex) {
            throw new ResponseStatusException(HttpStatus.INTERNAL_SERVER_ERROR, "Error al obtener empleado", ex);
        }
    }

    @Override
    public EmployeeDTO saveEmployee(EmployeeDTO employeeDTO) {
        try {
            try {
                if (!branchClient.existsBranch(employeeDTO.getBranchId())) {
                    throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Sucursal no encontrada");
                }
            } catch (Exception ex) {
                throw new ResponseStatusException(HttpStatus.SERVICE_UNAVAILABLE, "Error al consultar sucursal", ex);
            }
            return EmployeeMapper.toDTO(employeeRepository.save(EmployeeMapper.toEntity(employeeDTO)));
        } catch (Exception ex) {
            throw new ResponseStatusException(HttpStatus.INTERNAL_SERVER_ERROR, "Error al guardar empleado", ex);
        }
    }

    @Override
    public EmployeeDTO updateEmployee(Integer id, EmployeeDTO employeeDTO) {
        try {
            try {
                if (!branchClient.existsBranch(employeeDTO.getBranchId())) {
                    throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Sucursal no encontrada");
                }
            } catch (Exception ex) {
                throw new ResponseStatusException(HttpStatus.SERVICE_UNAVAILABLE, "Error al consultar sucursal", ex);
            }
            Employee employee = employeeRepository.findById(id)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Empleado no encontrado"));
            employee.setName(employeeDTO.getName());
            employee.setLastName(employeeDTO.getLastName());
            employee.setIdentityId(employeeDTO.getIdentityId());
            employee.setRole(employeeDTO.getRole());
            employee.setBranchId(employeeDTO.getBranchId());
            return EmployeeMapper.toDTO(employeeRepository.save(employee));
        } catch (Exception ex) {
            throw new ResponseStatusException(HttpStatus.INTERNAL_SERVER_ERROR, "Error al actualizar empleado", ex);
        }
    }

    @Override
    public void deleteEmployee(Integer id) {
        try {
            if (!employeeRepository.existsById(id)) {
                throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Empleado no encontrado");
            }
            employeeRepository.deleteById(id);
        } catch (Exception ex) {
            throw new ResponseStatusException(HttpStatus.INTERNAL_SERVER_ERROR, "Error al eliminar empleado", ex);
        }
    }

    @Override
    public List<EmployeeDTO> search(String name, String lastName, Integer identityId, EmployeeRole role, Integer branchId) {
        try {
            return employeeRepository.search(name, lastName, identityId, role, branchId)
                .stream()
                .map(EmployeeMapper::toDTO)
                .collect(Collectors.toList());
        } catch (Exception ex) {
            throw new ResponseStatusException(HttpStatus.INTERNAL_SERVER_ERROR, "Error al buscar empleados", ex);
        }
    }

    @Override
    public Boolean existsEmployee(Integer id) {
        try {
            return employeeRepository.existsById(id);
        } catch (Exception ex) {
            throw new ResponseStatusException(HttpStatus.INTERNAL_SERVER_ERROR, "Error al verificar existencia de empleado", ex);
        }
    }
}
