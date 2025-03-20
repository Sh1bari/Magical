package ru.noxly.guildservice.repository;

import ru.noxly.guildservice.model.entity.Team;
import ru.noxly.guildservice.model.entity.TeamHero;
import ru.noxly.resolver.BaseJpaRepository;
import ru.noxly.resolver.Resolve;

import java.util.List;

@Resolve
public interface TeamHeroRepository extends BaseJpaRepository<TeamHero, Long> {

    void deleteAllByTeam(Team team);
}
