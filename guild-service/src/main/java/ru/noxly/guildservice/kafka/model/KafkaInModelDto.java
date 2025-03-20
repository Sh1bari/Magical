package ru.noxly.guildservice.kafka.model;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.*;

import java.util.List;

@Getter
@ToString
@RequiredArgsConstructor
@NoArgsConstructor(access = AccessLevel.PRIVATE, force = true)
@Builder(builderMethodName = "init", setterPrefix = "set", toBuilder = true)
public class KafkaInModelDto {

    @JsonProperty("ExpeditionId")
    private final Long expeditionId;

    @JsonProperty("Heroes")
    private final List<Long> heroes;
}
