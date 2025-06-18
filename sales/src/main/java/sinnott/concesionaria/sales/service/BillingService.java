package sinnott.concesionaria.sales.service;

import java.time.LocalDate;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import sinnott.concesionaria.sales.entities.BillingDTO;
import sinnott.concesionaria.sales.entities.Sale;
import sinnott.concesionaria.sales.entities.SaleDTO;
import sinnott.concesionaria.sales.entities.SaleSummaryDTO;
import sinnott.concesionaria.sales.repository.SaleRepository;
import sinnott.concesionaria.sales.utils.Billing;
import sinnott.concesionaria.sales.utils.SaleMapper;

@Service
public class BillingService implements IBillingService {

    private final SaleRepository saleRepository;
    private final Billing billing;

    public BillingService(SaleRepository saleRepository, Billing billing) {
        this.saleRepository = saleRepository;
        this.billing = billing;
    }

    @Override
    public List<SaleSummaryDTO> getSales() {
        try {
            return saleRepository.findAll()
                .stream()
                .map(billing::format)
                .collect(Collectors.toList());
        } catch (Exception ex) {
            throw new ResponseStatusException(HttpStatus.INTERNAL_SERVER_ERROR, "Error al obtener ventas", ex);
        }
    }

    @Override
    public SaleSummaryDTO getSaleById(Integer id) {
        try {
            Sale sale = saleRepository.findById(id)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Venta no encontrada"));
            return billing.format(sale);
        } catch (Exception ex) {
            throw new ResponseStatusException(HttpStatus.INTERNAL_SERVER_ERROR, "Error al obtener venta", ex);
        }
    }

    @Override
    public BillingDTO saveSale(SaleDTO saleDTO) {
        try {
            if (billing.checkBillingValidations(saleDTO)) {
                return billing.processSale(saleRepository.save(SaleMapper.toEntity(saleDTO)));
            }
            return null;
        } catch (Exception ex) {
            throw new ResponseStatusException(HttpStatus.INTERNAL_SERVER_ERROR, "Error al guardar venta", ex);
        }
    }

    @Override
    public void deleteSale(Integer id) {
        try {
            if (!saleRepository.existsById(id)) {
                throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Venta no encontrada");
            }
            saleRepository.deleteById(id);
        } catch (Exception ex) {
            throw new ResponseStatusException(HttpStatus.INTERNAL_SERVER_ERROR, "Error al eliminar venta", ex);
        }
    }

    @Override
    public List<SaleSummaryDTO> search(Integer employeeId, Integer carId, Integer clientId, LocalDate saleDate) {
        try {
            return saleRepository.search(employeeId, carId, clientId, saleDate)
                .stream()
                .map(billing::format)
                .collect(Collectors.toList());
        } catch (Exception ex) {
            throw new ResponseStatusException(HttpStatus.INTERNAL_SERVER_ERROR, "Error al buscar ventas", ex);
        }
    }

    @Override
    public boolean existsSale(Integer id) {
        return saleRepository.existsById(id);
    }
} 