package ru.noxly.guildservice.kafka.in;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;
import lombok.*;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.kafka.support.Acknowledgment;
import org.springframework.stereotype.Service;
import ru.noxly.guildservice.kafka.model.KafkaInModelDto;

@Service
@RequiredArgsConstructor
@Slf4j
@ConditionalOnProperty(name = "kafka.enabled", havingValue = "true", matchIfMissing = true)
public class KafkaConsumerService {

    private final ObjectMapper objectMapper = new ObjectMapper()
            .registerModule(new JavaTimeModule());

    /**
     * Обработка сообщений из топика Kafka.
     *
     * @param message        Сообщение, полученное из Kafka.
     * @param acknowledgment Объект для ручного подтверждения обработки сообщения.
     */
    @KafkaListener(topics = "${kafka.topics.in.calc-guild-team-result}", containerFactory = "kafkaListenerContainerFactory", groupId = "${kafka.group-id}")
    public void paymentSucceeded(String message, Acknowledgment acknowledgment) {
        try {
            val request = objectMapper.readValue(message, KafkaInModelDto.class);
            log.info("Processing message: " + request);
            acknowledgment.acknowledge();
        } catch (Exception e) {
            log.error("Error processing message: " + e.getMessage());
        }
    }
}
