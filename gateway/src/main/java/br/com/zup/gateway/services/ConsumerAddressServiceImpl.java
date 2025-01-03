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
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ConsumerAddressServiceImpl implements ConsumerAddressService {

    private static final Logger LOG = LoggerFactory.getLogger(ConsumerAddressServiceImpl.class);

    @Autowired
    private ConsumerClient consumerClient;

    @Autowired
    private AddressClient addressClient;


    public ConsumerAddressResponseDTO registerConsumerAddress(ConsumerAddressRegisterDTO consumerAddressRegisterDTO) {
        LOG.info("Registering consumer address");

        ConsumerResponseDTO consumerResponseDTO = registerConsumer(consumerAddressRegisterDTO);
        AddressResponseDTO addressResponseDTO = registerAddress(consumerAddressRegisterDTO, consumerResponseDTO.getId());

        ConsumerAddressResponseDTO consumerAddressResponseDTO = new ConsumerAddressResponseDTO(consumerResponseDTO, addressResponseDTO);

        LOG.info("Registered consumer address");

        return consumerAddressResponseDTO;
    }


    private ConsumerResponseDTO registerConsumer(ConsumerAddressRegisterDTO consumerAddressRegisterDTO) {
        LOG.info("Registering consumer");

        ConsumerRegisterDTO consumerRegisterDTO = ConsumerAddressMapper.toConsumerRegisterDTO(consumerAddressRegisterDTO);

        ConsumerResponseDTO consumerResponseDTO = consumerClient.registerConsumerClient(consumerRegisterDTO);

        LOG.info("Registered consumer");

        return consumerResponseDTO;
    }


    private AddressResponseDTO registerAddress(ConsumerAddressRegisterDTO consumerAddressRegisterDTO, String consumerId) {
        LOG.info("Registering address");

        AddressRegisterDTO addressRegisterDTO = ConsumerAddressMapper.toAddressRegisterDTO(consumerAddressRegisterDTO, consumerId);

        AddressResponseDTO addressResponseDTO = addressClient.registerAddress(addressRegisterDTO);

        LOG.info("Registered address");

        return addressResponseDTO;
    }


    public AddressResponseDTO getAddressById(String addressId) {
        LOG.info("Getting address by id");

        AddressResponseDTO addressResponseDTO = addressClient.getAddressById(addressId);

        LOG.info("Finished retrieving address by id");

        return addressResponseDTO;
    }


    public ConsumerAddressResponseDTO getConsumerAddressById(String consumerId) {
        LOG.info("Getting consumer address by id");

        ConsumerResponseDTO consumerResponseDTO = consumerClient.getConsumer(consumerId);
        AddressResponseDTO addressResponseDTO = addressClient.getAddressByConsumerId(consumerId);

        ConsumerAddressResponseDTO consumerAddressResponseDTO = new ConsumerAddressResponseDTO(consumerResponseDTO, addressResponseDTO);

        LOG.info("Finished retrieving consumer address by id");

        return consumerAddressResponseDTO;
    }


    public List<AddressResponseDTO> getAllAddresses() {
        LOG.info("Getting all addresses");

        List<AddressResponseDTO> addressResponseDTOList = addressClient.getAllAddresses();

        LOG.info("Finished retrieving all addresses");

        return addressResponseDTOList;
    }


    public ConsumerResponseDTO getConsumerById(String consumerId) {
        LOG.info("Getting consumer by id");

        ConsumerResponseDTO consumerResponseDTO = consumerClient.getConsumer(consumerId);

        LOG.info("Finished retrieving consumer by id");

        return consumerResponseDTO;
    }


    public List<ConsumerResponseDTO> getAllConsumers() {
        LOG.info("Getting all consumers");

        List<ConsumerResponseDTO> consumerResponseDTOList = consumerClient.getAllConsumers();

        LOG.info("Finished retrieving all consumers");

        return consumerResponseDTOList;
    }


    public AddressResponseDTO updateAddressById(String addressId, AddressRegisterDTO addressRegisterDTO) {
        LOG.info("Updating address by id");

        AddressResponseDTO addressResponseDTO = addressClient.updateAddress(addressId, addressRegisterDTO);

        LOG.info("Finished updating address by id");

        return addressResponseDTO;
    }


    public ConsumerResponseDTO updateConsumer(String consumerId, ConsumerRegisterDTO consumerRegisterDTO) {

        LOG.info("Updating consumer by id");

        ConsumerResponseDTO consumerResponseDTO = consumerClient.updateConsumer(consumerId, consumerRegisterDTO);

        LOG.info("Finished updating consumer by id");

        return consumerResponseDTO;
    }


    public void deleteAddressById(String addressId) {
        LOG.info("Deleting address by id");

        addressClient.deleteAddress(addressId);

        LOG.info("Finished deleting address by id");
    }


    public void deleteConsumerById(String consumerId) {
        LOG.info("Deleting consumer by id");

        consumerClient.deleteConsumer(consumerId);

        LOG.info("Finished deleting consumer by id");
    }

}