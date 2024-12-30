package br.com.zup.address.services.mappers;

import br.com.zup.address.controllers.dtos.AddressRequestDTO;
import br.com.zup.address.controllers.dtos.AddressResponseDTO;
import br.com.zup.address.models.Address;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.util.ArrayList;
import java.util.List;

public class AddressMapper {

    private static final ObjectMapper mapper = new ObjectMapper();

    public static Address toAddress(AddressRequestDTO addressRequestDTO) {
        return mapper.convertValue(addressRequestDTO, Address.class);
    }

    public static AddressResponseDTO toAddressResponseDTO(Address address) {
        return mapper.convertValue(address, AddressResponseDTO.class);
    }

    public static List<AddressResponseDTO> toAddressResponseDTOList(List<Address> addresses) {

        if (addresses == null || addresses.isEmpty())
            return new ArrayList<>();

        List<AddressResponseDTO> addressResponseDTOs = new ArrayList<>();
        for (Address address : addresses) {
            addressResponseDTOs.add(toAddressResponseDTO(address));
        }
        return addressResponseDTOs;
    }
}
