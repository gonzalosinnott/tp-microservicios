package sinnott.concesionaria.stock.clients;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@FeignClient(name = "branchs", path = "/branchs")
public interface BranchClient {

    @GetMapping("/exists/{id}")
    boolean existsBranch(@PathVariable("id") Integer id);

} 