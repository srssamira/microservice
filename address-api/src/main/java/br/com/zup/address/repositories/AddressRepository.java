package br.com.zup.address.repositories;

import br.com.zup.address.models.Address;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface AddressRepository extends JpaRepository<Address, String> {
    Optional<Address> findAddressByConsumerId(String consumerId);
}
