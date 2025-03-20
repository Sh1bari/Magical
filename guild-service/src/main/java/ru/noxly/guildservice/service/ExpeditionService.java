package ru.noxly.guildservice.service;


import lombok.RequiredArgsConstructor;
import lombok.val;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.noxly.guildservice.model.entity.Expedition;
import ru.noxly.guildservice.model.entity.Team;
import ru.noxly.guildservice.model.enums.TeamStatus;
import ru.noxly.guildservice.model.model.request.CreateExpeditionRequest;
import ru.noxly.resolver.RepoResolver;

import java.time.OffsetDateTime;

import static ru.noxly.guildservice.model.enums.ExpeditionStatus.CREATED;

@Service
@RequiredArgsConstructor
public class ExpeditionService {

    private final RepoResolver resolver;

    @Transactional
    public Expedition create(final CreateExpeditionRequest request) {
        val createTime = OffsetDateTime.now();
        val entity = Expedition.init()
                .setExpeditionStatus(CREATED)
                .setCreateTime(createTime)
                .setName(request.getName())
                .build();
        resolver.resolve(Expedition.class).save(entity);
        val team = Team.init()
                .setExpedition(entity)
                .setCreateTime(createTime)
                .setStatus(TeamStatus.CREATED)
                .build();
        resolver.resolve(Team.class).save(team);
        val expedition = entity.toBuilder().setTeam(team).build();
        resolver.resolve(Expedition.class).save(expedition);

        return expedition;
    }

    public Expedition findById(String id) {
        val expedition = resolver.resolve(Expedition.class).findById(id);

        return expedition;
    }

    public Page<Expedition> findByPatternAndPageable(Specification<Expedition> spec, Pageable pageable) {
        return resolver.resolve(Expedition.class).findAll(spec, pageable);
    }
}
