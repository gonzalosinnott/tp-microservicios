package sinnott.concesionaria.sales.utils;

import java.time.LocalDate;
import java.util.Optional;

import org.springframework.http.HttpStatus;
import org.springframework.web.server.ResponseStatusException;

import sinnott.concesionaria.sales.clients.ClientsClient;
import sinnott.concesionaria.sales.clients.EmployeeClient;
import sinnott.concesionaria.sales.clients.StockClient;
import sinnott.concesionaria.sales.clients.dto.CarDTO;
import sinnott.concesionaria.sales.clients.dto.CarType;
import sinnott.concesionaria.sales.clients.dto.ClientDTO;
import sinnott.concesionaria.sales.clients.dto.EmployeeDTO;
import sinnott.concesionaria.sales.entities.repair.Repair;
import sinnott.concesionaria.sales.entities.repair.RepairDTO;
import sinnott.concesionaria.sales.entities.repair.RepairSummaryDTO;
import sinnott.concesionaria.sales.entities.sale.Sale;
import sinnott.concesionaria.sales.entities.sale.SaleDTO;
import sinnott.concesionaria.sales.repository.SaleRepository;

public class Assistance {

    private final EmployeeClient employeeClient;
    private final ClientsClient clientsClient;
    private final StockClient stockClient;
    private final SaleRepository saleRepository;

    public Assistance(EmployeeClient employeeClient, ClientsClient clientsClient, 
                      StockClient stockClient, SaleRepository saleRepository) {
        this.employeeClient = employeeClient;
        this.clientsClient = clientsClient;
        this.stockClient = stockClient;
        this.saleRepository = saleRepository;
    }

    public boolean checkAssistanceValidations(RepairDTO repairsDTO) {
        if (!employeeClient.existsEmployee(repairsDTO.getEmployeeId())) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Empleado no encontrado");
        }

        if (!clientsClient.existsClient(repairsDTO.getClientId())) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Cliente no encontrado");
        }

        if (!saleRepository.existsById(repairsDTO.getSaleId())) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Venta no encontrada");
        }

        return true;
    }

    public RepairSummaryDTO format(Repair repair) {
        EmployeeDTO employee = employeeClient.getEmployeeById(repair.getEmployeeId());
        ClientDTO client = clientsClient.getClientById(repair.getClientId());
        Optional<Sale> sale = saleRepository.findById(repair.getSaleId());
        CarDTO car = stockClient.getCarById(sale.get().getCarId());
   
        RepairSummaryDTO repairSummaryDTO = new RepairSummaryDTO();

        repairSummaryDTO.setRepairID(repair.getId());
        repairSummaryDTO.setSaleID(sale.get().getId());
        repairSummaryDTO.setClient(client.getName() + " " + client.getLastname());
        repairSummaryDTO.setEmployee(employee.getName() + " " + employee.getLastName());
        repairSummaryDTO.setCar(car.getBrand() + " " + car.getType() + " " + car.getModel() + " " + car.getFabricationYear());
        repairSummaryDTO.setVehicleKm(repair.getVehicleKm());
        repairSummaryDTO.setRepairDate(repair.getRepairDate());
        repairSummaryDTO.setHasWarranty(checkWarranty(car.getType(), sale.get().getSaleDate()));

        return repairSummaryDTO;
    }

    // POR DECISION DE NEGOCIO lA GARANTIA ES DE 3 AÑOS PARA LOS AUTO NUEVOS Y 1 AÑO PARA LOS AUTO USADOS
    private boolean checkWarranty(CarType carType, LocalDate saleDate) {
        LocalDate newCarWarrantyDate = LocalDate.now().minusYears(3);
        LocalDate usedCarWarrantyDate = LocalDate.now().minusYears(1);

        if (carType.equals(CarType.NEW) && saleDate.isAfter(newCarWarrantyDate)) {
            return true;
        }

        if (carType.equals(CarType.USED) && saleDate.isAfter(usedCarWarrantyDate)) {
            return true;
        }

        return false;
    }
}