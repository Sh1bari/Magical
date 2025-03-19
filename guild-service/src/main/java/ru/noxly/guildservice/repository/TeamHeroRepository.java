package ru.noxly.guildservice.repository;

import ru.noxly.guildservice.model.entity.TeamHero;
import ru.noxly.resolver.BaseJpaRepository;
import ru.noxly.resolver.Resolve;

@Resolve
public interface TeamHeroRepository extends BaseJpaRepository<TeamHero, Long> {
}
