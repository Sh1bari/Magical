package ru.noxly.guildservice.repository;

import ru.noxly.guildservice.model.entity.Hero;
import ru.noxly.resolver.BaseJpaRepository;
import ru.noxly.resolver.Resolve;

@Resolve
public interface HeroRepository extends BaseJpaRepository<Hero, Long> {
}
