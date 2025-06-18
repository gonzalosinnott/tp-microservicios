package sinnott.concesionaria.stock.service;

import org.springframework.http.HttpStatus;
import org.springframework.web.server.ResponseStatusException;
import org.springframework.stereotype.Service;

import sinnott.concesionaria.stock.clients.BranchClient;
import sinnott.concesionaria.stock.entities.inventory.Inventory;
import sinnott.concesionaria.stock.entities.inventory.InventoryDTO;
import sinnott.concesionaria.stock.repository.CarRepository;
import sinnott.concesionaria.stock.repository.InventoryRepository;
import sinnott.concesionaria.stock.utils.InventoryMapper;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class InventoryService {
    private final InventoryRepository stockRepository;
    private final CarRepository carRepository;
    private final BranchClient branchClient;

    public InventoryService(InventoryRepository stockRepository, CarRepository carRepository, BranchClient branchClient) {
        this.stockRepository = stockRepository;
        this.carRepository = carRepository;
        this.branchClient = branchClient;
    }

    public List<InventoryDTO> getAllInventory() {
        return stockRepository.findAll()
                              .stream()
                              .map(InventoryMapper::toDTO)
                              .collect(Collectors.toList());
    }

    public InventoryDTO getInventoryById(Integer id) {
        return InventoryMapper.toDTO(stockRepository.findById(id)
            .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Inventario no encontrado")));
    }

    public InventoryDTO saveInventory(InventoryDTO inventoryDTO) {
        if (!carRepository.existsById(inventoryDTO.getCarId())) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Auto no encontrado en catalogo");
        }
        if (!branchClient.existsBranch(inventoryDTO.getBranchId())) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Sucursal no encontrada");
        }
        return InventoryMapper.toDTO(stockRepository.save(InventoryMapper.toEntity(inventoryDTO)));
    }

    public InventoryDTO updateInventory(Integer id, InventoryDTO inventoryDTO) {
        if (!carRepository.existsById(inventoryDTO.getCarId())) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Auto no encontrado en catalogo");
        }
        if (!branchClient.existsBranch(inventoryDTO.getBranchId())) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Sucursal no encontrada");
        }
        Inventory stock = stockRepository.findById(id)
            .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Inventario no encontrado"));
        stock.setCarId(inventoryDTO.getCarId());
        stock.setBranchId(inventoryDTO.getBranchId());
        stock.setStock(inventoryDTO.getStock());
        return InventoryMapper.toDTO(stockRepository.save(stock));
    }

    public void deleteInventory(Integer id) {
        if (!stockRepository.existsById(id)) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Inventario no encontrado");
        }
        stockRepository.deleteById(id);
    }

    public List<InventoryDTO> search(Integer carId, Integer branchId, Integer stock) {
        return stockRepository.search(carId, branchId, stock)
                              .stream()
                              .map(InventoryMapper::toDTO)
                              .collect(Collectors.toList());
    }
} 