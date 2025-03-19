package ru.noxly.guildservice.repository;

import ru.noxly.guildservice.model.entity.Task;
import ru.noxly.resolver.BaseJpaRepository;
import ru.noxly.resolver.Resolve;

@Resolve
public interface TaskRepository extends BaseJpaRepository<Task, Long> {
}
