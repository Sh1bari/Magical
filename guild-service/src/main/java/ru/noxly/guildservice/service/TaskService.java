package ru.noxly.guildservice.service;

import lombok.*;
import org.springframework.stereotype.Service;
import ru.noxly.guildservice.model.entity.Expedition;
import ru.noxly.guildservice.model.entity.Task;
import ru.noxly.guildservice.model.entity.TaskMission;
import ru.noxly.guildservice.model.model.request.CreateTaskRequest;
import ru.noxly.resolver.RepoResolver;

@Service
@RequiredArgsConstructor
public class TaskService {

    private final RepoResolver resolver;

    public Task create(CreateTaskRequest request) {
        val task = Task.init()
                .setName(request.getName())
                .setExpedition(resolver.resolve(Expedition.class).findById(request.getExpeditionId()))
                .build();
        resolver.resolve(Task.class).save(task);
        val missions = resolver.getMissionRepository().getMissionsByIdIn(request.getMissionIds());
        val taskMissions = missions.stream()
                .map(o -> TaskMission.init()
                        .setMission(o)
                        .setTask(task)
                        .build()).toList();
        resolver.resolve(TaskMission.class).saveAll(taskMissions);
        val entity = task.toBuilder().setTaskMissions(taskMissions).build();
        resolver.resolve(Task.class).save(entity);

        return entity;
    }

    public Task findById(String id) {
        val task = resolver.resolve(Task.class).findById(id);

        return task;
    }
}
