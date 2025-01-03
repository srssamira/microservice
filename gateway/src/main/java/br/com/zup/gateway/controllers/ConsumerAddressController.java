package br.com.zup.gateway.controllers;

import br.com.zup.gateway.controllers.dtos.ConsumerAddressRegisterDTO;
import br.com.zup.gateway.controllers.dtos.ConsumerAddressResponseDTO;
import br.com.zup.gateway.infra.clients.address.dtos.AddressRegisterDTO;
import br.com.zup.gateway.infra.clients.address.dtos.AddressResponseDTO;
import br.com.zup.gateway.infra.clients.consumer.dtos.ConsumerRegisterDTO;
import br.com.zup.gateway.infra.clients.consumer.dtos.ConsumerResponseDTO;
import br.com.zup.gateway.services.ConsumerAddressService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.logging.Logger;

@RestController
@RequestMapping("/consumer-address")
public class ConsumerAddressController {

    @Autowired
    private ConsumerAddressService consumerAddressService;

    private static final Logger LOG = Logger.getLogger(ConsumerAddressController.class.getName());

    @PostMapping
    public ResponseEntity<ConsumerAddressResponseDTO> register(@RequestBody @Validated ConsumerAddressRegisterDTO registerDTO) {
        LOG.info("Registering consumer address: " + registerDTO);
        ConsumerAddressResponseDTO consumerAddress = consumerAddressService.registerConsumerAddress(registerDTO);
        LOG.info("Consumer address: " + consumerAddress.getAddress());
        return ResponseEntity.status(201).body(consumerAddress);
    }

    @GetMapping("/address/{addressId}")
    public ResponseEntity<AddressResponseDTO> findById(@PathVariable("addressId") String addressId) {
        LOG.info("Finding address by id: " + addressId);
        AddressResponseDTO addressResponseDTO = consumerAddressService.getAddressById(addressId);
        LOG.info("Address found: " + addressResponseDTO);
        return ResponseEntity.status(200).body(addressResponseDTO);
    }

    @GetMapping("/address")
    public ResponseEntity<List<AddressResponseDTO>> findAllAddresses() {
        LOG.info("Finding all addresses");
        List<AddressResponseDTO> addressResponseDTOS = consumerAddressService.getAllAddresses();
        LOG.info("Addresses found: " + addressResponseDTOS);
        return ResponseEntity.status(200).body(addressResponseDTOS);
    }

    @GetMapping("/{consumerId}")
    public ResponseEntity<ConsumerAddressResponseDTO> findAddressAndConsumerByConsumerId(@PathVariable String consumerId) {
        LOG.info("Finding address and consumer by id: " + consumerId);
        ConsumerAddressResponseDTO consumerAddressResponseDTO = consumerAddressService.getConsumerAddressById(consumerId);
        LOG.info("Consumer found: " + consumerAddressResponseDTO);

        return ResponseEntity.ok(consumerAddressResponseDTO);
    }

    @GetMapping("/consumer")
    public ResponseEntity<List<ConsumerResponseDTO>> findAllConsumerAddresses() {

        LOG.info("Finding all consumer addresses");
        List<ConsumerResponseDTO> consumerResponseDTOS = consumerAddressService.getAllConsumers();
        LOG.info("Consumer found: " + consumerResponseDTOS);

        return ResponseEntity.status(200).body(consumerResponseDTOS);

    }

    @GetMapping("/consumer/{consumerId}")
    public ResponseEntity<ConsumerResponseDTO> findByConsumerId(@PathVariable("consumerId") String consumerId) {
        LOG.info("Finding consumer by id: " + consumerId);
        ConsumerResponseDTO consumerResponseDTO = consumerAddressService.getConsumerById(consumerId);
        LOG.info("Consumer found: " + consumerResponseDTO);

        return ResponseEntity.ok(consumerResponseDTO);
    }

    @PutMapping("/address/{addressId}")
    public ResponseEntity<AddressResponseDTO> updateAddress(@PathVariable("addressId") String addressId, @RequestBody AddressRegisterDTO addressRegisterDTO) {
        LOG.info("Updating address: " + addressId);
        AddressResponseDTO addressResponseDTO = consumerAddressService.updateAddressById(addressId, addressRegisterDTO);
        LOG.info("Address updated: " + addressResponseDTO);

        return ResponseEntity.status(201).body(addressResponseDTO);
    }

    @PutMapping("/consumer/{consumerId}")
    public ResponseEntity<ConsumerResponseDTO> updateConsumer(@PathVariable("consumerId") String consumerId, @RequestBody ConsumerRegisterDTO registerDTO) {
        LOG.info("Updating consumer: " + consumerId);
        ConsumerResponseDTO consumerResponseDTO = consumerAddressService.updateConsumer(consumerId, registerDTO);
        LOG.info("Consumer updated: " + consumerResponseDTO);

        return ResponseEntity.status(201).body(consumerResponseDTO);
    }

    @DeleteMapping("address/{addressId}")
    public ResponseEntity<?> deleteAddress(@PathVariable("addressId") String addressId) {
        LOG.info("Delete consumer address by ID");
        consumerAddressService.deleteAddressById(addressId);
        return ResponseEntity.noContent().build();
    }

    @DeleteMapping("consumer/{consumerId}")
    public ResponseEntity<?> deleteConsumer(@PathVariable("consumerId") String consumerId) {
        LOG.info("Delete consumer by ID");
        consumerAddressService.deleteConsumerById(consumerId);
        return ResponseEntity.noContent().build();
    }
}
