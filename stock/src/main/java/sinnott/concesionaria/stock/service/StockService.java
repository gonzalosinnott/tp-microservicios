package sinnott.concesionaria.stock.service;

import org.springframework.stereotype.Service;

import sinnott.concesionaria.stock.entities.StockDTO;
import sinnott.concesionaria.stock.entities.StockRepository;
import sinnott.concesionaria.stock.models.Stock;
import sinnott.concesionaria.stock.utils.StockMapper;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class StockService {
    private final StockRepository stockRepository;

    public StockService(StockRepository stockRepository) {
        this.stockRepository = stockRepository;
    }

    public List<StockDTO> getAllStocks() {
        return stockRepository.findAll().stream().map(StockMapper::toDTO).collect(Collectors.toList());
    }

    public StockDTO getStockById(Integer id) {
        return stockRepository.findById(id).map(StockMapper::toDTO).orElseThrow(() -> new RuntimeException("Stock not found"));
    }

    public StockDTO saveStock(StockDTO stockDTO) {
        Stock stock = StockMapper.toEntity(stockDTO);
        return StockMapper.toDTO(stockRepository.save(stock));
    }

    public StockDTO updateStock(Integer id, StockDTO stockDTO) {
        Stock stock = stockRepository.findById(id).orElseThrow(() -> new RuntimeException("Stock not found"));
        stock.setCarId(stockDTO.getCarId());
        stock.setBranchId(stockDTO.getBranchId());
        stock.setStock(stockDTO.getStock());
        return StockMapper.toDTO(stockRepository.save(stock));
    }

    public void deleteStock(Integer id) {
        stockRepository.deleteById(id);
    }

    public List<StockDTO> search(Integer carId, Integer branchId, Integer stockValue) {
        return stockRepository.findAll().stream()
            .filter(s -> carId == null || s.getCarId().equals(carId))
            .filter(s -> branchId == null || s.getBranchId().equals(branchId))
            .filter(s -> stockValue == null || s.getStock().equals(stockValue))
            .map(StockMapper::toDTO)
            .collect(Collectors.toList());
    }
} 