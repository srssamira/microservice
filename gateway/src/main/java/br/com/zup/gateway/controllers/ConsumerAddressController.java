package br.com.zup.gateway.controllers;

import br.com.zup.gateway.controllers.dtos.ConsumerAddressRegisterDTO;
import br.com.zup.gateway.controllers.dtos.ConsumerAddressResponseDTO;
import br.com.zup.gateway.infra.clients.address.dtos.AddressResponseDTO;
import br.com.zup.gateway.services.ConsumerAddressService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/consumer-address")
public class ConsumerAddressController {

    @Autowired
    private ConsumerAddressService consumerAddressService;

    @PostMapping
    public ConsumerAddressResponseDTO register(@RequestBody ConsumerAddressRegisterDTO registerDTO){
        return consumerAddressService.registerConsumerAddress(registerDTO);
    }

    @GetMapping("/address/{addressId}")
    public AddressResponseDTO findById(@PathVariable("addressId") String addressId){
        return consumerAddressService.getAddressById(addressId);
    }

    @GetMapping("/address")
    public List<AddressResponseDTO> findAllAddresses(){
        return consumerAddressService.getAllAddresses();
    }
}
