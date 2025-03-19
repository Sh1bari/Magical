package ru.noxly.efs.webClient.main.model.dto;

import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.RequiredArgsConstructor;
import lombok.ToString;
import ru.noxly.efs.webClient.main.model.enums.TeamStatus;

import java.time.OffsetDateTime;
import java.util.List;

@Getter
@ToString
@RequiredArgsConstructor
@NoArgsConstructor(access = AccessLevel.PRIVATE, force = true)
@Builder(builderMethodName = "init", setterPrefix = "set", toBuilder = true)
public class TeamDto {

    private final Long id;

    private final TeamStatus status;

    private final OffsetDateTime createTime;

    private final OffsetDateTime sentTime;

    private final OffsetDateTime resultTime;

    private final List<HeroDto> heroes;
}
