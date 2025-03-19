package ru.noxly.guildservice.repository;

import ru.noxly.guildservice.model.entity.Mission;
import ru.noxly.resolver.BaseJpaRepository;
import ru.noxly.resolver.Resolve;

@Resolve
public interface MissionRepository extends BaseJpaRepository<Mission, Long> {
}
