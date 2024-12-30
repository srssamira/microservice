package br.com.zup.consumer.controllers;

import br.com.zup.consumer.controllers.dtos.ConsumerRegisterDTO;
import br.com.zup.consumer.controllers.dtos.ConsumerResponseDTO;
import br.com.zup.consumer.models.Consumer;
import br.com.zup.consumer.services.ConsumerService;
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

    // Create
    @PostMapping
    public ResponseEntity<ConsumerResponseDTO> createConsumer(@RequestBody ConsumerRegisterDTO consumerRegisterDTO) {
        Consumer consumer = consumerService.createConsumer(consumerRegisterDTO.toEntity());
        return ResponseEntity.status(201).body(ConsumerResponseDTO.fromEntity(consumer));
    }

    // Read (Get all)
    @GetMapping
    public ResponseEntity<List<ConsumerResponseDTO>> getAllConsumers() {
        List<Consumer> consumers = consumerService.getAllConsumers();
        List<ConsumerResponseDTO> response = consumers.stream()
                .map(ConsumerResponseDTO::fromEntity)
                .collect(Collectors.toList());
        return ResponseEntity.ok(response);
    }

    // Read (Get by ID)
    @GetMapping("/{id}")
    public ResponseEntity<ConsumerResponseDTO> getConsumerById(@PathVariable String id) {
        Consumer consumer = consumerService.getConsumerById(id)
                .orElseThrow(() -> new RuntimeException("Consumer not found with id: " + id));
        return ResponseEntity.ok(ConsumerResponseDTO.fromEntity(consumer));
    }

    // Update
    @PutMapping("/{id}")
    public ResponseEntity<ConsumerResponseDTO> updateConsumer(@PathVariable String id, @RequestBody ConsumerRegisterDTO consumerRegisterDTO) {
        Consumer updatedConsumer = consumerService.updateConsumer(id, consumerRegisterDTO.toEntity());
        return ResponseEntity.ok(ConsumerResponseDTO.fromEntity(updatedConsumer));
    }

    // Delete
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteConsumer(@PathVariable String id) {
        consumerService.deleteConsumer(id);
        return ResponseEntity.noContent().build();
    }
}