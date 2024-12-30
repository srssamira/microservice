package br.com.zup.address.controllers.dtos;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class AddressResponseDTO {

    private String id;
    private String street;
    private String city;
    private String zipCode;
    private String state;
    private String consumerId;

}
