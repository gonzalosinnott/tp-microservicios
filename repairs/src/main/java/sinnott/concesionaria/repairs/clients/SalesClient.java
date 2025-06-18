package sinnott.concesionaria.repairs.clients;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import sinnott.concesionaria.repairs.clients.dto.SaleDTO;


@FeignClient(name = "sales", path = "/sales")
public interface SalesClient {


    @GetMapping("/exists/{id}")
    boolean existsSale(@PathVariable("id") Integer id);

    @GetMapping("/billing/{id}")
    SaleDTO getSaleById(@PathVariable("id") Integer id);
} 