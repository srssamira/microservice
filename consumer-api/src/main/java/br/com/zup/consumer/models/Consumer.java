package br.com.zup.consumer.models;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import lombok.Data;
import org.hibernate.annotations.UuidGenerator;

@Entity
@Data
public class Consumer {

    @Id
    @UuidGenerator
    private String id;
    private String name;
    private Integer age;
    private String email;

}
