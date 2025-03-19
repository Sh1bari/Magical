package ru.noxly.websocket.services;

import lombok.RequiredArgsConstructor;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.stereotype.Service;
import ru.noxly.websocket.models.ExpeditionDto;

import static java.lang.String.format;

@Service
@RequiredArgsConstructor
public class SocketMessageSender {

	private final SimpMessagingTemplate messagingTemplate;

	public void sendExpeditionInfo(final ExpeditionDto expedition) {
		messagingTemplate.convertAndSend(format("/topic/expedition/%s", expedition.getId()), expedition);
	}
}
