package br.com.zup.gateway.controllers;

import br.com.zup.gateway.infra.clients.address.dtos.AddressRegisterDTO;
import br.com.zup.gateway.infra.clients.address.dtos.AddressResponseDTO;
import br.com.zup.gateway.services.ConsumerAddressService;
import br.com.zup.gateway.services.ConsumerAddressServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.logging.Logger;

@RestController
@RequestMapping("/address")
public class AddressController {

    @Autowired
    private ConsumerAddressService consumerAddressService;

    private static final Logger LOG = Logger.getLogger(ConsumerAddressController.class.getName());


    @GetMapping("/{addressId}")
    public ResponseEntity<AddressResponseDTO> findById(@PathVariable("addressId") String addressId) {
        LOG.info("Finding address by id: " + addressId);
        AddressResponseDTO addressResponseDTO = consumerAddressService.getAddressById(addressId);
        LOG.info("Address found: " + addressResponseDTO);
        return ResponseEntity.status(200).body(addressResponseDTO);
    }

    @GetMapping
    public ResponseEntity<List<AddressResponseDTO>> findAllAddresses() {
        LOG.info("Finding all addresses");
        List<AddressResponseDTO> addressResponseDTOS = consumerAddressService.getAllAddresses();
        LOG.info("Addresses found: " + addressResponseDTOS);
        return ResponseEntity.status(200).body(addressResponseDTOS);
    }

    @PutMapping("/{addressId}")
    public ResponseEntity<AddressResponseDTO> updateAddress(@PathVariable("addressId") String addressId, @RequestBody AddressRegisterDTO addressRegisterDTO) {
        LOG.info("Updating address: " + addressId);
        AddressResponseDTO addressResponseDTO = consumerAddressService.updateAddressById(addressId, addressRegisterDTO);
        LOG.info("Address updated: " + addressResponseDTO);

        return ResponseEntity.status(201).body(addressResponseDTO);
    }

    @DeleteMapping("/{addressId}")
    public ResponseEntity<?> deleteAddress(@PathVariable("addressId") String addressId) {
        LOG.info("Delete consumer address by ID");
        consumerAddressService.deleteAddressById(addressId);
        return ResponseEntity.noContent().build();
    }
}
