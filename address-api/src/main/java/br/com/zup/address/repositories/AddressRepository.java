package br.com.zup.address.repositories;

import br.com.zup.address.models.Address;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface AddressRepository extends JpaRepository<Address, String> {

    @Query(value = "SELECT * FROM address WHERE consumer_id = :consumerId", nativeQuery = true)
    Optional<Address> findAddressByConsumerId(@Param("consumerId") String consumerId);
}
