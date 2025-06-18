package sinnott.concesionaria.sales.service;

import sinnott.concesionaria.sales.entities.BillingDTO;
import sinnott.concesionaria.sales.entities.SaleDTO;
import sinnott.concesionaria.sales.entities.SaleSummaryDTO;
import java.time.LocalDate;
import java.util.List;

public interface IBillingService {
    List<SaleSummaryDTO> getSales();
    SaleSummaryDTO getSaleById(Integer id);
    BillingDTO saveSale(SaleDTO saleDTO);
    void deleteSale(Integer id);
    List<SaleSummaryDTO> search(Integer employeeId, Integer carId, Integer clientId, LocalDate saleDate);
    boolean existsSale(Integer id);
} 