package br.com.zup.consumer.controllers.dtos;

import br.com.zup.consumer.models.Consumer;
import lombok.Data;

@Data
public class ConsumerResponseDTO {
    private String id;
    private String name;
    private Integer age;
    private String email;

}