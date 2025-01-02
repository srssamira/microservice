package br.com.zup.gateway.controllers;

import br.com.zup.gateway.controllers.dtos.ConsumerAddressRegisterDTO;
import br.com.zup.gateway.controllers.dtos.ConsumerAddressResponseDTO;
import br.com.zup.gateway.infra.clients.address.dtos.AddressRegisterDTO;
import br.com.zup.gateway.infra.clients.address.dtos.AddressResponseDTO;
import br.com.zup.gateway.infra.clients.consumer.dtos.ConsumerRegisterDTO;
import br.com.zup.gateway.infra.clients.consumer.dtos.ConsumerResponseDTO;
import br.com.zup.gateway.services.ConsumerAddressService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
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
    public ResponseEntity<ConsumerAddressResponseDTO> register(@RequestBody ConsumerAddressRegisterDTO registerDTO){
        LOG.info("Register consumer address");
        return ResponseEntity.status(201).body(consumerAddressService.registerConsumerAddress(registerDTO));
    }

    @GetMapping("/address/{addressId}")
    public ResponseEntity<AddressResponseDTO> findById(@PathVariable("addressId") String addressId){
        LOG.info("Find consumer address by ID");
        return ResponseEntity.ok(consumerAddressService.getAddressById(addressId));
    }

    @GetMapping("/address")
    public ResponseEntity<List<AddressResponseDTO>> findAllAddresses(){
        LOG.info("Find all consumer addresses");
        return ResponseEntity.ok(consumerAddressService.getAllAddresses());
    }

    @GetMapping("/consumer")
    public ResponseEntity<List<ConsumerResponseDTO>> findAllConsumerAddresses(){
        LOG.info("Find all consumer addresses");
        return ResponseEntity.ok(consumerAddressService.getAllConsumers());
    }

    @GetMapping("/consumer/{consumerId}")
    public ResponseEntity<ConsumerResponseDTO> findByConsumerId(@PathVariable("consumerId") String consumerId){
        LOG.info("Find consumer by ID");
        return ResponseEntity.ok(consumerAddressService.getConsumerById(consumerId));
    }

    @PutMapping("/address/{addressId}")
    public ResponseEntity<AddressResponseDTO> updateAddress(@PathVariable("addressId") String addressId, @RequestBody AddressRegisterDTO addressRegisterDTO){
        LOG.info("Update consumer address");
        return ResponseEntity.ok(consumerAddressService.updateAddressById(addressId, addressRegisterDTO));
    }

    @PutMapping("/consumer/{consumerId}")
    public ResponseEntity<ConsumerResponseDTO> updateConsumer(@PathVariable("consumerId") String consumerId, @RequestBody ConsumerRegisterDTO registerDTO){
        LOG.info("Update consumer address");
        return ResponseEntity.ok(consumerAddressService.updateConsumer(consumerId, registerDTO));
    }

    @DeleteMapping("{address/addressId}")
    public ResponseEntity<?> deleteAddress(@PathVariable("addressId") String addressId){
        LOG.info("Delete consumer address by ID");
        consumerAddressService.deleteAddressById(addressId);
        return ResponseEntity.noContent().build();
    }

    @DeleteMapping("consumer/{consumerId}")
    public ResponseEntity<?> deleteConsumer(@PathVariable("consumerId") String consumerId){
        LOG.info("Delete consumer by ID");
        consumerAddressService.deleteConsumerById(consumerId);
        return ResponseEntity.noContent().build();
    }
}
