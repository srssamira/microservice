package br.com.zup.gateway.controllers;

import br.com.zup.gateway.infra.clients.consumer.dtos.ConsumerRegisterDTO;
import br.com.zup.gateway.infra.clients.consumer.dtos.ConsumerResponseDTO;
import br.com.zup.gateway.services.ConsumerAddressService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.logging.Logger;

@RestController
@RequestMapping("/consumer")
public class ConsumerController {

    @Autowired
    private ConsumerAddressService consumerAddressService;

    private static final Logger LOG = Logger.getLogger(ConsumerAddressController.class.getName());

    @GetMapping("/{consumerId}")
    public ResponseEntity<ConsumerResponseDTO> findByConsumerId(@PathVariable("consumerId") String consumerId) {
        LOG.info("Finding consumer by id: " + consumerId);
        ConsumerResponseDTO consumerResponseDTO = consumerAddressService.getConsumerById(consumerId);
        LOG.info("Consumer found: " + consumerResponseDTO);

        return ResponseEntity.ok(consumerResponseDTO);
    }

    @GetMapping
    public ResponseEntity<List<ConsumerResponseDTO>> findAllConsumerAddresses() {

        LOG.info("Finding all consumer addresses");
        List<ConsumerResponseDTO> consumerResponseDTOS = consumerAddressService.getAllConsumers();
        LOG.info("Consumer found: " + consumerResponseDTOS);

        return ResponseEntity.status(200).body(consumerResponseDTOS);

    }


    @PutMapping("/{consumerId}")
    public ResponseEntity<ConsumerResponseDTO> updateConsumer(@PathVariable("consumerId") String consumerId, @RequestBody ConsumerRegisterDTO registerDTO) {
        LOG.info("Updating consumer: " + consumerId);
        ConsumerResponseDTO consumerResponseDTO = consumerAddressService.updateConsumer(consumerId, registerDTO);
        LOG.info("Consumer updated: " + consumerResponseDTO);

        return ResponseEntity.status(201).body(consumerResponseDTO);
    }

    @DeleteMapping("/{consumerId}")
    public ResponseEntity<?> deleteConsumer(@PathVariable("consumerId") String consumerId) {
        LOG.info("Delete consumer by ID");
        consumerAddressService.deleteConsumerById(consumerId);
        return ResponseEntity.noContent().build();
    }


}
