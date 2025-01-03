package br.com.zup.address.controllers.dtos;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class AddressRegisterDTO {

    @NotNull(message = "Street can't be null")
    @NotBlank(message = "Street can't be blank")
    private String street;

    @NotNull(message = "City can't be null")
    @NotBlank(message = "City can't be blank")
    private String city;

    @NotNull(message = "ZipCode can't be null")
    @NotBlank(message = "ZipCode can't be blank")
    private String zipCode;

    @NotNull(message = "State can't be null")
    @NotBlank(message = "State can't be blank")
    private String state;

    @NotNull(message = "ConsumerId can't be null")
    @NotBlank(message = "ConsumerId can't be blank")
    private String consumerId;

}
