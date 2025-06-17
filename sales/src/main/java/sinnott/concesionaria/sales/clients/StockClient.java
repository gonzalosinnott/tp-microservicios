package sinnott.concesionaria.sales.clients;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import java.util.List;

import sinnott.concesionaria.sales.clients.dto.CarDTO;
import sinnott.concesionaria.sales.clients.dto.InventoryDTO;

@FeignClient(name = "stock", path = "/stock")
@Component
public interface StockClient {

    @GetMapping("/inventory/search")
    List<InventoryDTO> searchInventory(@RequestParam("carId") Integer carId, 
                                       @RequestParam("branchId") Integer branchId, 
                                       @RequestParam("stock") Integer stock);

    @GetMapping("/catalog/cars/{id}")
    CarDTO getCarById(@PathVariable("id") Integer id);

    @PutMapping("/inventory/{id}")
    InventoryDTO updateInventory(@PathVariable("id") Integer id, @RequestBody InventoryDTO inventoryDTO);
}
