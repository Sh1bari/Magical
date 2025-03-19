package ru.noxly.guildservice.kafka;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.kafka.support.SendResult;
import org.springframework.stereotype.Service;
import ru.noxly.guildservice.exceptions.GeneralException;

import java.util.concurrent.CompletableFuture;

@Slf4j
@Service
@RequiredArgsConstructor
public class KafkaProducerService {

    private final KafkaTemplate<String, String> kafkaTemplate;
    private final ObjectMapper objectMapper;

    /**
     * Отправка сообщения в топик Kafka.
     * @param topic Топик, куда отправляется сообщение.
     * @param key Ключ сообщения (может быть null).
     * @param value Значение сообщения.
     */
    public void sendMessage(String topic, String key, Object value) {
        try {
            String jsonValue = objectMapper.writeValueAsString(value);
            CompletableFuture<SendResult<String, String>> future = kafkaTemplate.send(topic, key, jsonValue);

            future.whenComplete((result, ex) -> {
                if (ex == null) {
                    log.info("Message sent to topic: {}, key: {}, value: {}, partition: {}, offset: {}",
                            topic, key, jsonValue,
                            result.getRecordMetadata().partition(),
                            result.getRecordMetadata().offset());
                } else {
                    log.error("Failed to send message to Kafka topic: {}", topic, ex);
                }
            });
        } catch (JsonProcessingException e) {
            log.error("Failed to serialize value to JSON", e);
            throw new GeneralException(500, "Failed to serialize value to JSON");
        }
    }
}
