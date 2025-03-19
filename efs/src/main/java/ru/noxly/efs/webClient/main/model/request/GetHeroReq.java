package ru.noxly.efs.webClient.main.model.request;

import lombok.*;
import ru.noxly.efs.common.PaginationRequest;

@Getter
@ToString
@RequiredArgsConstructor
@NoArgsConstructor(access = AccessLevel.PRIVATE, force = true)
@Builder(builderMethodName = "init", setterPrefix = "set", toBuilder = true)
public class GetHeroReq {

    private final HeroFilter heroFilter;

    private final PaginationRequest paginationRequest;
}
