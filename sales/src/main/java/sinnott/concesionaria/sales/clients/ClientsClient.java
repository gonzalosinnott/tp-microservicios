package sinnott.concesionaria.sales.clients;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import sinnott.concesionaria.sales.clients.dto.ClientDTO;

@FeignClient(name = "clients", path = "/clients")
@Component
public interface ClientsClient {

    @GetMapping("/exists/{id}")
    Boolean existsClient(@PathVariable("id") Integer id);

    @GetMapping("/{id}")
    ClientDTO getClientById(@PathVariable("id") Integer id);
}
