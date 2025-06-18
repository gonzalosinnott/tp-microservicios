package sinnott.concesionaria.branchs.service;

import sinnott.concesionaria.branchs.entitites.BranchDTO;
import sinnott.concesionaria.branchs.entitites.enums.Country;
import java.util.List;

public interface IBranchService {
    List<BranchDTO> getBranches();
    BranchDTO getBranchById(Integer id);
    BranchDTO saveBranch(BranchDTO branchDTO);
    BranchDTO updateBranch(Integer id, BranchDTO branchDTO);
    void deleteBranch(Integer id);
    List<BranchDTO> search(String name, Country country, String province, String city, String address);
    boolean existsBranch(Integer id);
} 