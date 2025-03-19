package ru.noxly.guildservice.repository;

import ru.noxly.guildservice.model.entity.TaskMission;
import ru.noxly.resolver.BaseJpaRepository;
import ru.noxly.resolver.Resolve;

@Resolve
public interface TaskMissionRepository extends BaseJpaRepository<TaskMission, Long> {
}
