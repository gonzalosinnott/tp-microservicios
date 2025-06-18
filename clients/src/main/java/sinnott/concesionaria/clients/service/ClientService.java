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
public class ClientService implements IClientService {
    private final ClientRepository clientRepository;

    public ClientService(ClientRepository clientRepository) {
        this.clientRepository = clientRepository;
    }

    @Override
    public List<ClientDTO> getClients() {
        try {
            return clientRepository.findAll()
                .stream()
                .map(ClientMapper::toDTO)
                .collect(Collectors.toList());
        } catch (Exception ex) {
            throw new ResponseStatusException(HttpStatus.INTERNAL_SERVER_ERROR, "Error al obtener clientes", ex);
        }
    }

    @Override
    public ClientDTO getClientById(Integer id) {
        try {
            return ClientMapper.toDTO(clientRepository.findById(id)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Cliente no encontrado")));
        } catch (Exception ex) {
            throw new ResponseStatusException(HttpStatus.INTERNAL_SERVER_ERROR, "Error al obtener cliente", ex);
        }
    }

    @Override
    public ClientDTO saveClient(ClientDTO clientDTO) {
        try {
            return ClientMapper.toDTO(clientRepository.save(ClientMapper.toEntity(clientDTO)));
        } catch (Exception ex) {
            throw new ResponseStatusException(HttpStatus.INTERNAL_SERVER_ERROR, "Error al guardar cliente", ex);
        }
    }

    @Override
    public ClientDTO updateClient(Integer id, ClientDTO clientDTO) {
        try {
            Client client = clientRepository.findById(id)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Cliente no encontrado"));
            client.setName(clientDTO.getName());
            client.setLastName(clientDTO.getLastName());
            client.setPhone(clientDTO.getPhone());
            client.setAddress(clientDTO.getAddress());
            return ClientMapper.toDTO(clientRepository.save(client));
        } catch (Exception ex) {
            throw new ResponseStatusException(HttpStatus.INTERNAL_SERVER_ERROR, "Error al actualizar cliente", ex);
        }
    }

    @Override
    public void deleteClient(Integer id) {
        try {
            if(!clientRepository.existsById(id)) {
                throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Cliente no encontrado");
            }
            clientRepository.deleteById(id);
        } catch (Exception ex) {
            throw new ResponseStatusException(HttpStatus.INTERNAL_SERVER_ERROR, "Error al eliminar cliente", ex);
        }
    }

    @Override
    public List<ClientDTO> search(String name, String lastName, Integer identityId, String email, String phone, String address) {
        try {
            return clientRepository.search(name, lastName, identityId, email, phone, address)
                .stream()
                .map(ClientMapper::toDTO)
                .collect(Collectors.toList());
        } catch (Exception ex) {
            throw new ResponseStatusException(HttpStatus.INTERNAL_SERVER_ERROR, "Error al buscar clientes", ex);
        }
    }

    @Override
    public Boolean existsClient(Integer id) {
        try {
            return clientRepository.existsById(id);
        } catch (Exception ex) {
            throw new ResponseStatusException(HttpStatus.INTERNAL_SERVER_ERROR, "Error al verificar existencia de cliente", ex);
        }
    }
} 