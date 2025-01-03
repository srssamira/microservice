package br.com.zup.gateway.services;

import br.com.zup.gateway.controllers.dtos.ConsumerAddressRegisterDTO;
import br.com.zup.gateway.controllers.dtos.ConsumerAddressResponseDTO;
import br.com.zup.gateway.infra.clients.address.AddressClient;
import br.com.zup.gateway.infra.clients.address.dtos.AddressRegisterDTO;
import br.com.zup.gateway.infra.clients.address.dtos.AddressResponseDTO;
import br.com.zup.gateway.infra.clients.consumer.ConsumerClient;
import br.com.zup.gateway.infra.clients.consumer.dtos.ConsumerRegisterDTO;
import br.com.zup.gateway.infra.clients.consumer.dtos.ConsumerResponseDTO;
import br.com.zup.gateway.services.mappers.ConsumerAddressMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ConsumerAddressService {

    @Autowired
    private ConsumerClient consumerClient;

    @Autowired
    private AddressClient addressClient;

    public ConsumerAddressResponseDTO registerConsumerAddress(ConsumerAddressRegisterDTO consumerAddressRegisterDTO) {
        ConsumerResponseDTO consumerResponseDTO = registerConsumer(consumerAddressRegisterDTO);
        AddressResponseDTO addressResponseDTO = registerAddress(consumerAddressRegisterDTO, consumerResponseDTO.getId());
        return new ConsumerAddressResponseDTO(consumerResponseDTO, addressResponseDTO);
    }

    private ConsumerResponseDTO registerConsumer(ConsumerAddressRegisterDTO consumerAddressRegisterDTO) {
        ConsumerRegisterDTO consumerRegisterDTO = ConsumerAddressMapper.toConsumerRegisterDTO(consumerAddressRegisterDTO);
        return consumerClient.registerConsumerClient(consumerRegisterDTO);
    }

    private AddressResponseDTO registerAddress(ConsumerAddressRegisterDTO consumerAddressRegisterDTO, String consumerId) {
        AddressRegisterDTO addressRegisterDTO = ConsumerAddressMapper.toAddressRegisterDTO(consumerAddressRegisterDTO, consumerId);
        return addressClient.registerAddress(addressRegisterDTO);
    }

    public AddressResponseDTO getAddressById(String addressId) {
        return addressClient.getAddressById(addressId);
    }

    public ConsumerAddressResponseDTO getConsumerAddressById(String consumerId) {
        ConsumerResponseDTO consumerResponseDTO = consumerClient.getConsumer(consumerId);
        AddressResponseDTO addressResponseDTO = addressClient.getAddresByConsumerId(consumerId);
        return new ConsumerAddressResponseDTO(consumerResponseDTO, addressResponseDTO);
    }

    public List<AddressResponseDTO> getAllAddresses() {
        return addressClient.getAllAddresses();
    }

    public ConsumerResponseDTO getConsumerById(String consumerId) {
        return consumerClient.getConsumer(consumerId);
    }

    public List<ConsumerResponseDTO> getAllConsumers() {
        return consumerClient.getAllConsumers();
    }

    public AddressResponseDTO updateAddressById(String addressId, AddressRegisterDTO addressRegisterDTO) {
        return addressClient.updateAddress(addressId, addressRegisterDTO);
    }

    public ConsumerResponseDTO updateConsumer(String consumerId, ConsumerRegisterDTO consumerRegisterDTO) {
        return consumerClient.updateConsumer(consumerId, consumerRegisterDTO);
    }

    public void deleteAddressById(String addressId) {
        addressClient.deleteAddress(addressId);
    }

    public void deleteConsumerById(String consumerId) {
        consumerClient.deleteConsumer(consumerId);
    }

}