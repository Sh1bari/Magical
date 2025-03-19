package ru.noxly.guildservice.model.entity;

import jakarta.persistence.Embeddable;
import lombok.*;

@Embeddable
@Getter
@ToString
@RequiredArgsConstructor
@NoArgsConstructor(force = true)
@Builder(builderMethodName = "init", setterPrefix = "set", toBuilder = true)
public class HeroCharacteristic {

    private final Integer characteristicFight;

    private final Integer characteristicStrategy;

    private final Integer characteristicMagic;
}
