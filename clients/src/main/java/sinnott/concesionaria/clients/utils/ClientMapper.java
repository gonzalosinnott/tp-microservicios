package sinnott.concesionaria.clients.utils;

import sinnott.concesionaria.clients.entitites.Client;
import sinnott.concesionaria.clients.entitites.ClientDTO;

public class ClientMapper {
    public static Client toEntity(ClientDTO clientDTO) {
        return new Client(clientDTO.getName(),
                          clientDTO.getLastName(),
                          clientDTO.getIdentityId(),
                          clientDTO.getEmail(),
                          clientDTO.getPhone(),
                          clientDTO.getAddress());
    }

    public static ClientDTO toDTO(Client client) {
        return new ClientDTO(client.getId(),
                             client.getName(),
                             client.getLastName(),
                             client.getIdentityId(),
                             client.getEmail(),
                             client.getPhone(),
                             client.getAddress());
    }
} 