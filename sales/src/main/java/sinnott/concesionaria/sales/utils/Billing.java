package sinnott.concesionaria.sales.utils;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Component;
import org.springframework.web.server.ResponseStatusException;

import sinnott.concesionaria.sales.clients.BranchClient;
import sinnott.concesionaria.sales.clients.ClientsClient;
import sinnott.concesionaria.sales.clients.EmployeeClient;
import sinnott.concesionaria.sales.clients.StockClient;
import sinnott.concesionaria.sales.clients.dto.BranchDTO;
import sinnott.concesionaria.sales.clients.dto.CarDTO;
import sinnott.concesionaria.sales.clients.dto.ClientDTO;
import sinnott.concesionaria.sales.clients.dto.EmployeeDTO;
import sinnott.concesionaria.sales.clients.dto.InventoryDTO;
import sinnott.concesionaria.sales.entities.sale.BillingDTO;
import sinnott.concesionaria.sales.entities.sale.Sale;
import sinnott.concesionaria.sales.entities.sale.SaleDTO;
import sinnott.concesionaria.sales.entities.sale.SaleSummaryDTO;

@Component
public class Billing {

    private final EmployeeClient employeeClient;
    private final ClientsClient clientsClient;
    private final StockClient stockClient;
    private final BranchClient branchClient;

    public Billing(EmployeeClient employeeClient, ClientsClient clientsClient, 
                   StockClient stockClient, BranchClient branchClient) {
        this.employeeClient = employeeClient;
        this.clientsClient = clientsClient;
        this.stockClient = stockClient;
        this.branchClient = branchClient;
    }

    public Boolean checkBillingValidations(SaleDTO sale) {
        if (!employeeClient.existsEmployee(sale.getEmployeeId())) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Empleado no encontrado");
        }

        EmployeeDTO employee = employeeClient.getEmployeeById(sale.getEmployeeId());
        
        if (!clientsClient.existsClient(sale.getClientId())) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Cliente no encontrado");
        }

        List<Optional<InventoryDTO>> filteredInventory = getInventory(sale.getCarId(), employee.getBranchId());

        if(filteredInventory.stream().allMatch(Optional::isEmpty)) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "No existe Stock de dicho auto en la sucursal o en casa central");
        }

        return true;
    }

    public SaleSummaryDTO format(Sale sale) {
        EmployeeDTO employee = employeeClient.getEmployeeById(sale.getEmployeeId());
        BranchDTO branch = branchClient.getBranchById(employee.getBranchId());
        ClientDTO client = clientsClient.getClientById(sale.getClientId());
        CarDTO car = stockClient.getCarById(sale.getCarId());

        SaleSummaryDTO saleSummaryDTO = new SaleSummaryDTO();

        saleSummaryDTO.setSaleID(sale.getId());
        saleSummaryDTO.setClient(client.getName() + " " + client.getLastname());
        saleSummaryDTO.setEmployee(employee.getName() + " " + employee.getLastName());
        saleSummaryDTO.setBranch(branch.getName() + " - " + branch.getCity() + " - " + branch.getProvince());
        saleSummaryDTO.setCar(car.getBrand() + " - " + car.getType() + " - " + car.getModel() + " - " + car.getFabricationYear());
        saleSummaryDTO.setAmount(sale.getAmount());
        saleSummaryDTO.setSaleDate(sale.getSaleDate());

        return saleSummaryDTO;
    }

    public BillingDTO processSale(Sale sale) {
        EmployeeDTO employee = employeeClient.getEmployeeById(sale.getEmployeeId());
        BranchDTO branch = branchClient.getBranchById(employee.getBranchId());
        ClientDTO client = clientsClient.getClientById(sale.getClientId());
        CarDTO car = stockClient.getCarById(sale.getCarId());
        List<Optional<InventoryDTO>> filteredInventory = getInventory(sale.getCarId(), employee.getBranchId());
        
        ///Por decision de negocio se decide que se use primero el stock de la sucursal y luego el de casa central
        Optional<InventoryDTO> inventory = filteredInventory.get(0);

        Integer deliveryTime = branch.getDeliveryTimeFromBranch();
        
        if(inventory.get().getBranchId() != employee.getBranchId()) {
            inventory = filteredInventory.get(1);
            deliveryTime = branch.getDeliveryTimeFromCentralWarehouse();
        }

        updateInventory(inventory.get());

        BillingDTO billingDTO = new BillingDTO();
        
        billingDTO.setSaleID(sale.getId());
        billingDTO.setClient(client.getName() + " " + client.getLastname());
        billingDTO.setEmployee(employee.getName() + " " + employee.getLastName());
        billingDTO.setBranch(branch.getName() + " - " + branch.getCity() + " - " + branch.getProvince());
        billingDTO.setCar(car.getBrand() + " - " + car.getType() + " - " + car.getModel() + " - " + car.getFabricationYear());
        billingDTO.setAmount(sale.getAmount());
        billingDTO.setSaleDate(sale.getSaleDate());
        billingDTO.setDeliveryDate(sale.getSaleDate().plusDays(deliveryTime));

        return billingDTO;
    }

    private List<Optional<InventoryDTO>> getInventory(Integer carId, Integer branchId) {
        List<InventoryDTO> inventory = stockClient.searchInventory(carId, branchId, null);
        List<Optional<InventoryDTO>> filteredInventory = new ArrayList<>();

        filteredInventory.add(inventory.stream()
                                        .filter(item -> item.getBranchId() == branchId && item.getStock() > 0)
                                        .findFirst());

        filteredInventory.add(inventory.stream()
                                        .filter(item -> item.getBranchId() == 1 && item.getStock() > 0)
                                        .findFirst());

        return filteredInventory;
    }

    private void updateInventory(InventoryDTO inventoryDTO) {
        stockClient.updateInventory(inventoryDTO.getId(), inventoryDTO);
    }

}
