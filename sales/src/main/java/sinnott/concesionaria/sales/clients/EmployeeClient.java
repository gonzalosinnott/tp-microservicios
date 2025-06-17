package sinnott.concesionaria.sales.clients;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import sinnott.concesionaria.sales.clients.dto.EmployeeDTO;

@FeignClient(name = "employees", path = "/employees")
public interface EmployeeClient {

    @GetMapping("/exists/{id}")
    Boolean existsEmployee(@PathVariable("id") Integer id);

    @GetMapping("/{id}")
    EmployeeDTO getEmployeeById(@PathVariable("id") Integer id);
}
