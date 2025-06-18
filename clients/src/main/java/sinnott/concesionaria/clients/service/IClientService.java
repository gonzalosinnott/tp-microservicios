package sinnott.concesionaria.clients.service;

import sinnott.concesionaria.clients.entitites.ClientDTO;
import java.util.List;

public interface IClientService {
    List<ClientDTO> getClients();
    ClientDTO getClientById(Integer id);
    ClientDTO saveClient(ClientDTO clientDTO);
    ClientDTO updateClient(Integer id, ClientDTO clientDTO);
    void deleteClient(Integer id);
    List<ClientDTO> search(String name, String lastName, Integer identityId, String email, String phone, String address);
    Boolean existsClient(Integer id);
} 