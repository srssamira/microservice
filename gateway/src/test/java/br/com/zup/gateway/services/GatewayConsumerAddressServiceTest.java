package br.com.zup.gateway.services;

import br.com.zup.gateway.controllers.dtos.AddressDTO;
import br.com.zup.gateway.controllers.dtos.ConsumerAddressRegisterDTO;
import br.com.zup.gateway.controllers.dtos.ConsumerAddressResponseDTO;
import br.com.zup.gateway.infra.clients.address.AddressClient;
import br.com.zup.gateway.infra.clients.address.dtos.AddressRegisterDTO;
import br.com.zup.gateway.infra.clients.address.dtos.AddressResponseDTO;
import br.com.zup.gateway.infra.clients.consumer.ConsumerClient;
import br.com.zup.gateway.infra.clients.consumer.dtos.ConsumerRegisterDTO;
import br.com.zup.gateway.infra.clients.consumer.dtos.ConsumerResponseDTO;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;


@ExtendWith(MockitoExtension.class)
public class GatewayConsumerAddressServiceTest {

    @Mock
    private ConsumerClient consumerClient;

    @Mock
    private AddressClient addressClient;

    @InjectMocks
    private ConsumerAddressServiceImpl consumerAddressService;

    private ConsumerResponseDTO consumerResponseDTO;
    private AddressResponseDTO addressResponseDTO;
    private ConsumerAddressRegisterDTO consumerAddressRegisterDTO;
    private ConsumerRegisterDTO consumerRegisterDTO;
    private AddressRegisterDTO addressRegisterDTO;

    @BeforeEach
    public void setup(){

        String consumerId = UUID.randomUUID().toString();

        consumerResponseDTO = new ConsumerResponseDTO();
        consumerResponseDTO.setId(consumerId);
        consumerResponseDTO.setName("maria carolina");
        consumerResponseDTO.setAge("20");
        consumerResponseDTO.setEmail("maria.carolina@teste.com");

        addressResponseDTO = new AddressResponseDTO();
        addressResponseDTO.setId(UUID.randomUUID().toString());
        addressResponseDTO.setCity("patos de minas");
        addressResponseDTO.setState("minas gerais");
        addressResponseDTO.setStreet("rua do cadeado");
        addressResponseDTO.setZipCode("987654-321");
        addressResponseDTO.setConsumerId(consumerId);

        AddressDTO addressDTO = new AddressDTO(addressResponseDTO);

        consumerAddressRegisterDTO = new ConsumerAddressRegisterDTO();
        consumerAddressRegisterDTO.setAge("20");
        consumerAddressRegisterDTO.setEmail("maria.carolina@teste.com");
        consumerAddressRegisterDTO.setName("maria carolina");
        consumerAddressRegisterDTO.setAddress(addressDTO);


        addressRegisterDTO = new AddressRegisterDTO();
        addressRegisterDTO.setCity("patos de minas");
        addressRegisterDTO.setState("minas gerais");
        addressRegisterDTO.setStreet("rua do cadeado");
        addressRegisterDTO.setZipCode("987654-321");
        addressRegisterDTO.setConsumerId(consumerId);


        consumerRegisterDTO = new ConsumerRegisterDTO();
        consumerRegisterDTO.setAge("20");
        consumerRegisterDTO.setEmail("maria.carolina@teste.com");
        consumerRegisterDTO.setName("maria carolina");
    }


    @Test
    public void test_when_register_consumer_address(){

        when(consumerClient.registerConsumerClient(any(ConsumerRegisterDTO.class)))
                .thenReturn(consumerResponseDTO);
        when(addressClient.registerAddress(any(AddressRegisterDTO.class)))
                .thenReturn(addressResponseDTO);

        ConsumerAddressResponseDTO responseDTO = consumerAddressService
                .registerConsumerAddress(consumerAddressRegisterDTO);

        assertEquals(responseDTO.getAddress().getCity(), addressResponseDTO.getCity());
        assertEquals(responseDTO.getId(), consumerResponseDTO.getId());
        assertEquals(responseDTO.getEmail(),consumerResponseDTO.getEmail());
        assertEquals(responseDTO.getAge(), consumerResponseDTO.getAge());
        assertEquals(responseDTO.getName(),consumerResponseDTO.getName());
    }


    @Test
    public void test_when_return_address_by_id(){

        when(addressClient.getAddressById(anyString())).thenReturn(addressResponseDTO);

        AddressResponseDTO responseDTO = consumerAddressService.getAddressById("1");
        assertEquals(responseDTO.getCity(), addressResponseDTO.getCity());
        assertEquals(responseDTO.getStreet(), addressResponseDTO.getStreet());
        assertEquals(responseDTO.getState(), addressResponseDTO.getState());
        assertEquals(responseDTO.getZipCode(), addressResponseDTO.getZipCode());
    }


