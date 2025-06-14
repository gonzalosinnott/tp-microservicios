package sinnott.concesionaria.clients.utils;

import sinnott.concesionaria.clients.entitites.ClientDTO;
import sinnott.concesionaria.clients.models.Client;

public class ClientMapper {
    public static Client toEntity(ClientDTO clientDTO) {
        return new Client(clientDTO.getName(),
                          clientDTO.getSurname(),
                          clientDTO.getEmail(),
                          clientDTO.getPhone(),
                          clientDTO.getAddress()
        );
    }

    public static ClientDTO toDTO(Client client) {
        return new ClientDTO(
            client.getId(),
            client.getName(),
            client.getSurname(),
            client.getEmail(),
            client.getPhone(),
            client.getAddress()
        );
    }
} 