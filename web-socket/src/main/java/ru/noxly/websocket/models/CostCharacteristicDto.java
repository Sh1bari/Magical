package ru.noxly.websocket.models;

import lombok.*;

@Getter
@ToString
@RequiredArgsConstructor
@NoArgsConstructor(access = AccessLevel.PRIVATE, force = true)
@Builder(builderMethodName = "init", setterPrefix = "set", toBuilder = true)
public class CostCharacteristicDto {

    private final Integer fight;

    private final Integer strategy;

    private final Integer magic;

    private final Integer total;
}