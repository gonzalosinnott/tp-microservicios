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
import sinnott.concesionaria.stock.entities.inventory.InventorySaleDTO;

@Service
public class InventoryService implements IInventoryService {
    private final InventoryRepository stockRepository;
    private final CarRepository carRepository;
    private final BranchClient branchClient;

    public InventoryService(InventoryRepository stockRepository, CarRepository carRepository, BranchClient branchClient) {
        this.stockRepository = stockRepository;
        this.carRepository = carRepository;
        this.branchClient = branchClient;
    }

    @Override
    public List<InventoryDTO> getAllInventory() {
        try {
            return stockRepository.findAll()
                .stream()
                .map(InventoryMapper::toDTO)
                .collect(Collectors.toList());
        } catch (Exception ex) {
            throw new ResponseStatusException(HttpStatus.INTERNAL_SERVER_ERROR, "Error al obtener inventario", ex);
        }
    }

    @Override
    public InventoryDTO getInventoryById(Integer id) {
        try {
            return InventoryMapper.toDTO(stockRepository.findById(id)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Inventario no encontrado")));
        } catch (Exception ex) {
            throw new ResponseStatusException(HttpStatus.INTERNAL_SERVER_ERROR, "Error al obtener inventario", ex);
        }
    }

    @Override
    public InventoryDTO saveInventory(InventoryDTO inventoryDTO) {
        try {
            try {
                if (!carRepository.existsById(inventoryDTO.getCarId())) {
                    throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Auto no encontrado en catalogo");
                }
            } catch (Exception ex) {
                throw new ResponseStatusException(HttpStatus.INTERNAL_SERVER_ERROR, "Error al consultar auto en catalogo", ex);
            }
            try {
                if (!branchClient.existsBranch(inventoryDTO.getBranchId())) {
                    throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Sucursal no encontrada");
                }
            } catch (Exception ex) {
                throw new ResponseStatusException(HttpStatus.SERVICE_UNAVAILABLE, "Error al consultar sucursal", ex);
            }
            return InventoryMapper.toDTO(stockRepository.save(InventoryMapper.toEntity(inventoryDTO)));
        } catch (Exception ex) {
            throw new ResponseStatusException(HttpStatus.INTERNAL_SERVER_ERROR, "Error al guardar inventario", ex);
        }
    }

    @Override
    public InventoryDTO updateInventory(Integer id, InventoryDTO inventoryDTO) {
        try {
            try {
                if (!carRepository.existsById(inventoryDTO.getCarId())) {
                    throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Auto no encontrado en catalogo");
                }
            } catch (Exception ex) {
                throw new ResponseStatusException(HttpStatus.INTERNAL_SERVER_ERROR, "Error al consultar auto en catalogo", ex);
            }
            try {
                if (!branchClient.existsBranch(inventoryDTO.getBranchId())) {
                    throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Sucursal no encontrada");
                }
            } catch (Exception ex) {
                throw new ResponseStatusException(HttpStatus.SERVICE_UNAVAILABLE, "Error al consultar sucursal", ex);
            }
            Inventory stock = stockRepository.findById(id)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Inventario no encontrado"));
            stock.setCarId(inventoryDTO.getCarId());
            stock.setBranchId(inventoryDTO.getBranchId());
            stock.setStock(inventoryDTO.getStock());
            return InventoryMapper.toDTO(stockRepository.save(stock));
        } catch (Exception ex) {
            throw new ResponseStatusException(HttpStatus.INTERNAL_SERVER_ERROR, "Error al actualizar inventario", ex);
        }
    }

    @Override
    public InventoryDTO updateInventoryForSale(InventorySaleDTO inventorySaleDTO) {
        try {
            Inventory inventory = stockRepository.findByCarIdAndBranchId(inventorySaleDTO.getCarId(), inventorySaleDTO.getBranchId())
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Inventario no encontrado para el auto y sucursal especificados"));
            if (inventory.getStock() < inventorySaleDTO.getQuantity()) {
                throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Stock insuficiente para la venta");
            }
            inventory.setStock(inventory.getStock() - inventorySaleDTO.getQuantity());
            return InventoryMapper.toDTO(stockRepository.save(inventory));
        } catch (ResponseStatusException ex) {
            throw ex;
        } catch (Exception ex) {
            throw new ResponseStatusException(HttpStatus.INTERNAL_SERVER_ERROR, "Error al actualizar inventario por venta", ex);
        }
    }

    @Override
    public void deleteInventory(Integer id) {
        try {
            if (!stockRepository.existsById(id)) {
                throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Inventario no encontrado");
            }
            stockRepository.deleteById(id);
        } catch (Exception ex) {
            throw new ResponseStatusException(HttpStatus.INTERNAL_SERVER_ERROR, "Error al eliminar inventario", ex);
        }
    }

    @Override
    public List<InventoryDTO> search(Integer carId, Integer branchId, Integer stock) {
        try {
            return stockRepository.search(carId, branchId, stock)
                .stream()
                .map(InventoryMapper::toDTO)
                .collect(Collectors.toList());
        } catch (Exception ex) {
            throw new ResponseStatusException(HttpStatus.INTERNAL_SERVER_ERROR, "Error al buscar inventario", ex);
        }
    }
} 