package br.com.zup.gateway.controllers;

import br.com.zup.gateway.controllers.dtos.ConsumerAddressRegisterDTO;
import br.com.zup.gateway.controllers.dtos.ConsumerAddressResponseDTO;
import br.com.zup.gateway.services.ConsumerAddressService;
import br.com.zup.gateway.services.ConsumerAddressServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

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


    @GetMapping("/{consumerId}")
    public ResponseEntity<ConsumerAddressResponseDTO> findAddressAndConsumerByConsumerId(@PathVariable String consumerId) {
        LOG.info("Finding address and consumer by id: " + consumerId);
        ConsumerAddressResponseDTO consumerAddressResponseDTO = consumerAddressService.getConsumerAddressById(consumerId);
        LOG.info("Consumer found: " + consumerAddressResponseDTO);

        return ResponseEntity.ok(consumerAddressResponseDTO);
    }


}
