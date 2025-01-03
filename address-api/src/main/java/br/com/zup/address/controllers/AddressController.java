package br.com.zup.address.controllers;

import br.com.zup.address.controllers.dtos.AddressRegisterDTO;
import br.com.zup.address.controllers.dtos.AddressResponseDTO;
import br.com.zup.address.models.Address;
import br.com.zup.address.services.AddressService;
import br.com.zup.address.services.mappers.AddressMapper;
import jakarta.validation.Valid;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/address")
public class AddressController {
    private static final Logger log = LoggerFactory.getLogger(AddressController.class);

    @Autowired
    private AddressService addressService;

    @PostMapping
    public ResponseEntity<AddressResponseDTO> createAddress(@RequestBody @Valid AddressRegisterDTO requestDTO) {
        log.info("Start address register flow");
        Address address = AddressMapper.toAddress(requestDTO);

        Address createdAddress = addressService.createAddress(address);

        AddressResponseDTO responseDTO = AddressMapper.toAddressResponseDTO(createdAddress);
        log.info("Finish address register flow");
        return ResponseEntity.ok(responseDTO);
    }

    @GetMapping
    public ResponseEntity<?> getAllAddresses() {
        List<Address> addresses = addressService.getAllAddresses();

        return ResponseEntity.ok(AddressMapper.toAddressResponseDTOList(addresses));
    }

    @GetMapping("/consumer/{consumerId}")
    public ResponseEntity<?> getAddressByConsumerId(@PathVariable String consumerId){
        Optional<AddressResponseDTO> addressResponseDTO = addressService.getByConsumerId(consumerId);
        if(addressResponseDTO.isEmpty()){
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        }
        return ResponseEntity.ok(addressResponseDTO.get());
    }

    @GetMapping("/{id}")
    public ResponseEntity<AddressResponseDTO> getAddressById(@PathVariable String id) {
        Address address = addressService.getAddressById(id)
                .orElseThrow(() -> new RuntimeException("Address not found with id " + id));

        return ResponseEntity.ok(AddressMapper.toAddressResponseDTO(address));
    }

    @PutMapping("/{id}")
    public ResponseEntity<AddressResponseDTO> updateAddress(@PathVariable String id, @RequestBody AddressRegisterDTO requestDTO) {
        Address updatedAddress = AddressMapper.toAddress(requestDTO);

        Address address = addressService.updateAddress(id, updatedAddress);

        return ResponseEntity.ok(AddressMapper.toAddressResponseDTO(address));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> deleteAddress(@PathVariable String id) {
        addressService.deleteAddress(id);
        return ResponseEntity.noContent().build();
    }
}