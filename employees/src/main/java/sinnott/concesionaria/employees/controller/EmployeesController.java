package sinnott.concesionaria.employees.controller;

import java.util.List;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import sinnott.concesionaria.employees.entitites.EmployeeDTO;
import sinnott.concesionaria.employees.entitites.enums.EmployeeRole;
import sinnott.concesionaria.employees.service.EmployeeService;

@RestController
@RequestMapping("/employees")
@Tag(name = "Empleados", description = "API para la gestión de empleados")
public class EmployeesController {

    private final EmployeeService employeeService;

    public EmployeesController(EmployeeService employeeService) {
        this.employeeService = employeeService;
    }
    
    @GetMapping
    @Operation(summary = "Obtener todos los empleados")
    public ResponseEntity<List<EmployeeDTO>> getEmployees() {
        return ResponseEntity.ok(employeeService.getEmployees());
    }

    @GetMapping("/{id}")
    @Operation(summary = "Obtener un empleado por ID")
    public ResponseEntity<EmployeeDTO> getEmployeeById(@PathVariable Integer id) {
        return ResponseEntity.ok(employeeService.getEmployeeById(id));
    }

    @PostMapping
    @Operation(summary = "Crear un empleado")
    public ResponseEntity<EmployeeDTO> createEmployee(@Valid @RequestBody EmployeeDTO employeeDTO) {
        return ResponseEntity.ok(employeeService.saveEmployee(employeeDTO));
    }

    @PutMapping("/{id}")
    @Operation(summary = "Actualizar un empleado")
    public ResponseEntity<EmployeeDTO> updateEmployee(@PathVariable Integer id, @Valid @RequestBody EmployeeDTO employeeDTO) {
        return ResponseEntity.ok(employeeService.updateEmployee(id, employeeDTO));
    }

    @DeleteMapping("/{id}")
    @Operation(summary = "Eliminar un empleado")
    public ResponseEntity<Void> deleteEmployee(@PathVariable Integer id) {
        employeeService.deleteEmployee(id);
        return ResponseEntity.noContent().build();
    }

    @GetMapping("/search")
    @Operation(summary = "Buscar empleados por cualquier combinación de atributos")
    public ResponseEntity<List<EmployeeDTO>> searchByParams(@RequestParam(required = false) String name,
                                                            @RequestParam(required = false) String lastName,
                                                            @RequestParam(required = false) Integer identityId,
                                                            @RequestParam(required = false) EmployeeRole role,
                                                            @RequestParam(required = false) Integer branchId) {
        return ResponseEntity.ok(employeeService.search(name, lastName, identityId, role, branchId));
    }

    @GetMapping("/exists/{id}")
    @Operation(summary = "Verificar si un empleado existe")
    public ResponseEntity<Boolean> existsEmployee(@PathVariable Integer id) {
        return ResponseEntity.ok(employeeService.existsEmployee(id));
    }
}
