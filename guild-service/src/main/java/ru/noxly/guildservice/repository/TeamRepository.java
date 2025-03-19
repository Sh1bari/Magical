package ru.noxly.guildservice.repository;

import ru.noxly.guildservice.model.entity.Team;
import ru.noxly.resolver.BaseJpaRepository;
import ru.noxly.resolver.Resolve;

@Resolve
public interface TeamRepository extends BaseJpaRepository<Team, Long> {
}
