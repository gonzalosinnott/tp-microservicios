package sinnott.concesionaria.repairs.clients;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@FeignClient(name = "clients", path = "/clients")
public interface ClientsClient {

    @GetMapping("/exists/{id}")
    Boolean existsClient(@PathVariable("id") Integer id);

}