package ru.noxly.guildservice.model.model.request;

import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.RequiredArgsConstructor;
import lombok.ToString;
import ru.noxly.guildservice.model.entity.embeded.CostCharacteristic;
import ru.noxly.guildservice.model.enums.LevelEnum;
import ru.noxly.guildservice.model.enums.MissionType;

@Getter
@ToString
@RequiredArgsConstructor
@NoArgsConstructor(access = AccessLevel.PRIVATE, force = true)
@Builder(builderMethodName = "init", setterPrefix = "set", toBuilder = true)
public class CreateMissionRequest {

    private final String name;

    private final MissionType type;

    private final LevelEnum level;

    private final CostCharacteristic cost;
}
