package br.com.zup.gateway.services.mappers;

import br.com.zup.gateway.controllers.dtos.ConsumerAddressRegisterDTO;
import br.com.zup.gateway.infra.clients.address.dtos.AddressRegisterDTO;
import br.com.zup.gateway.infra.clients.consumer.dtos.ConsumerRegisterDTO;
import com.fasterxml.jackson.databind.ObjectMapper;

public class ConsumerAddressMapper {

    private static final ObjectMapper mapper = new ObjectMapper();

    public static ConsumerRegisterDTO toConsumerRegisterDTO(ConsumerAddressRegisterDTO consumerAddressRegisterDTO) {
        return mapper.convertValue(consumerAddressRegisterDTO, ConsumerRegisterDTO.class);
    }

    public static AddressRegisterDTO toAddressRegisterDTO(ConsumerAddressRegisterDTO consumerAddressRegisterDTO, String consumerId) {
        AddressRegisterDTO addressRegisterDTO = new AddressRegisterDTO();
        addressRegisterDTO.setConsumerId(consumerId);
        addressRegisterDTO.setStreet(consumerAddressRegisterDTO.getAddress().getStreet());
        addressRegisterDTO.setCity(consumerAddressRegisterDTO.getAddress().getCity());
        addressRegisterDTO.setState(consumerAddressRegisterDTO.getAddress().getState());
        addressRegisterDTO.setZipCode(consumerAddressRegisterDTO.getAddress().getZipCode());
        return addressRegisterDTO;
    }


}
