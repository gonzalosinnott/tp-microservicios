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
import sinnott.concesionaria.sales.entities.BillingDTO;
import sinnott.concesionaria.sales.entities.Sale;
import sinnott.concesionaria.sales.entities.SaleDTO;
import sinnott.concesionaria.sales.entities.SaleSummaryDTO;
import sinnott.concesionaria.sales.clients.dto.InventorySaleDTO;

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
        try {
            if (!employeeClient.existsEmployee(sale.getEmployeeId())) {
                throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Empleado no encontrado");
            }
        } catch (Exception ex) {
            throw new ResponseStatusException(HttpStatus.SERVICE_UNAVAILABLE, "Error al consultar microservicio de empleados", ex);
        }

        EmployeeDTO employee;

        try {
            employee = employeeClient.getEmployeeById(sale.getEmployeeId());
        } catch (Exception ex) {
            throw new ResponseStatusException(HttpStatus.SERVICE_UNAVAILABLE, "Error al consultar microservicio de empleados", ex);
        }

        try {
            if (!clientsClient.existsClient(sale.getClientId())) {
                throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Cliente no encontrado");
            }
        } catch (Exception ex) {
            throw new ResponseStatusException(HttpStatus.SERVICE_UNAVAILABLE, "Error al consultar microservicio de clientes", ex);
        }

        List<Optional<InventoryDTO>> filteredInventory;

        try {
            filteredInventory = getInventory(sale.getCarId(), employee.getBranchId());
        } catch (Exception ex) {
            throw new ResponseStatusException(HttpStatus.SERVICE_UNAVAILABLE, "Error al consultar microservicio de stock", ex);
        }

        if(filteredInventory.stream().allMatch(Optional::isEmpty)) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "No existe Stock de dicho auto en la sucursal o en casa central");
        }

        return true;
    }

    public SaleSummaryDTO format(Sale sale) {
        try {
            EmployeeDTO employee = employeeClient.getEmployeeById(sale.getEmployeeId());
            BranchDTO branch = branchClient.getBranchById(employee.getBranchId());
            ClientDTO client = clientsClient.getClientById(sale.getClientId());
            CarDTO car = stockClient.getCarById(sale.getCarId());
            SaleSummaryDTO saleSummaryDTO = new SaleSummaryDTO();
            saleSummaryDTO.setId(sale.getId());
            saleSummaryDTO.setClient(client.getName() + " " + client.getLastName());
            saleSummaryDTO.setEmployee(employee.getName() + " " + employee.getLastName());
            saleSummaryDTO.setBranch(branch.getName() + " - " + branch.getCity() + " - " + branch.getProvince());
            saleSummaryDTO.setCar(car.getBrand() + " - " + car.getModel() + " - " + car.getFabricationYear());
            saleSummaryDTO.setType(car.getType());
            saleSummaryDTO.setAmount(sale.getAmount());
            saleSummaryDTO.setSaleDate(sale.getSaleDate());
            return saleSummaryDTO;
        } catch (Exception ex) {
            throw new ResponseStatusException(HttpStatus.SERVICE_UNAVAILABLE, "Error al consultar microservicios para armar resumen de venta", ex);
        }
    }

    public BillingDTO processSale(Sale sale) {
        try {
            EmployeeDTO employee = employeeClient.getEmployeeById(sale.getEmployeeId());
            BranchDTO branch = branchClient.getBranchById(employee.getBranchId());
            ClientDTO client = clientsClient.getClientById(sale.getClientId());
            CarDTO car = stockClient.getCarById(sale.getCarId());
            List<Optional<InventoryDTO>> filteredInventory = getInventory(sale.getCarId(), employee.getBranchId());
            Optional<InventoryDTO> inventory = filteredInventory.get(0);
            Integer deliveryTime = branch.getDeliveryTimeFromBranch();
            
            if(inventory.get().getBranchId() != employee.getBranchId()) {
                inventory = filteredInventory.get(1);
                deliveryTime = branch.getDeliveryTimeFromCentralWarehouse();
            }

            InventorySaleDTO inventorySaleDTO = new InventorySaleDTO();
            inventorySaleDTO.setCarId(inventory.get().getCarId());
            inventorySaleDTO.setBranchId(inventory.get().getBranchId());
            inventorySaleDTO.setQuantity(1);
           
            try {
                stockClient.updateInventoryForSale(inventorySaleDTO);
            } catch (Exception ex) {
                throw new ResponseStatusException(HttpStatus.SERVICE_UNAVAILABLE, "Error al actualizar inventario por venta en microservicio de stock", ex);
            }
            
            BillingDTO billingDTO = new BillingDTO();
            billingDTO.setId(sale.getId());
            billingDTO.setClient(client.getName() + " " + client.getLastName());
            billingDTO.setEmployee(employee.getName() + " " + employee.getLastName());
            billingDTO.setBranch(branch.getName() + " - " + branch.getCity() + " - " + branch.getProvince());
            billingDTO.setCar(car.getBrand() + " - " + car.getModel() + " - " + car.getFabricationYear() + " - " + car.getType());
            billingDTO.setAmount(sale.getAmount());
            billingDTO.setSaleDate(sale.getSaleDate());
            billingDTO.setDeliveryDate(sale.getSaleDate().plusDays(deliveryTime));
            return billingDTO;
        } catch (Exception ex) {
            throw new ResponseStatusException(HttpStatus.SERVICE_UNAVAILABLE, "Error al procesar venta con microservicios", ex);
        }
    }

    private List<Optional<InventoryDTO>> getInventory(Integer carId, Integer branchId) {
        try {
            List<InventoryDTO> inventory = stockClient.searchInventory(carId, branchId, null);
            List<Optional<InventoryDTO>> filteredInventory = new ArrayList<>();
            filteredInventory.add(inventory.stream()
                                            .filter(item -> item.getBranchId() == branchId && item.getStock() > 0)
                                            .findFirst());
            filteredInventory.add(inventory.stream()
                                            .filter(item -> item.getBranchId() == 1 && item.getStock() > 0)
                                            .findFirst());
            return filteredInventory;
        } catch (Exception ex) {
            throw new ResponseStatusException(HttpStatus.SERVICE_UNAVAILABLE, "Error al consultar inventario en microservicio de stock", ex);
        }
    }
}
