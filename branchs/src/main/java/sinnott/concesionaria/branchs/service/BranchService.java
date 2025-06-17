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
public class BranchService {
    private final BranchRepository branchRepository;

    public BranchService(BranchRepository branchRepository) {
        this.branchRepository = branchRepository;
    }

    public List<BranchDTO> getBranches() {
        return branchRepository.findAll()
                               .stream()
                               .map(BranchMapper::toDTO)
                               .collect(Collectors.toList());
    }

    public BranchDTO getBranchById(Integer id) {
        return BranchMapper.toDTO(branchRepository.findById(id)
            .orElseThrow(() ->  new ResponseStatusException(HttpStatus.NOT_FOUND, "Sucursal no encontrada")));
    }

    public BranchDTO saveBranch(BranchDTO branchDTO) {
        return BranchMapper.toDTO(branchRepository.save(BranchMapper.toEntity(branchDTO)));
    }

    public BranchDTO updateBranch(Integer id, BranchDTO branchDTO) {
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
    }

    public void deleteBranch(Integer id) {
        if (!branchRepository.existsById(id)) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Sucursal no encontrada");
        }
        branchRepository.deleteById(id);
    }

    public List<BranchDTO> search(String name, Country country, String province, String city, String address) {
        return branchRepository.search(name, country, province, city, address)
                               .stream()
                               .map(BranchMapper::toDTO)
                               .collect(Collectors.toList());
    }

    public boolean existsBranch(Integer id) {
        return branchRepository.existsById(id);
    }
} 