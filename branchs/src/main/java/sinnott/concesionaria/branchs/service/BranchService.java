package sinnott.concesionaria.branchs.service;

import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import sinnott.concesionaria.branchs.entitites.Branch;
import sinnott.concesionaria.branchs.entitites.BranchDTO;
import sinnott.concesionaria.branchs.entitites.enums.Country;
import sinnott.concesionaria.branchs.repository.BranchRepository;
import sinnott.concesionaria.branchs.utils.BranchMapper;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class BranchService implements IBranchService {
    private final BranchRepository branchRepository;

    public BranchService(BranchRepository branchRepository) {
        this.branchRepository = branchRepository;
    }

    @Override
    public List<BranchDTO> getBranches() {
        try {
            return branchRepository.findAll()
                .stream()
                .map(BranchMapper::toDTO)
                .collect(Collectors.toList());
        } catch (Exception ex) {
            throw new ResponseStatusException(HttpStatus.INTERNAL_SERVER_ERROR, "Error al obtener sucursales", ex);
        }
    }

    @Override
    public BranchDTO getBranchById(Integer id) {
        try {
            return BranchMapper.toDTO(branchRepository.findById(id)
                .orElseThrow(() ->  new ResponseStatusException(HttpStatus.NOT_FOUND, "Sucursal no encontrada")));
        } catch (Exception ex) {
            throw new ResponseStatusException(HttpStatus.INTERNAL_SERVER_ERROR, "Error al obtener sucursal", ex);
        }
    }

    @Override
    public BranchDTO saveBranch(BranchDTO branchDTO) {
        try {
            return BranchMapper.toDTO(branchRepository.save(BranchMapper.toEntity(branchDTO)));
        } catch (Exception ex) {
            throw new ResponseStatusException(HttpStatus.INTERNAL_SERVER_ERROR, "Error al guardar sucursal", ex);
        }
    }

    @Override
    public BranchDTO updateBranch(Integer id, BranchDTO branchDTO) {
        try {
            Branch branch = branchRepository.findById(id)
                .orElseThrow(() ->  new ResponseStatusException(HttpStatus.NOT_FOUND, "Sucursal no encontrada"));
            branch.setName(branchDTO.getName());
            branch.setCountry(branchDTO.getCountry());
            branch.setProvince(branchDTO.getProvince());
            branch.setCity(branchDTO.getCity());
            branch.setAddress(branchDTO.getAddress());
            branch.setOpeningDate(branchDTO.getOpeningDate());
            branch.setDeliveryTimeFromCentralWarehouse(branchDTO.getDeliveryTimeFromCentralWarehouse());
            branch.setDeliveryTimeFromBranch(branchDTO.getDeliveryTimeFromBranch());
            return BranchMapper.toDTO(branchRepository.save(branch));
        } catch (Exception ex) {
            throw new ResponseStatusException(HttpStatus.INTERNAL_SERVER_ERROR, "Error al actualizar sucursal", ex);
        }
    }

    @Override
    public void deleteBranch(Integer id) {
        try {
            if (!branchRepository.existsById(id)) {
                throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Sucursal no encontrada");
            }
            branchRepository.deleteById(id);
        } catch (Exception ex) {
            throw new ResponseStatusException(HttpStatus.INTERNAL_SERVER_ERROR, "Error al eliminar sucursal", ex);
        }
    }

    @Override
    public List<BranchDTO> search(String name, Country country, String province, String city, String address) {
        try {
            return branchRepository.search(name, country, province, city, address)
                .stream()
                .map(BranchMapper::toDTO)
                .collect(Collectors.toList());
        } catch (Exception ex) {
            throw new ResponseStatusException(HttpStatus.INTERNAL_SERVER_ERROR, "Error al buscar sucursales", ex);
        }
    }

    @Override
    public boolean existsBranch(Integer id) {
        try {
            return branchRepository.existsById(id);
        } catch (Exception ex) {
            throw new ResponseStatusException(HttpStatus.INTERNAL_SERVER_ERROR, "Error al verificar existencia de sucursal", ex);
        }
    }
} 