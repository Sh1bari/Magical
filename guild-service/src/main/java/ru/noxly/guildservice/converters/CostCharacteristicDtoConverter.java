package ru.noxly.guildservice.converters;

import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;
import ru.noxly.guildservice.model.entity.embeded.CostCharacteristic;
import ru.noxly.guildservice.model.model.dto.CostCharacteristicDto;

@Component
@RequiredArgsConstructor
public class CostCharacteristicDtoConverter implements Converter<CostCharacteristic, CostCharacteristicDto> {

    @Override
    public CostCharacteristicDto convert(@NonNull final CostCharacteristic source) {
        return CostCharacteristicDto.init()
                .setFight(source.getFight())
                .setMagic(source.getMagic())
                .setStrategy(source.getStrategy())
                .setTotal(source.getTotal())
                .build();
    }
}