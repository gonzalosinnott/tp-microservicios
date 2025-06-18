package sinnott.concesionaria.clients.utils;

import sinnott.concesionaria.clients.entitites.Client;
import sinnott.concesionaria.clients.entitites.ClientDTO;

public class ClientMapper {
    public static Client toEntity(ClientDTO clientDTO) {
        Client client = new Client();
        client.setId(clientDTO.getId());
        client.setName(clientDTO.getName());
        client.setLastName(clientDTO.getLastName());
        client.setIdentityId(clientDTO.getIdentityId());
        client.setEmail(clientDTO.getEmail());
        client.setPhone(clientDTO.getPhone());
        client.setAddress(clientDTO.getAddress());
        return client;
    }

    public static ClientDTO toDTO(Client client) {
        ClientDTO dto = new ClientDTO();
        dto.setId(client.getId());
        dto.setName(client.getName());
        dto.setLastName(client.getLastName());
        dto.setIdentityId(client.getIdentityId());
        dto.setEmail(client.getEmail());
        dto.setPhone(client.getPhone());
        dto.setAddress(client.getAddress());
        return dto;
    }
} 