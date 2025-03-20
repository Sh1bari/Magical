package ru.noxly.guildservice.model.model.response;

import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.RequiredArgsConstructor;
import lombok.ToString;
import ru.noxly.guildservice.common.PageResponse;
import ru.noxly.guildservice.model.model.dto.ExpeditionDto;

@Getter
@ToString
@RequiredArgsConstructor
@NoArgsConstructor(access = AccessLevel.PRIVATE, force = true)
@Builder(builderMethodName = "init", setterPrefix = "set", toBuilder = true)
public class ExpeditionPageRes {

    private final PageResponse<ExpeditionDto> expeditions;

    public static ExpeditionPageRes fromPage(org.springframework.data.domain.Page<ExpeditionDto> page) {
        return new ExpeditionPageRes(PageResponse.fromPage(page));
    }
}
