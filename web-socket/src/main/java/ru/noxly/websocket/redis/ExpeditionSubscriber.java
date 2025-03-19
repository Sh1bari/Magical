package ru.noxly.websocket.redis;

import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import lombok.val;
import org.springframework.stereotype.Service;
import ru.noxly.websocket.models.ExpeditionDto;
import ru.noxly.websocket.services.SocketMessageSender;

import java.io.IOException;

@Service
@Slf4j
@RequiredArgsConstructor
public class ExpeditionSubscriber {

    private final SocketMessageSender sender;

    private final ObjectMapper objectMapper;

    public void receiveMessage(String message, String channel) {
        try {
            val expeditionDto = objectMapper.readValue(message, ExpeditionDto.class);
            sender.sendExpeditionInfo(expeditionDto);
            log.info("🔔 Получено обновление для expeditionId={} из канала {}: {}", expeditionDto.getId(), channel, expeditionDto);

        } catch (IOException e) {
            log.error("Ошибка десериализации сообщения: {}", e.getMessage(), e);
        }
    }
}
