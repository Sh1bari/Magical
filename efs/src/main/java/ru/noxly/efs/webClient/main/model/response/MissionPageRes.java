package ru.noxly.efs.webClient.main.model.response;

import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.RequiredArgsConstructor;
import lombok.ToString;
import ru.noxly.efs.common.PageResponse;
import ru.noxly.efs.webClient.main.model.dto.MissionDto;

@Getter
@ToString
@RequiredArgsConstructor
@NoArgsConstructor(access = AccessLevel.PRIVATE, force = true)
@Builder(builderMethodName = "init", setterPrefix = "set", toBuilder = true)
public class MissionPageRes {

    private final PageResponse<MissionDto> heroes;

    public static MissionPageRes fromPage(org.springframework.data.domain.Page<MissionDto> page) {
        return new MissionPageRes(PageResponse.fromPage(page));
    }
}
