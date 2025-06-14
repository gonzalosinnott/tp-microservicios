package sinnott.concesionaria.clients.entitites;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import sinnott.concesionaria.clients.models.Client;

import java.util.List;

@Repository
public interface ClientRepository extends JpaRepository<Client, Integer> {
    
    @Query("SELECT c FROM Client c WHERE " +
            "(:name IS NULL OR c.name LIKE %:name%) AND " +
            "(:surname IS NULL OR c.surname LIKE %:surname%) AND " +
            "(:email IS NULL OR c.email LIKE %:email%) AND " +
            "(:phone IS NULL OR c.phone LIKE %:phone%) AND " +
            "(:address IS NULL OR c.address LIKE %:address%)")
    List<Client> search(@Param("name") String name, 
                        @Param("surname") String surname, 
                        @Param("email") String email, 
                        @Param("phone") String phone, 
                        @Param("address") String address);
} 