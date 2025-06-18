package sinnott.concesionaria.sales.clients;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import java.util.List;

import sinnott.concesionaria.sales.clients.dto.CarDTO;
import sinnott.concesionaria.sales.clients.dto.InventoryDTO;
import sinnott.concesionaria.sales.clients.dto.InventorySaleDTO;

@FeignClient(name = "stock", path = "/stock")
public interface StockClient {

    @GetMapping("/inventory/search")
    List<InventoryDTO> searchInventory(@RequestParam("carId") Integer carId, 
                                       @RequestParam("branchId") Integer branchId, 
                                       @RequestParam("stock") Integer stock);

    @GetMapping("/catalog/cars/{id}")
    CarDTO getCarById(@PathVariable("id") Integer id);


    @PostMapping("/inventory/sale")
    InventoryDTO updateInventoryForSale(@RequestBody InventorySaleDTO inventorySaleDTO);
}
