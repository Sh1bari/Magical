package ru.noxly.guildservice.redis;

import lombok.RequiredArgsConstructor;
import lombok.val;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Service;
import ru.noxly.guildservice.model.model.dto.ExpeditionDto;

@Service
@RequiredArgsConstructor
public class ExpeditionPublisher {

	@Autowired
	@Qualifier("pubSubRedisTemplate")
	private RedisTemplate<String, ExpeditionDto> redisTemplate;

	public void publishUpdate(ExpeditionDto dto) {
		val expeditionId = dto.getId();
		if (expeditionId == null) {
			throw new IllegalArgumentException("ExpeditionId не может быть null");
		}

		val channel = "expedition-updates:" + expeditionId;
		redisTemplate.convertAndSend(channel, dto);
	}
}
