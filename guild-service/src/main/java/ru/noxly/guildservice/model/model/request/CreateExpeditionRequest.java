package ru.noxly.guildservice.model.model.request;

import lombok.*;

@Getter
@ToString
@RequiredArgsConstructor
@NoArgsConstructor(access = AccessLevel.PRIVATE, force = true)
@Builder(builderMethodName = "init", setterPrefix = "set", toBuilder = true)
public class CreateExpeditionRequest {

    private final String name;
}