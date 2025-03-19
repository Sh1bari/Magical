package ru.noxly.guildservice.model.entity;

import jakarta.persistence.Embeddable;
import lombok.*;

@Embeddable
@Getter
@ToString
@RequiredArgsConstructor
@NoArgsConstructor(force = true)
@Builder(builderMethodName = "init", setterPrefix = "set", toBuilder = true)
public class CostCharacteristic {
    private final Integer costFight;

    private final Integer costStrategy;

    private final Integer costMagic;

    private final Integer costTotal;
}
