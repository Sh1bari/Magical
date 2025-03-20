package ru.noxly.guildservice.model.model.request;

import lombok.*;
import ru.noxly.guildservice.common.PaginationRequest;

@Getter
@ToString
@RequiredArgsConstructor
@NoArgsConstructor(access = AccessLevel.PRIVATE, force = true)
@Builder(builderMethodName = "init", setterPrefix = "set", toBuilder = true)
public class GetMissionReq {

    private final String name;

    private final PaginationRequest paginationRequest;
}
