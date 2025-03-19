package ru.noxly.websocket.models;

import lombok.*;

import java.time.OffsetDateTime;
import java.util.List;

@Getter
@ToString
@RequiredArgsConstructor
@NoArgsConstructor(access = AccessLevel.PRIVATE, force = true)
@Builder(builderMethodName = "init", setterPrefix = "set", toBuilder = true)
public class ExpeditionDto {

    private final Long id;

    private final String name;

    private final ExpeditionStatus expeditionStatus;

    private final List<TaskDto> tasks;

    private final OffsetDateTime createTime;

    private final OffsetDateTime sentTime;

    private final OffsetDateTime resultTime;

    private final TeamDto team;
}