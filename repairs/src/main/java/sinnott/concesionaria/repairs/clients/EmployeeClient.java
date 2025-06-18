package sinnott.concesionaria.repairs.clients;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@FeignClient(name = "employees", path = "/employees")
public interface EmployeeClient {

    @GetMapping("/exists/{id}")
    Boolean existsEmployee(@PathVariable("id") Integer id);
} 