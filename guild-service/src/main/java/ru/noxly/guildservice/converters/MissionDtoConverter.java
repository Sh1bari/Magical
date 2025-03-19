package ru.noxly.guildservice.converters;

import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;
import ru.noxly.guildservice.model.entity.Mission;
import ru.noxly.guildservice.model.model.dto.MissionDto;

@Component
@RequiredArgsConstructor
public class MissionDtoConverter implements Converter<Mission, MissionDto> {

    private final CostCharacteristicDtoConverter costCharacteristicDtoConverter;

    @Override
    public MissionDto convert(@NonNull final Mission source) {
        return MissionDto.init()
                .setId(source.getId())
                .setMissionType(source.getMissionType())
                .setCost(costCharacteristicDtoConverter.convert(source.getCost()))
                .setLevel(source.getLevel())
                .setName(source.getName())
                .build();
    }
}
