package sinnott.concesionaria.sales.clients.dto;

import lombok.Data;

@Data
public class ClientDTO {
    private Integer id;
    private String name;
    private String lastname;
    private Integer identityId;
    private String email;
    private String phone;
    private String address;
} 