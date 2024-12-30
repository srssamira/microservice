package br.com.zup.gateway.controllers;

import br.com.zup.gateway.controllers.dtos.ConsumerAddressRegisterDTO;
import br.com.zup.gateway.controllers.dtos.ConsumerAddressResponseDTO;
import br.com.zup.gateway.services.ConsumerAddressService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/consumer-address")
public class ConsumerAddressController {

    @Autowired
    private ConsumerAddressService consumerAddressService;

    @PostMapping
    public ConsumerAddressResponseDTO register(@RequestBody ConsumerAddressRegisterDTO registerDTO){
        return consumerAddressService.registerConsumerAddress(registerDTO);
    }
}
