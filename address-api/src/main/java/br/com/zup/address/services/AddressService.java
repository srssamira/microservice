package br.com.zup.address.services;

import br.com.zup.address.controllers.dtos.AddressResponseDTO;
import br.com.zup.address.models.Address;
import br.com.zup.address.repositories.AddressRepository;
import br.com.zup.address.services.mappers.AddressMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class AddressService {

    @Autowired
    private AddressRepository addressRepository;

    public Address createAddress(Address address) {
        return addressRepository.save(address);
    }

    public List<Address> getAllAddresses() {
            return addressRepository.findAll();
    }

    public Optional<Address> getAddressById(String id) {
        return addressRepository.findById(id);
    }

    public Optional<AddressResponseDTO> getByConsumerId(String consumerId) {
        Optional<Address> address = addressRepository.findAddressByConsumerId(consumerId);

        if (address.isEmpty())
            throw new RuntimeException("ConsumerId not found");

        return Optional.of(AddressMapper.toAddressResponseDTO(address.get()));
    }

    public Address updateAddress(String id, Address updatedAddress) {
        return addressRepository.findById(id).map(address -> {
            address.setStreet(updatedAddress.getStreet());
            address.setCity(updatedAddress.getCity());
            address.setState(updatedAddress.getState());
            address.setZipCode(updatedAddress.getZipCode());
            return addressRepository.save(address);
        }).orElseThrow(() -> new RuntimeException("Address not found with id " + id));
    }

    public void deleteAddress(String id) {
        if (addressRepository.existsById(id)) {
            addressRepository.deleteById(id);
        } else {
            throw new RuntimeException("Address not found with id " + id);
        }
    }
}