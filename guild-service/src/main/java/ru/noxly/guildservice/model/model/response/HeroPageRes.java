package ru.noxly.guildservice.model.model.response;

import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.RequiredArgsConstructor;
import lombok.ToString;
import ru.noxly.guildservice.common.PageResponse;
import ru.noxly.guildservice.model.model.dto.HeroDto;

@Getter
@ToString
@RequiredArgsConstructor
@NoArgsConstructor(access = AccessLevel.PRIVATE, force = true)
@Builder(builderMethodName = "init", setterPrefix = "set", toBuilder = true)
public class HeroPageRes {
    private final PageResponse<HeroDto> lots;

    public static HeroPageRes fromPage(org.springframework.data.domain.Page<HeroDto> page) {
        return new HeroPageRes(PageResponse.fromPage(page));
    }
}
