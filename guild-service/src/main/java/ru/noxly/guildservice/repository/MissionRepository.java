package ru.noxly.guildservice.repository;

import ru.noxly.guildservice.model.entity.Mission;
import ru.noxly.resolver.BaseJpaRepository;
import ru.noxly.resolver.Resolve;

import java.util.Collection;
import java.util.List;

@Resolve
public interface MissionRepository extends BaseJpaRepository<Mission, Long> {

    List<Mission> getMissionsByIdIn(Collection<Long> id);
}
