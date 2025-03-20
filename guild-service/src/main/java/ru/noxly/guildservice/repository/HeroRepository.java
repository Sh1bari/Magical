package ru.noxly.guildservice.repository;

import ru.noxly.guildservice.model.entity.Hero;
import ru.noxly.guildservice.model.entity.Mission;
import ru.noxly.resolver.BaseJpaRepository;
import ru.noxly.resolver.Resolve;

import java.util.Collection;
import java.util.List;

@Resolve
public interface HeroRepository extends BaseJpaRepository<Hero, Long> {

    List<Hero> getHeroesByIdIn(Collection<Long> id);
}
