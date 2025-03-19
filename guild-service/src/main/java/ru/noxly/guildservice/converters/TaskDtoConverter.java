package ru.noxly.guildservice.converters;

import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;
import ru.noxly.guildservice.model.entity.Task;
import ru.noxly.guildservice.model.entity.TaskMission;
import ru.noxly.guildservice.model.model.dto.TaskDto;

@Component
@RequiredArgsConstructor
public class TaskDtoConverter implements Converter<Task, TaskDto> {

    private final MissionDtoConverter missionDtoConverter;

    @Override
    public TaskDto convert(@NonNull final Task source) {
        return TaskDto.init()
                .setExpeditionId(source.getExpedition().getId())
                .setId(source.getId())
                .setMissions(source.getTaskMissions()
                        .stream()
                        .map(TaskMission::getMission)
                            .map(missionDtoConverter::convert)
                        .toList())
                .setName(source.getName())
                .build();
    }
}
