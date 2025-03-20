package ru.noxly.efs.webClient.main.model.request;

import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.RequiredArgsConstructor;
import lombok.ToString;
import ru.noxly.efs.webClient.main.model.enums.ExpeditionStatus;

@Getter
@ToString
@RequiredArgsConstructor
@NoArgsConstructor(access = AccessLevel.PRIVATE, force = true)
@Builder(builderMethodName = "init", setterPrefix = "set", toBuilder = true)
public class ExpeditionFilter {

    private final ExpeditionStatus status;

    private final String name;
}
