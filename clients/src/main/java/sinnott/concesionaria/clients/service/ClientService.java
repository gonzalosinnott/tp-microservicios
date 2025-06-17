package sinnott.concesionaria.clients.service;

import org.springframework.stereotype.Service;
import org.springframework.http.HttpStatus;
import org.springframework.web.server.ResponseStatusException;

import sinnott.concesionaria.clients.entitites.Client;
import sinnott.concesionaria.clients.entitites.ClientDTO;
import sinnott.concesionaria.clients.repository.ClientRepository;
import sinnott.concesionaria.clients.utils.ClientMapper;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class ClientService {
    private final ClientRepository clientRepository;

    public ClientService(ClientRepository clientRepository) {
        this.clientRepository = clientRepository;
    }

    public List<ClientDTO> getClients() {
        return clientRepository.findAll()
                .stream()
                .map(ClientMapper::toDTO)
                .collect(Collectors.toList());
    }

    public ClientDTO getClientById(Integer id) {
        return ClientMapper.toDTO(clientRepository.findById(id)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Cliente no encontrado")));
    }

    public ClientDTO saveClient(ClientDTO clientDTO) {
        return ClientMapper.toDTO(clientRepository.save(ClientMapper.toEntity(clientDTO)));
    }

    public ClientDTO updateClient(Integer id, ClientDTO clientDTO) {
        Client client = clientRepository.findById(id)
                                        .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Cliente no encontrado"));;
        client.setName(clientDTO.getName());
        client.setLastName(clientDTO.getLastname());
        client.setPhone(clientDTO.getPhone());
        client.setAddress(clientDTO.getAddress());
        return ClientMapper.toDTO(clientRepository.save(client));
    }

    public void deleteClient(Integer id) {
        if(!clientRepository.existsById(id)) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Cliente no encontrado");
        }
        clientRepository.deleteById(id);
    }

    public List<ClientDTO> search(String name, String lastName, Integer identityId, String email, String phone, String address) {
        return clientRepository.search(name, lastName, identityId, email, phone, address)
                               .stream()
                               .map(ClientMapper::toDTO)
                               .collect(Collectors.toList());
    }

    public Boolean existsClient(Integer id) {
        return clientRepository.existsById(id);
    }
} 