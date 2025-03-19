package ru.noxly.efs.webClient.main.model.dto;

import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.RequiredArgsConstructor;
import lombok.ToString;
import ru.noxly.efs.webClient.main.model.enums.LevelEnum;
import ru.noxly.efs.webClient.main.model.enums.MissionType;

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
