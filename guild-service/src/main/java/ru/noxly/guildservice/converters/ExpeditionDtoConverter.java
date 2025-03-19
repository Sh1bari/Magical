package ru.noxly.guildservice.converters;

import lombok.RequiredArgsConstructor;
import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;
import ru.noxly.guildservice.model.entity.Expedition;
import ru.noxly.guildservice.model.model.dto.ExpeditionDto;

import static ru.noxly.guildservice.utils.CommonUtils.nullOrApply;

@Component
@RequiredArgsConstructor
public class ExpeditionDtoConverter implements Converter<Expedition, ExpeditionDto> {

    private final TaskDtoConverter taskDtoConverter;
    private final TeamDtoConverter teamDtoConverter;

    @Override
    public ExpeditionDto convert(Expedition source) {
        return ExpeditionDto.init()
                .setSentTime(source.getSentTime())
                .setTasks(
                        nullOrApply(
                                source.getTasks(), tasks -> tasks.stream()
                                        .map(taskDtoConverter::convert)
                                        .toList()
                        )
                )
                .setCreateTime(source.getCreateTime())
                .setTeam(
                        nullOrApply(
                                source.getTeam(), team -> teamDtoConverter.convert(source.getTeam()))
                )
                .setExpeditionStatus(source.getExpeditionStatus())
                .setResultTime(source.getResultTime())
                .setName(source.getName())
                .setId(source.getId())
                .build();
    }
}