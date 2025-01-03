package br.com.zup.gateway.controllers;

import br.com.zup.gateway.infra.clients.consumer.dtos.ConsumerRegisterDTO;
import br.com.zup.gateway.infra.clients.consumer.dtos.ConsumerResponseDTO;
import br.com.zup.gateway.services.ConsumerAddressService;
import br.com.zup.gateway.services.ConsumerAddressServiceImpl;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.bean.override.mockito.MockitoBean;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

import static org.mockito.Mockito.when;

@WebMvcTest(ConsumerController.class)
public class GatewayConsumerControllerTest {
    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private ObjectMapper objectMapper;

    @MockitoBean
    private ConsumerAddressService consumerAddressService;


    private ConsumerRegisterDTO consumerRegisterDTO;
    private ConsumerResponseDTO consumerResponseDTO;


    @BeforeEach
    public void setup(){

        consumerRegisterDTO = new ConsumerRegisterDTO();
        consumerRegisterDTO.setAge("20");
        consumerRegisterDTO.setEmail("samira@teste.com");
        consumerRegisterDTO.setName("samira");

        consumerResponseDTO = new ConsumerResponseDTO();
        consumerResponseDTO.setId(UUID.randomUUID().toString());
        consumerResponseDTO.setAge("20");
        consumerResponseDTO.setEmail("samira@teste.com");
        consumerResponseDTO.setName("samira");


    }

    @Test
    public void test_if_return_status_200_and_consumer_by_id() throws Exception {
        String id = UUID.randomUUID().toString();

        when(consumerAddressService.getConsumerById(id)).thenReturn(consumerResponseDTO);

        mockMvc.perform(MockMvcRequestBuilders.get("/consumer/" + id))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.jsonPath("$.id").value(consumerResponseDTO.getId()))
                .andExpect(MockMvcResultMatchers.jsonPath("$.name").value(consumerResponseDTO.getName()))
                .andExpect(MockMvcResultMatchers.jsonPath("$.age").value(consumerResponseDTO.getAge()))
                .andExpect(MockMvcResultMatchers.jsonPath("$.email").value(consumerResponseDTO.getEmail()));
    }

    @Test
    public void test_if_return_status_200_and_all_consumers() throws Exception {
        List<ConsumerResponseDTO> responseDTOList = new ArrayList<>();
        responseDTOList.add(consumerResponseDTO);

        when(consumerAddressService.getAllConsumers()).thenReturn(responseDTOList);

        mockMvc.perform(MockMvcRequestBuilders.get("/consumer"))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.jsonPath("$[0].id").value(consumerResponseDTO.getId()))
                .andExpect(MockMvcResultMatchers.jsonPath("$[0].name").value(consumerResponseDTO.getName()))
                .andExpect(MockMvcResultMatchers.jsonPath("$[0].age").value(consumerResponseDTO.getAge()))
                .andExpect(MockMvcResultMatchers.jsonPath("$[0].email").value(consumerResponseDTO.getEmail()));
    }

    @Test
    public void test_if_return_status_201_and_update_consumer() throws Exception {
        String id = UUID.randomUUID().toString();

        when(consumerAddressService.updateConsumer(id, consumerRegisterDTO)).thenReturn(consumerResponseDTO);

        System.out.println(objectMapper.writeValueAsString(consumerResponseDTO));

        mockMvc.perform(MockMvcRequestBuilders.put("/consumer/" + id)
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(consumerRegisterDTO)))
                .andExpect(MockMvcResultMatchers.status().is(201))
                .andExpect(MockMvcResultMatchers.jsonPath("$.id").value(consumerResponseDTO.getId()))
                .andExpect(MockMvcResultMatchers.jsonPath("$.name").value(consumerResponseDTO.getName()))
                .andExpect(MockMvcResultMatchers.jsonPath("$.age").value(consumerResponseDTO.getAge()))
                .andExpect(MockMvcResultMatchers.jsonPath("$.email").value(consumerResponseDTO.getEmail()));
    }

    @Test
    public void test_if_return_status_204_and_delete_consumer() throws Exception {
        String id = UUID.randomUUID().toString();

        Mockito.doNothing().when(consumerAddressService).deleteConsumerById(id);

        mockMvc.perform(MockMvcRequestBuilders.delete("/consumer/" + id))
                .andExpect(MockMvcResultMatchers.status().isNoContent());
    }
}