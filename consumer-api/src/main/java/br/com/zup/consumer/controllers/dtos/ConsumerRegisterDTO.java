package br.com.zup.consumer.controllers.dtos;

import br.com.zup.consumer.models.Consumer;
import lombok.Data;

@Data
public class ConsumerRegisterDTO {

    private String name;
    private String age;
    private String email;

    public Consumer toEntity() {
        Consumer consumer = new Consumer();
        consumer.setName(this.name);
        consumer.setAge(this.age);
        consumer.setEmail(this.email);
        return consumer;
    }
}
