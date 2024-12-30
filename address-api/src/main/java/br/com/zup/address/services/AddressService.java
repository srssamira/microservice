package br.com.zup.address.services;

import br.com.zup.address.models.Address;
import br.com.zup.address.repositories.AddressRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class AddressService {

    @Autowired
    private AddressRepository addressRepository;

    // Create
    public Address createAddress(Address address) {
        return addressRepository.save(address);
    }

    // Read (Get All)
    public List<Address> getAllAddresses() {
        return addressRepository.findAll();
    }

    // Read (Get by ID)
    public Optional<Address> getAddressById(String id) {
        return addressRepository.findById(id);
    }

    // Update
    public Address updateAddress(String id, Address updatedAddress) {
        return addressRepository.findById(id).map(address -> {
            address.setStreet(updatedAddress.getStreet());
            address.setCity(updatedAddress.getCity());
            address.setState(updatedAddress.getState());
            address.setZipCode(updatedAddress.getZipCode());
            return addressRepository.save(address);
        }).orElseThrow(() -> new RuntimeException("Address not found with id " + id));
    }

    // Delete
    public void deleteAddress(String id) {
        if (addressRepository.existsById(id)) {
            addressRepository.deleteById(id);
        } else {
            throw new RuntimeException("Address not found with id " + id);
        }
    }
}