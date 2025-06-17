package sinnott.concesionaria.branchs.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import sinnott.concesionaria.branchs.entitites.BranchDTO;
import sinnott.concesionaria.branchs.entitites.enums.Country;
import sinnott.concesionaria.branchs.service.BranchService;

import java.util.List;

@RestController
@RequestMapping("/branchs")
@Tag(name = "Sucursales", description = "API para la gestión de sucursales")
public class BranchsController {

    private final BranchService branchService;

    public BranchsController(BranchService branchService) {
        this.branchService = branchService;
    }

    @GetMapping
    @Operation(summary = "Obtener todas las sucursales")
    public ResponseEntity<List<BranchDTO>> getBranches() {
        return ResponseEntity.ok(branchService.getBranches());
    }

    @GetMapping("/{id}")
    @Operation(summary = "Obtener una sucursal por ID")
    public ResponseEntity<BranchDTO> getBranchById(@PathVariable Integer id) {
        return ResponseEntity.ok(branchService.getBranchById(id));
    }

    @PostMapping
    @Operation(summary = "Crear una sucursal")
    public ResponseEntity<BranchDTO> createBranch(@Valid @RequestBody BranchDTO branchDTO) {
        return ResponseEntity.ok(branchService.saveBranch(branchDTO));
    }

    @PutMapping("/{id}")
    @Operation(summary = "Actualizar una sucursal")
    public ResponseEntity<BranchDTO> updateBranch(@PathVariable Integer id, @Valid @RequestBody BranchDTO branchDTO) {
        return ResponseEntity.ok(branchService.updateBranch(id, branchDTO));
    }

    @DeleteMapping("/{id}")
    @Operation(summary = "Eliminar una sucursal")
    public ResponseEntity<Void> deleteBranch(@PathVariable Integer id) {
        branchService.deleteBranch(id);
        return ResponseEntity.noContent().build();
    }

    @GetMapping("/search")
    @Operation(summary = "Buscar sucursales por cualquier combinación de atributos")
    public ResponseEntity<List<BranchDTO>> searchByParams(@RequestParam(required = false) String name,
                                                          @RequestParam(required = false) Country country,
                                                          @RequestParam(required = false) String province,
                                                          @RequestParam(required = false) String city,
                                                          @RequestParam(required = false) String address) {
        return ResponseEntity.ok(branchService.search(name, country, province, city, address));
    }

    @GetMapping("/exists/{id}")
    @Operation(summary = "Verificar si una sucursal existe")
    public ResponseEntity<Boolean> existsBranch(@PathVariable Integer id) {
        return ResponseEntity.ok(branchService.existsBranch(id));
    }
} 