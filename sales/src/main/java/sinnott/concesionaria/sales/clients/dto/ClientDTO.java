package sinnott.concesionaria.sales.clients.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class ClientDTO {
    private Integer id;
    private String name;
    private String lastname;
    private Integer identityId;
    private String email;
    private String phone;
    private String address;
} 