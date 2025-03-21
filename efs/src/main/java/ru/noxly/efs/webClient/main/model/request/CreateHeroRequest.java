package ru.noxly.efs.webClient.main.model.request;

import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.RequiredArgsConstructor;
import lombok.ToString;
import ru.noxly.efs.webClient.main.model.embeded.HeroCharacteristic;
import ru.noxly.efs.webClient.main.model.enums.HeroType;
import ru.noxly.efs.webClient.main.model.enums.LevelEnum;

@Getter
@ToString
@RequiredArgsConstructor
@NoArgsConstructor(access = AccessLevel.PRIVATE, force = true)
@Builder(builderMethodName = "init", setterPrefix = "set", toBuilder = true)
public class CreateHeroRequest {

    private final String name;

    private final HeroType type;

    private final LevelEnum level;

    private final HeroCharacteristic heroCharacteristic;
}
