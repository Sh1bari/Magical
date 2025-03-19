package ru.noxly.guildservice.converters;

import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;
import ru.noxly.guildservice.model.entity.Hero;
import ru.noxly.guildservice.model.model.dto.HeroDto;

@Component
@RequiredArgsConstructor
public class HeroDtoConverter implements Converter<Hero, HeroDto> {

    private final HeroCharacteristicsDtoConverter heroCharacteristicsDtoConverter;

    @Override
    public HeroDto convert(@NonNull final Hero source) {
        return HeroDto.init()
                .setName(source.getName())
                .setCharacteristics(heroCharacteristicsDtoConverter.convert(source.getCharacteristic()))
                .setStatus(source.getStatus())
                .setId(source.getId())
                .setLevel(source.getLevel())
                .setType(source.getType())
                .build();
    }
}
