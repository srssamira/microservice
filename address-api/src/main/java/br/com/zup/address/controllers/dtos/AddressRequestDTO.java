package br.com.zup.address.controllers.dtos;

import lombok.Data;

@Data
public class AddressRequestDTO {
    private String street;
    private String city;
    private String zipCode;
    private String state;
    private String consumerId;

}
