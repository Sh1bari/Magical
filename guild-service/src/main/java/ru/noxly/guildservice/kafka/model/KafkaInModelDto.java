package ru.noxly.guildservice.kafka.model;

import lombok.*;

import java.util.List;

@Getter
@ToString
@RequiredArgsConstructor
@NoArgsConstructor(access = AccessLevel.PRIVATE, force = true)
@Builder(builderMethodName = "init", setterPrefix = "set", toBuilder = true)
public class KafkaInModelDto {

    private final Long expeditionId;

    private final List<Long> missions;
}
