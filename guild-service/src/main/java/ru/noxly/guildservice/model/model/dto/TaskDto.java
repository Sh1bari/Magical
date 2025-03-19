package ru.noxly.guildservice.model.model.dto;

import lombok.*;

import java.util.List;

@Getter
@ToString
@RequiredArgsConstructor
@NoArgsConstructor(access = AccessLevel.PRIVATE, force = true)
@Builder(builderMethodName = "init", setterPrefix = "set", toBuilder = true)
public class TaskDto {

    private final Long id;

    private final String name;

    private final Long expeditionId;

    private final List<MissionDto> missions;
}
