package br.com.zup.gateway.services;

import br.com.zup.gateway.controllers.dtos.ConsumerAddressRegisterDTO;
import br.com.zup.gateway.controllers.dtos.ConsumerAddressResponseDTO;
import br.com.zup.gateway.infra.clients.address.dtos.AddressRegisterDTO;
import br.com.zup.gateway.infra.clients.address.dtos.AddressResponseDTO;
import br.com.zup.gateway.infra.clients.consumer.dtos.ConsumerRegisterDTO;
import br.com.zup.gateway.infra.clients.consumer.dtos.ConsumerResponseDTO;

import java.util.List;

public interface ConsumerAddressService {

    ConsumerAddressResponseDTO registerConsumerAddress(ConsumerAddressRegisterDTO consumerAddressRegisterDTO);

    AddressResponseDTO getAddressById(String addressId);

    ConsumerAddressResponseDTO getConsumerAddressById(String consumerId);

    List<AddressResponseDTO> getAllAddresses();

    ConsumerResponseDTO getConsumerById(String consumerId);

    List<ConsumerResponseDTO> getAllConsumers();

    AddressResponseDTO updateAddressById(String addressId, AddressRegisterDTO addressRegisterDTO);

    ConsumerResponseDTO updateConsumer(String consumerId, ConsumerRegisterDTO consumerRegisterDTO);

    void deleteAddressById(String addressId);

    void deleteConsumerById(String consumerId);
}
