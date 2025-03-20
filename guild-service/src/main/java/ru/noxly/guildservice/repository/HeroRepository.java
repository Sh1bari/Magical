package ru.noxly.guildservice.repository;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import ru.noxly.guildservice.model.entity.Hero;
import ru.noxly.guildservice.model.entity.Mission;
import ru.noxly.resolver.BaseJpaRepository;
import ru.noxly.resolver.Resolve;

import java.util.Collection;
import java.util.List;

@Resolve
public interface HeroRepository extends BaseJpaRepository<Hero, Long> {

    List<Hero> getHeroesByIdIn(Collection<Long> id);

    @Query(value = "SELECT h.* FROM heroes h " +
            "JOIN team_hero th ON h.id = th.hero_id " +
            "JOIN teams t ON th.team_id = t.id " +
            "WHERE t.id = :teamId",
            nativeQuery = true)
    List<Hero> findHeroesByTeamId(@Param("teamId") Long teamId);
}
