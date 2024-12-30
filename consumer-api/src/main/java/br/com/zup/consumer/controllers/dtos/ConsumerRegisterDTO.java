package br.com.zup.consumer.controllers.dtos;

import br.com.zup.consumer.models.Consumer;

public class ConsumerRegisterDTO {

    private String name;
    private String age;
    private String email;

    public ConsumerRegisterDTO() {
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getAge() {
        return age;
    }

    public void setAge(String age) {
        this.age = age;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public Consumer toEntity() {
        Consumer consumer = new Consumer();
        consumer.setName(this.name);
        consumer.setAge(this.age);
        consumer.setEmail(this.email);
        return consumer;
    }
}
