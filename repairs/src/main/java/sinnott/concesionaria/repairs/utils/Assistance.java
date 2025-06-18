package sinnott.concesionaria.repairs.utils;

import java.time.LocalDate;

import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Component;
import org.springframework.web.server.ResponseStatusException;

import sinnott.concesionaria.repairs.clients.ClientsClient;
import sinnott.concesionaria.repairs.clients.EmployeeClient;
import sinnott.concesionaria.repairs.clients.SalesClient;
import sinnott.concesionaria.repairs.clients.dto.CarType;
import sinnott.concesionaria.repairs.clients.dto.SaleDTO;
import sinnott.concesionaria.repairs.entities.Repair;
import sinnott.concesionaria.repairs.entities.RepairDTO;
import sinnott.concesionaria.repairs.entities.RepairSummaryDTO;

@Component
public class Assistance {

    private final EmployeeClient employeeClient;
    private final ClientsClient clientsClient;
    private final SalesClient salesClient;

    public Assistance(EmployeeClient employeeClient, ClientsClient clientsClient, SalesClient salesClient) {
        this.employeeClient = employeeClient;
        this.clientsClient = clientsClient;
        this.salesClient = salesClient;
    }

    public boolean checkAssistanceValidations(RepairDTO repairsDTO) {
        try {
            if (!employeeClient.existsEmployee(repairsDTO.getEmployeeId())) {
                throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Empleado no encontrado");
            }
        } catch (Exception ex) {
            throw new ResponseStatusException(HttpStatus.SERVICE_UNAVAILABLE, "Error al consultar microservicio de empleados", ex);
        }
        try {
            if (!clientsClient.existsClient(repairsDTO.getClientId())) {
                throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Cliente no encontrado");
            }
        } catch (Exception ex) {
            throw new ResponseStatusException(HttpStatus.SERVICE_UNAVAILABLE, "Error al consultar microservicio de clientes", ex);
        }
        try {
            if (!salesClient.existsSale(repairsDTO.getSaleId())) {
                throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Venta no encontrada");
            }
        } catch (Exception ex) {
            throw new ResponseStatusException(HttpStatus.INTERNAL_SERVER_ERROR, "Error al consultar base de datos de ventas", ex);
        }
        return true;
    }

    public RepairSummaryDTO format(Repair repair) {
        try {
            SaleDTO sale = salesClient.getSaleById(repair.getSaleId());
            RepairSummaryDTO repairSummaryDTO = new RepairSummaryDTO();
            repairSummaryDTO.setRepairID(repair.getId());
            repairSummaryDTO.setSaleID(sale.getId());
            repairSummaryDTO.setClient(sale.getClient());
            repairSummaryDTO.setEmployee(sale.getEmployee());
            repairSummaryDTO.setCar(sale.getCar());
            repairSummaryDTO.setVehicleKm(repair.getVehicleKm());
            repairSummaryDTO.setRepairDate(repair.getRepairDate());
            repairSummaryDTO.setHasWarranty(checkWarranty(sale.getType(), sale.getSaleDate(), repair.getVehicleKm()));
            return repairSummaryDTO;
        } catch (Exception ex) {
            throw new ResponseStatusException(HttpStatus.SERVICE_UNAVAILABLE, "Error al consultar microservicios para armar resumen de reparación", ex);
        }
    }

    // POR DECISION DE NEGOCIO lA GARANTIA ES DE 3 AÑOS PARA LOS AUTO NUEVOS Y 1 AÑO PARA LOS AUTO USADOS
    private boolean checkWarranty(CarType carType, LocalDate saleDate, Double vehicleKm) {
        LocalDate newCarWarrantyDate = LocalDate.now().minusYears(3);
        LocalDate usedCarWarrantyDate = LocalDate.now().minusYears(1);

        if (carType.equals(CarType.NEW) && 
            (saleDate.isAfter(newCarWarrantyDate) || vehicleKm < 100000)) {
            return true;
        }

        if (carType.equals(CarType.USED) && 
            (saleDate.isAfter(usedCarWarrantyDate) || vehicleKm < 50000)) {
            return true;
        }

        return false;
    }
}