package ru.noxly.guildservice.kafka.model;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.*;
import ru.noxly.guildservice.model.enums.LevelEnum;
import ru.noxly.guildservice.model.enums.MissionType;

import java.util.List;

@Getter
@ToString
@RequiredArgsConstructor
@NoArgsConstructor(access = AccessLevel.PRIVATE, force = true)
@Builder(builderMethodName = "init", setterPrefix = "set", toBuilder = true)
public class ExpeditionKafkaDto {

    @JsonProperty("ID")  // Go использует PascalCase
    private final Long id;

    @JsonProperty("Name")
    private final String name;

    @JsonProperty("Missions")
    private final List<MissionKafkaDto> missions;

    @Getter
    @ToString
    @RequiredArgsConstructor
    @NoArgsConstructor(access = AccessLevel.PRIVATE, force = true)
    @Builder(builderMethodName = "init", setterPrefix = "set", toBuilder = true)
    public static class MissionKafkaDto {

        @JsonProperty("ID")
        private final Long id;

        @JsonProperty("Name")
        private final String name;

        @JsonProperty("Type")
        private final MissionType type;

        @JsonProperty("Level")
        private final LevelEnum level;

        @JsonProperty("Cost")  // В Go-структуре это `Cost`, а не `Price`
        private final CostCharacteristicKafkaDto cost;

        @Getter
        @ToString
        @RequiredArgsConstructor
        @NoArgsConstructor(access = AccessLevel.PRIVATE, force = true)
        @Builder(builderMethodName = "init", setterPrefix = "set", toBuilder = true)
        public static class CostCharacteristicKafkaDto {

            @JsonProperty("Fight")
            private final Integer fight;

            @JsonProperty("Strategy")
            private final Integer strategy;

            @JsonProperty("Magic")
            private final Integer magic;

            @JsonProperty("Total")
            private final Integer total;
        }
    }
}
