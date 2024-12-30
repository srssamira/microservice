package br.com.zup.consumer.services;

import br.com.zup.consumer.repositories.ConsumerRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import br.com.zup.consumer.models.Consumer;

import java.util.List;
import java.util.Optional;

@Service
public class ConsumerService {
    private static final Logger log = LoggerFactory.getLogger(ConsumerService.class);

    @Autowired
    private ConsumerRepository consumerRepository;

    // Create
    public Consumer createConsumer(Consumer consumer) {
        log.info("Start create consumer flow");
        return consumerRepository.save(consumer);
    }

    // Read (Get all consumers)
    public List<Consumer> getAllConsumers() {
        return consumerRepository.findAll();
    }

    // Read (Get consumer by ID)
    public Optional<Consumer> getConsumerById(String id) {
        return consumerRepository.findById(id);
    }

    // Update
    public Consumer updateConsumer(String id, Consumer updatedConsumer) {
        return consumerRepository.findById(id).map(consumer -> {
            consumer.setName(updatedConsumer.getName());
            consumer.setAge(updatedConsumer.getAge());
            consumer.setEmail(updatedConsumer.getEmail());
            return consumerRepository.save(consumer);
        }).orElseThrow(() -> new RuntimeException("Consumer not found with id: " + id));
    }

    // Delete
    public void deleteConsumer(String id) {
        if (consumerRepository.existsById(id)) {
            consumerRepository.deleteById(id);
        } else {
            log.error("Delete consumer blocked: consumer not found: id "+id);
            throw new RuntimeException("Consumer not found with id: " + id);
        }
    }
}