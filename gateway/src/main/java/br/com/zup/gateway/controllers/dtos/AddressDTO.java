package br.com.zup.gateway.controllers.dtos;

import br.com.zup.gateway.infra.clients.address.dtos.AddressResponseDTO;

public class AddressDTO {
    private String street;
    private String city;
    private String zipCode;
    private String state;
    private String consumerId;

    public AddressDTO() {
    }

    public AddressDTO(AddressResponseDTO addressResponseDTO) {
        this.city = addressResponseDTO.getCity();
        this.consumerId = addressResponseDTO.getConsumerId();
        this.zipCode = addressResponseDTO.getZipCode();
        this.state = addressResponseDTO.getState();
        this.street = addressResponseDTO.getStreet();
    }

    public String getStreet() {
        return street;
    }

    public void setStreet(String street) {
        this.street = street;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getZipCode() {
        return zipCode;
    }

    public void setZipCode(String zipCode) {
        this.zipCode = zipCode;
    }

    public String getState() {
        return state;
    }

    public void setState(String state) {
        this.state = state;
    }

    public String getConsumerId() {
        return consumerId;
    }

    public void setConsumerId(String consumerId) {
        this.consumerId = consumerId;
    }
}
