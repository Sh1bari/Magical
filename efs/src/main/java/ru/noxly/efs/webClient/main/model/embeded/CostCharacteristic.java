package ru.noxly.efs.webClient.main.model.embeded;

import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.RequiredArgsConstructor;
import lombok.ToString;

@Getter
@ToString
@RequiredArgsConstructor
@NoArgsConstructor(force = true)
@Builder(builderMethodName = "init", setterPrefix = "set", toBuilder = true)
public class CostCharacteristic {

    private final Integer fight;

    private final Integer strategy;

    private final Integer magic;

    private final Integer total;
}
