package ru.noxly.websocket.models;

import lombok.*;

@Getter
@ToString
@RequiredArgsConstructor
@NoArgsConstructor(access = AccessLevel.PRIVATE, force = true)
@Builder(builderMethodName = "init", setterPrefix = "set", toBuilder = true)
public class MissionDto {

    private final Long id;

    private final String name;

    private final MissionType missionType;

    private final LevelEnum level;

    private final CostCharacteristicDto cost;
}
