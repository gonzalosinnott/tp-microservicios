package sinnott.concesionaria.stock.clients;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@FeignClient(name = "branchs", path = "/branchs")
@Component
public interface BranchClient {

    @GetMapping("/exists/{id}")
    public Boolean existsBranch(@PathVariable Integer id);

} 