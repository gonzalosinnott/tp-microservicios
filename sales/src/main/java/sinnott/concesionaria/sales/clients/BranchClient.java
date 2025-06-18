package sinnott.concesionaria.sales.clients;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import sinnott.concesionaria.sales.clients.dto.BranchDTO;

@FeignClient(name = "branchs", path = "/branchs")
public interface BranchClient {

    @GetMapping("/{id}")
    BranchDTO getBranchById(@PathVariable("id") Integer id);
}
