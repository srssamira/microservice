package br.com.zup.gateway.infra.clients.address.dtos;

import lombok.Data;

@Data
public class AddressResponseDTO {

    private String id;
    private String street;
    private String city;
    private String zipCode;
    private String state;
    private String consumerId;

}
