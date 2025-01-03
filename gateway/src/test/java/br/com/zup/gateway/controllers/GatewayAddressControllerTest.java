package br.com.zup.gateway.controllers;

import br.com.zup.gateway.controllers.dtos.AddressDTO;
import br.com.zup.gateway.infra.clients.address.dtos.AddressRegisterDTO;
import br.com.zup.gateway.infra.clients.address.dtos.AddressResponseDTO;
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

@WebMvcTest(AddressController.class)
public class GatewayAddressControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private ObjectMapper objectMapper;

    @MockitoBean
    private ConsumerAddressService consumerAddressService;

    private AddressRegisterDTO addressRegisterDTO;
    private AddressResponseDTO addressResponseDTO;
    private AddressDTO addressDTO;

    @BeforeEach
    public void setup(){

        String consumerId = UUID.randomUUID().toString();

        addressRegisterDTO = new AddressRegisterDTO();
        addressRegisterDTO.setConsumerId(consumerId);
        addressRegisterDTO.setStreet("rua da perola");
        addressRegisterDTO.setCity("uberlandia");
        addressRegisterDTO.setState("minas gerais");
        addressRegisterDTO.setZipCode("125468-546");

        addressResponseDTO = new AddressResponseDTO();
        addressResponseDTO.setId(UUID.randomUUID().toString());
        addressResponseDTO.setConsumerId(consumerId);
        addressResponseDTO.setStreet("rua da perola");
        addressResponseDTO.setCity("uberlandia");
        addressResponseDTO.setState("minas gerais");
        addressResponseDTO.setZipCode("125468-546");

        addressDTO = new AddressDTO(addressResponseDTO);
    }

    @Test
    public void test_if_return_status_200_and_addres_by_id() throws Exception {
        String id = UUID.randomUUID().toString();

        when(consumerAddressService.getAddressById(id)).thenReturn(addressResponseDTO);

        mockMvc.perform(MockMvcRequestBuilders.get("/address/" + id))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.jsonPath("$.id").value(addressResponseDTO.getId()))
                .andExpect(MockMvcResultMatchers.jsonPath("$.city").value(addressResponseDTO.getCity()))
                .andExpect(MockMvcResultMatchers.jsonPath("$.street").value(addressResponseDTO.getStreet()))
                .andExpect(MockMvcResultMatchers.jsonPath("$.zipCode").value(addressResponseDTO.getZipCode()))
                .andExpect(MockMvcResultMatchers.jsonPath("$.state").value(addressResponseDTO.getState()))
                .andExpect(MockMvcResultMatchers.jsonPath("$.consumerId").value(addressResponseDTO.getConsumerId()));
    }

    @Test
    public void test_if_return_status_200_and_return_all_adresses() throws Exception {
        List<AddressResponseDTO> addressResponseDTOList = new ArrayList<>();

        addressResponseDTOList.add(addressResponseDTO);

        when(consumerAddressService.getAllAddresses()).thenReturn(addressResponseDTOList);

        mockMvc.perform(MockMvcRequestBuilders.get("/address"))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.jsonPath("$[0].id").value(addressResponseDTO.getId()))
                .andExpect(MockMvcResultMatchers.jsonPath("$[0].city").value(addressResponseDTO.getCity()))
                .andExpect(MockMvcResultMatchers.jsonPath("$[0].street").value(addressResponseDTO.getStreet()))
                .andExpect(MockMvcResultMatchers.jsonPath("$[0].zipCode").value(addressResponseDTO.getZipCode()))
                .andExpect(MockMvcResultMatchers.jsonPath("$[0].state").value(addressResponseDTO.getState()))
                .andExpect(MockMvcResultMatchers.jsonPath("$[0].consumerId").value(addressResponseDTO.getConsumerId()));
    }


    @Test
    public void test_if_return_status_201_and_update_address() throws Exception {
        String id = UUID.randomUUID().toString();

        when(consumerAddressService.updateAddressById(id, addressRegisterDTO)).thenReturn(addressResponseDTO);

        mockMvc.perform(MockMvcRequestBuilders.put("/address/" + id)
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(addressRegisterDTO)))
                .andExpect(MockMvcResultMatchers.status().is(201))
                .andExpect(MockMvcResultMatchers.jsonPath("$.id").value(addressResponseDTO.getId()))
                .andExpect(MockMvcResultMatchers.jsonPath("$.city").value(addressResponseDTO.getCity()))
                .andExpect(MockMvcResultMatchers.jsonPath("$.street").value(addressResponseDTO.getStreet()))
                .andExpect(MockMvcResultMatchers.jsonPath("$.zipCode").value(addressResponseDTO.getZipCode()))
                .andExpect(MockMvcResultMatchers.jsonPath("$.state").value(addressResponseDTO.getState()))
                .andExpect(MockMvcResultMatchers.jsonPath("$.consumerId").value(addressResponseDTO.getConsumerId()));
    }

    @Test
    public void test_if_return_status_204_and_delete_address() throws Exception {
        String id = UUID.randomUUID().toString();

        Mockito.doNothing().when(consumerAddressService).deleteAddressById(id);

        mockMvc.perform(MockMvcRequestBuilders.delete("/address/"+id))
                .andExpect(MockMvcResultMatchers.status().isNoContent());
    }
}