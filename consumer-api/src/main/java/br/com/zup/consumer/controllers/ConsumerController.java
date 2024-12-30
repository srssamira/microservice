package br.com.zup.consumer.controllers;

import br.com.zup.consumer.controllers.dtos.ConsumerRegisterDTO;
import br.com.zup.consumer.controllers.dtos.ConsumerResponseDTO;
import br.com.zup.consumer.models.Consumer;
import br.com.zup.consumer.services.ConsumerService;
import br.com.zup.consumer.services.mappers.ConsumerMapper;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/consumer")
public class ConsumerController {

    @Autowired
    private ConsumerService consumerService;

    @PostMapping
    public ResponseEntity<ConsumerResponseDTO> createConsumer(@RequestBody @Valid ConsumerRegisterDTO consumerRegisterDTO) {
        Consumer consumer = consumerService.createConsumer(ConsumerMapper.toConsumer(consumerRegisterDTO));
        return ResponseEntity.status(201).body(ConsumerMapper.toConsumerResponseDTO(consumer));
    }

    @GetMapping
    public ResponseEntity<List<ConsumerResponseDTO>> getAllConsumers() {
        List<Consumer> consumers = consumerService.getAllConsumers();
        List<ConsumerResponseDTO> response = ConsumerMapper.toConsumerResponseDTOList(consumers);
        return ResponseEntity.ok(response);
    }

    @GetMapping("/{id}")
    public ResponseEntity<ConsumerResponseDTO> getConsumerById(@PathVariable String id) {
        Consumer consumer = consumerService.getConsumerById(id)
                .orElseThrow(() -> new RuntimeException("Consumer not found with id: " + id));
        return ResponseEntity.ok(ConsumerMapper.toConsumerResponseDTO(consumer));
    }

    @PutMapping("/{id}")
    public ResponseEntity<ConsumerResponseDTO> updateConsumer(@PathVariable String id, @RequestBody ConsumerRegisterDTO consumerRegisterDTO) {
        Consumer updatedConsumer = consumerService
                .updateConsumer(id, ConsumerMapper.toConsumer(consumerRegisterDTO));
        return ResponseEntity.ok(ConsumerMapper.toConsumerResponseDTO(updatedConsumer));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> deleteConsumer(@PathVariable String id) {
        consumerService.deleteConsumer(id);
        return ResponseEntity.noContent().build();
    }
}