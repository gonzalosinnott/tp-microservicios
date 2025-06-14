package sinnott.concesionaria.clients.service;

import org.springframework.stereotype.Service;

import sinnott.concesionaria.clients.entitites.ClientDTO;
import sinnott.concesionaria.clients.entitites.ClientRepository;
import sinnott.concesionaria.clients.models.Client;
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
                .orElseThrow(() -> new RuntimeException("Cliente no encontrado")));
    }

    public ClientDTO saveClient(ClientDTO clientDTO) {
        return ClientMapper.toDTO(clientRepository.save(ClientMapper.toEntity(clientDTO)));
    }

    public ClientDTO updateClient(Integer id, ClientDTO clientDTO) {
        Client client = clientRepository.findById(id)
                                        .orElseThrow(() -> new RuntimeException("Cliente no encontrado"));
        client.setName(clientDTO.getName());
        client.setSurname(clientDTO.getSurname());
        client.setPhone(clientDTO.getPhone());
        client.setAddress(clientDTO.getAddress());
        return ClientMapper.toDTO(clientRepository.save(client));
    }

    public void deleteClient(Integer id) {
        clientRepository.deleteById(id);
    }

    public List<ClientDTO> search(String name, String lastName, String email, String phone, String address) {
        return clientRepository.search(name, lastName, email, phone, address).stream()
                                                                             .map(ClientMapper::toDTO)
                                                                             .collect(Collectors.toList());
    }
} 