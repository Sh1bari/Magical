package ru.noxly.efs.webClient.main.model.request;

import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.RequiredArgsConstructor;
import lombok.ToString;
import ru.noxly.efs.webClient.main.model.embeded.CostCharacteristic;
import ru.noxly.efs.webClient.main.model.enums.LevelEnum;
import ru.noxly.efs.webClient.main.model.enums.MissionType;

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
