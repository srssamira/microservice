package br.com.zup.gateway.infra.clients.consumer.dtos;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class ConsumerRegisterDTO {
    private String name;
    private String age;
    private String email;

}

