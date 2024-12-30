package br.com.zup.consumer.services.mappers;

import br.com.zup.consumer.controllers.dtos.ConsumerRegisterDTO;
import br.com.zup.consumer.controllers.dtos.ConsumerResponseDTO;
import br.com.zup.consumer.models.Consumer;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.util.ArrayList;
import java.util.List;

public class ConsumerMapper {

    private static final ObjectMapper mapper = new ObjectMapper();

    public static Consumer toConsumer(ConsumerRegisterDTO registerDTO) {
        return mapper.convertValue(registerDTO, Consumer.class);
    }

    public static ConsumerResponseDTO toConsumerResponseDTO(Consumer consumer) {
        return mapper.convertValue(consumer, ConsumerResponseDTO.class);
    }

    public static List<ConsumerResponseDTO> toConsumerResponseDTOList(List<Consumer> consumers) {
        List<ConsumerResponseDTO> consumerResponseDTOList = new ArrayList<>();
        for (Consumer consumer : consumers) {
            consumerResponseDTOList.add(toConsumerResponseDTO(consumer));
        }
        return consumerResponseDTOList;
    }
}
