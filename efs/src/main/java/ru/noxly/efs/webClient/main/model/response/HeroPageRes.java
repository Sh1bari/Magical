package ru.noxly.efs.webClient.main.model.response;

import lombok.*;
import ru.noxly.efs.common.PageResponse;
import ru.noxly.efs.webClient.main.model.dto.HeroDto;

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