    @Test
    public void test_when_return_consumer_address_by_consumer_id(){

        when(consumerClient.getConsumer(anyString())).thenReturn(consumerResponseDTO);
        when(addressClient.getAddressByConsumerId(anyString())).thenReturn(addressResponseDTO);

        ConsumerAddressResponseDTO responseDTO = consumerAddressService.getConsumerAddressById(anyString());

        assertEquals(responseDTO.getAddress().getCity(), addressResponseDTO.getCity());
        assertEquals(responseDTO.getId(), consumerResponseDTO.getId());
        assertEquals(responseDTO.getEmail(),consumerResponseDTO.getEmail());
        assertEquals(responseDTO.getAge(), consumerResponseDTO.getAge());
        assertEquals(responseDTO.getName(),consumerResponseDTO.getName());
    }


    @Test
    public void test_when_return_all_addresses(){
        List<AddressResponseDTO> addressResponseDTOList = new ArrayList<>();
        addressResponseDTOList.add(addressResponseDTO);

        when(addressClient.getAllAddresses()).thenReturn(addressResponseDTOList);

        List<AddressResponseDTO> responseDTOList = consumerAddressService.getAllAddresses();

        assertEquals(responseDTOList.get(0).getCity(), addressResponseDTO.getCity());
        assertEquals(responseDTOList.get(0).getStreet(), addressResponseDTO.getStreet());
        assertEquals(responseDTOList.get(0).getState(), addressResponseDTO.getState());
        assertEquals(responseDTOList.get(0).getZipCode(), addressResponseDTO.getZipCode());
    }

    @Test
    public void test_when_return_consumer_by_id(){
        when(consumerClient.getConsumer(anyString())).thenReturn(consumerResponseDTO);

        ConsumerResponseDTO responseDTO =  consumerAddressService.getConsumerById("1");

        assertEquals(responseDTO.getId(), consumerResponseDTO.getId());
        assertEquals(responseDTO.getEmail(),consumerResponseDTO.getEmail());
        assertEquals(responseDTO.getAge(), consumerResponseDTO.getAge());
        assertEquals(responseDTO.getName(),consumerResponseDTO.getName());
    }


    @Test
    public void test_when_return_all_consumers(){
        List<ConsumerResponseDTO> consumerResponseDTOList = new ArrayList<>();
        consumerResponseDTOList.add(consumerResponseDTO);

        when(consumerClient.getAllConsumers()).thenReturn(consumerResponseDTOList);

        List<ConsumerResponseDTO> responseDTOList = consumerAddressService.getAllConsumers();

        assertEquals(responseDTOList.get(0).getId(), consumerResponseDTO.getId());
        assertEquals(responseDTOList.get(0).getEmail(),consumerResponseDTO.getEmail());
        assertEquals(responseDTOList.get(0).getAge(), consumerResponseDTO.getAge());
        assertEquals(responseDTOList.get(0).getName(),consumerResponseDTO.getName());
    }


    @Test
    public void test_when_update_address_by_id(){
        when(addressClient.updateAddress(anyString(), any(AddressRegisterDTO.class)))
                .thenReturn(addressResponseDTO);

        AddressResponseDTO responseDTO = consumerAddressService
                .updateAddressById(addressResponseDTO.getId(), addressRegisterDTO);

        assertEquals(responseDTO.getCity(), addressResponseDTO.getCity());
        assertEquals(responseDTO.getStreet(), addressResponseDTO.getStreet());
        assertEquals(responseDTO.getState(), addressResponseDTO.getState());
        assertEquals(responseDTO.getZipCode(), addressResponseDTO.getZipCode());
    }


    @Test
    public void test_when_update_consumer_by_id(){
        when(consumerClient.updateConsumer(anyString(), any(ConsumerRegisterDTO.class)))
                .thenReturn(consumerResponseDTO);

        ConsumerResponseDTO responseDTO = consumerAddressService
                .updateConsumer(consumerResponseDTO.getId(), consumerRegisterDTO);

        assertEquals(responseDTO.getId(), consumerResponseDTO.getId());
        assertEquals(responseDTO.getEmail(),consumerResponseDTO.getEmail());
        assertEquals(responseDTO.getAge(), consumerResponseDTO.getAge());
        assertEquals(responseDTO.getName(),consumerResponseDTO.getName());
    }


    @Test
    public void test_when_delete_address_by_id(){
        doNothing().when(addressClient).deleteAddress(anyString());

        consumerAddressService.deleteAddressById("abcd");

        verify(addressClient, times(1)).deleteAddress("abcd");
    }


    @Test
    public void test_when_delete_consumer_by_id(){
        doNothing().when(consumerClient).deleteConsumer(anyString());

        consumerAddressService.deleteConsumerById("abcd");

        verify(consumerClient, times(1)).deleteConsumer("abcd");
    }
}