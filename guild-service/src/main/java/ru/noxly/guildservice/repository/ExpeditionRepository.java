package ru.noxly.guildservice.repository;

import ru.noxly.guildservice.model.entity.Expedition;
import ru.noxly.resolver.BaseJpaRepository;
import ru.noxly.resolver.Resolve;

@Resolve
public interface ExpeditionRepository extends BaseJpaRepository<Expedition, Long> {
}
