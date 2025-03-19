package ru.noxly.guildservice.model.entity.embeded;

import jakarta.persistence.Embeddable;
import lombok.*;

@Embeddable
@Getter
@ToString
@RequiredArgsConstructor
@NoArgsConstructor(force = true)
@Builder(builderMethodName = "init", setterPrefix = "set", toBuilder = true)
public class HeroCharacteristic {

    private final Integer fight;

    private final Integer strategy;

    private final Integer magic;
}
