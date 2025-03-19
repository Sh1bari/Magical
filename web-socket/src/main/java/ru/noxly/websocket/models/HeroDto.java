package ru.noxly.websocket.models;

import lombok.*;

@Getter
@ToString
@RequiredArgsConstructor
@NoArgsConstructor(access = AccessLevel.PRIVATE, force = true)
@Builder(builderMethodName = "init", setterPrefix = "set", toBuilder = true)
public class HeroDto {

    private final Long id;

    private final String name;

    private final HeroType type;

    private final LevelEnum level;

    private final HeroCharacteristicDto characteristics;

    private final HeroStatus status;
}
