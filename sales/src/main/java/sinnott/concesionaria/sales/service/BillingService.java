package sinnott.concesionaria.sales.service;

import java.time.LocalDate;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import sinnott.concesionaria.sales.entities.sale.BillingDTO;
import sinnott.concesionaria.sales.entities.sale.Sale;
import sinnott.concesionaria.sales.entities.sale.SaleDTO;
import sinnott.concesionaria.sales.entities.sale.SaleSummaryDTO;
import sinnott.concesionaria.sales.repository.SaleRepository;
import sinnott.concesionaria.sales.utils.Billing;
import sinnott.concesionaria.sales.utils.SaleMapper;

@Service
public class BillingService {

    private final SaleRepository saleRepository;
    private final Billing billing;

    public BillingService(SaleRepository saleRepository, Billing billing) {
        this.saleRepository = saleRepository;
        this.billing = billing;
    }

    public List<SaleSummaryDTO> getSales() {
        return saleRepository.findAll()
                             .stream()
                             .map(billing::format)
                             .collect(Collectors.toList());
    }

    public SaleSummaryDTO getSaleById(Integer id) {
        Sale sale = saleRepository.findById(id)
                                  .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Venta no encontrada"));
        return billing.format(sale);
    }

    public BillingDTO saveSale(SaleDTO saleDTO) {
        if (billing.checkBillingValidations(saleDTO)) {
            return billing.processSale(saleRepository.save(SaleMapper.toEntity(saleDTO)));
        }
        return null;
    }

    public void deleteSale(Integer id) {
        if (!saleRepository.existsById(id)) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Venta no encontrada");
        }
        saleRepository.deleteById(id);
    }

    public List<SaleSummaryDTO> search(Integer employeeId, Integer carId, Integer clientId, LocalDate saleDate) {
        return saleRepository.search(employeeId, carId, clientId, saleDate)
                             .stream()
                             .map(billing::format)
                             .collect(Collectors.toList());
    }
} 