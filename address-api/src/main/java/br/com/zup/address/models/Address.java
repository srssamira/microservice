package br.com.zup.address.models;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import lombok.Data;
import org.hibernate.annotations.UuidGenerator;

@Entity
@Data
public class Address {

    @Id
    @UuidGenerator
    private String id;
    private String street;
    private String city;
    private String zipCode;
    private String state;
    @Column(nullable = false)
    private String consumerId;

}
