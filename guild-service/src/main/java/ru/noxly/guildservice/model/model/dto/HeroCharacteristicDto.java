package ru.noxly.guildservice.model.model.dto;

import lombok.*;

@Getter
@ToString
@RequiredArgsConstructor
@NoArgsConstructor(access = AccessLevel.PRIVATE, force = true)
@Builder(builderMethodName = "init", setterPrefix = "set", toBuilder = true)
public class HeroCharacteristicDto {

    private final Integer fight;

    private final Integer strategy;

    private final Integer magic;
}