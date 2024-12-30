package br.com.zup.consumer.controllers.dtos;

import br.com.zup.consumer.models.Consumer;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

@Data
public class ConsumerRegisterDTO {

    @NotNull(message = "Name can't be null")
    @NotBlank(message = "Name can't be blank")
    private String name;

    @NotNull(message = "Age can't be null")
    @Min(value = 1, message = "Age must be greater than 0")
    private Integer age;

    @NotNull(message = "Email can't be null")
    @NotBlank(message = "Email can't be blank")
    private String email;

}
