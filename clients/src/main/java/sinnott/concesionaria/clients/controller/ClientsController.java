package sinnott.concesionaria.clients.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import sinnott.concesionaria.clients.entitites.ClientDTO;
import sinnott.concesionaria.clients.service.ClientService;

import java.util.List;

@RestController
@RequestMapping("/clients")
@Tag(name = "Clientes", description = "API para la gestión de clientes")
public class ClientsController {

    private final ClientService clientService;

    public ClientsController(ClientService clientService) {
        this.clientService = clientService;
    }

    @GetMapping
    @Operation(summary = "Obtener todos los clientes")
    public ResponseEntity<List<ClientDTO>> getClients() {
        return ResponseEntity.ok(clientService.getClients());
    }

    @GetMapping("/{id}")
    @Operation(summary = "Obtener un cliente por ID")
    public ResponseEntity<ClientDTO> getClientById(@PathVariable Integer id) {
        return ResponseEntity.ok(clientService.getClientById(id));
    }

    @PostMapping
    @Operation(summary = "Crear un cliente")
    public ResponseEntity<ClientDTO> createClient(@Valid @RequestBody ClientDTO clientDTO) {
        return ResponseEntity.ok(clientService.saveClient(clientDTO));
    }

    @PutMapping("/{id}")
    @Operation(summary = "Actualizar un cliente")
    public ResponseEntity<ClientDTO> updateClient(@PathVariable Integer id, @Valid @RequestBody ClientDTO clientDTO) {
        return ResponseEntity.ok(clientService.updateClient(id, clientDTO));
    }

    @DeleteMapping("/{id}")
    @Operation(summary = "Eliminar un cliente")
    public ResponseEntity<Void> deleteClient(@PathVariable Integer id) {
        clientService.deleteClient(id);
        return ResponseEntity.noContent().build();
    }

    @GetMapping("/search")
    @Operation(summary = "Buscar clientes por cualquier combinación de atributos")
    public ResponseEntity<List<ClientDTO>> searchByParams(@RequestParam(required = false) String name,
                                                          @RequestParam(required = false) String lastName,
                                                          @RequestParam(required = false) Integer identityId, 
                                                          @RequestParam(required = false) String email,
                                                          @RequestParam(required = false) String phone,
                                                          @RequestParam(required = false) String address) {
        return ResponseEntity.ok(clientService.search(name, lastName, identityId, email, phone, address));
    }

    @GetMapping("/exists/{id}")
    @Operation(summary = "Verificar si un cliente existe")
    public ResponseEntity<Boolean> existsClient(@PathVariable Integer id) {
        return ResponseEntity.ok(clientService.existsClient(id));
    }
}