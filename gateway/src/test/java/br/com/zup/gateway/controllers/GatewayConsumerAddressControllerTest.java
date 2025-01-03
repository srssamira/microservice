package br.com.zup.gateway.controllers;

import br.com.zup.gateway.controllers.dtos.AddressDTO;
import br.com.zup.gateway.controllers.dtos.ConsumerAddressRegisterDTO;
import br.com.zup.gateway.controllers.dtos.ConsumerAddressResponseDTO;
import br.com.zup.gateway.infra.clients.address.dtos.AddressResponseDTO;
import br.com.zup.gateway.infra.clients.consumer.dtos.ConsumerResponseDTO;
import br.com.zup.gateway.services.ConsumerAddressService;
import br.com.zup.gateway.services.ConsumerAddressServiceImpl;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.bean.override.mockito.MockitoBean;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

import java.util.UUID;

import static org.mockito.Mockito.when;

@WebMvcTest(ConsumerAddressController.class)
public class GatewayConsumerAddressControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private ObjectMapper objectMapper;

    @MockitoBean
    private ConsumerAddressService consumerAddressService;

    private ConsumerAddressRegisterDTO registerDTO;
    private ConsumerAddressResponseDTO responseDTO;
    private AddressResponseDTO addressResponseDTO;
    private ConsumerResponseDTO consumerResponseDTO;
    private AddressDTO addressDTO;

    @BeforeEach
    public void setup(){

        consumerResponseDTO = new ConsumerResponseDTO();
        consumerResponseDTO.setId(UUID.randomUUID().toString());
        consumerResponseDTO.setAge("20");
        consumerResponseDTO.setEmail("samira@teste.com");
        consumerResponseDTO.setName("samira");

        addressResponseDTO = new AddressResponseDTO();
        addressResponseDTO.setId(UUID.randomUUID().toString());
        addressResponseDTO.setCity("uberlandia");
        addressResponseDTO.setState("minas gerais");
        addressResponseDTO.setStreet("rua das petalas");
        addressResponseDTO.setZipCode("123456-789");
        addressResponseDTO.setConsumerId(UUID.randomUUID().toString());

        addressDTO = new AddressDTO(addressResponseDTO);

        registerDTO = new ConsumerAddressRegisterDTO();
        registerDTO.setAddress(addressDTO);
        registerDTO.setAge("20");
        registerDTO.setEmail("samira@teste.com");
        registerDTO.setName("samira");
        responseDTO = new ConsumerAddressResponseDTO(consumerResponseDTO, addressResponseDTO);
    }

    @Test
    public void test_if_create_consumer_address_and_return_status_201() throws Exception {

        when(consumerAddressService.registerConsumerAddress(registerDTO)).thenReturn(responseDTO);

        mockMvc.perform(MockMvcRequestBuilders.post("/consumer-address")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(registerDTO)))
                .andExpect(MockMvcResultMatchers.status().is(201))
                .andExpect(MockMvcResultMatchers.jsonPath("$.id").value(consumerResponseDTO.getId()))
                .andExpect(MockMvcResultMatchers.jsonPath("$.name").value(consumerResponseDTO.getName()))
                .andExpect(MockMvcResultMatchers.jsonPath("$.age").value(consumerResponseDTO.getAge()))
                .andExpect(MockMvcResultMatchers.jsonPath("$.email").value(consumerResponseDTO.getEmail()))
                .andExpect(MockMvcResultMatchers.jsonPath("$.address.city").value(addressResponseDTO.getCity()))
                .andExpect(MockMvcResultMatchers.jsonPath("$.address.street").value(addressResponseDTO.getStreet()))
                .andExpect(MockMvcResultMatchers.jsonPath("$.address.state").value(addressResponseDTO.getState()))
                .andExpect(MockMvcResultMatchers.jsonPath("$.address.zipCode").value(addressResponseDTO.getZipCode()))
                .andExpect(MockMvcResultMatchers.jsonPath("$.address.consumerId").value(addressResponseDTO.getConsumerId()));
    }

    @Test
    public void test_if_return_status_200_and_return_consumer_and_address_by_consumer_id() throws Exception {
        String id = UUID.randomUUID().toString();

        when(consumerAddressService.getConsumerAddressById(id)).thenReturn(responseDTO);

        mockMvc.perform(MockMvcRequestBuilders.get("/consumer-address/"+id))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.jsonPath("$.id").value(consumerResponseDTO.getId()))
                .andExpect(MockMvcResultMatchers.jsonPath("$.name").value(consumerResponseDTO.getName()))
                .andExpect(MockMvcResultMatchers.jsonPath("$.age").value(consumerResponseDTO.getAge()))
                .andExpect(MockMvcResultMatchers.jsonPath("$.email").value(consumerResponseDTO.getEmail()))
                .andExpect(MockMvcResultMatchers.jsonPath("$.address.city").value(addressResponseDTO.getCity()))
                .andExpect(MockMvcResultMatchers.jsonPath("$.address.street").value(addressResponseDTO.getStreet()))
                .andExpect(MockMvcResultMatchers.jsonPath("$.address.state").value(addressResponseDTO.getState()))
                .andExpect(MockMvcResultMatchers.jsonPath("$.address.zipCode").value(addressResponseDTO.getZipCode()))
                .andExpect(MockMvcResultMatchers.jsonPath("$.address.consumerId").value(addressResponseDTO.getConsumerId()));
    }
}