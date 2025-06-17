package sinnott.concesionaria.branchs.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import sinnott.concesionaria.branchs.entitites.Branch;
import sinnott.concesionaria.branchs.entitites.enums.Country;
import java.util.List;

@Repository
public interface BranchRepository extends JpaRepository<Branch, Integer> {
    @Query("SELECT b FROM Branch b WHERE " +
           "(:name IS NULL OR b.name LIKE %:name%) AND " +
           "(:country IS NULL OR b.country = :country) AND " +
           "(:province IS NULL OR b.province LIKE %:province%) AND " +
           "(:city IS NULL OR b.city LIKE %:city%) AND " +
           "(:address IS NULL OR b.address LIKE %:address%)")
    List<Branch> search(@Param("name") String name,
                        @Param("country") Country country,
                        @Param("province") String province,
                        @Param("city") String city,
                        @Param("address") String address);
} 