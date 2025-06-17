package sinnott.concesionaria.clients.entitites;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
public class Client {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    private String name;

    private String lastName;

    @Column(unique = true)
    private Integer identityId;

    private String email;

    private String phone;

    private String address;

    public Client(String name, String lastName, Integer identityId,  String email, String phone, String address) {
        this.name = name;
        this.lastName = lastName;
        this.identityId = identityId;
        this.email = email;
        this.phone = phone;
        this.address = address;
    }
} 