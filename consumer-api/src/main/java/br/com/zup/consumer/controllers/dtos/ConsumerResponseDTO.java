package br.com.zup.consumer.controllers.dtos;

import br.com.zup.consumer.models.Consumer;

public class ConsumerResponseDTO {
    private String id;
    private String name;
    private String age;
    private String email;

    // Getters e Setters
    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
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

    // Converte entidade para DTO
    public static ConsumerResponseDTO fromEntity(Consumer consumer) {
        ConsumerResponseDTO dto = new ConsumerResponseDTO();
        dto.setId(consumer.getId());
        dto.setName(consumer.getName());
        dto.setAge(consumer.getAge());
        dto.setEmail(consumer.getEmail());
        return dto;
    }
}