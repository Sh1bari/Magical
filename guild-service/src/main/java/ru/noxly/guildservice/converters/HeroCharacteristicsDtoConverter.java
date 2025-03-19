package ru.noxly.guildservice.converters;

import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;
import ru.noxly.guildservice.model.entity.embeded.HeroCharacteristic;
import ru.noxly.guildservice.model.model.dto.HeroCharacteristicDto;

@Component
@RequiredArgsConstructor
public class HeroCharacteristicsDtoConverter implements Converter<HeroCharacteristic, HeroCharacteristicDto> {

    @Override
    public HeroCharacteristicDto convert(@NonNull final HeroCharacteristic source) {
        return HeroCharacteristicDto.init()
                .setFight(source.getFight())
                .setMagic(source.getMagic())
                .setStrategy(source.getStrategy())
                .build();
    }
}
