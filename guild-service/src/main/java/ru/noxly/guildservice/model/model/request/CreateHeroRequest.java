package ru.noxly.guildservice.model.model.request;

import lombok.*;
import ru.noxly.guildservice.model.entity.embeded.HeroCharacteristic;
import ru.noxly.guildservice.model.enums.HeroStatus;
import ru.noxly.guildservice.model.enums.HeroType;
import ru.noxly.guildservice.model.enums.LevelEnum;

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
