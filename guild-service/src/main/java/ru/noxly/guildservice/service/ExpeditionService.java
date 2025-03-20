package ru.noxly.guildservice.service;


import lombok.RequiredArgsConstructor;
import lombok.val;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.noxly.guildservice.exceptions.GeneralException;
import ru.noxly.guildservice.exceptions.GlobalAppException;
import ru.noxly.guildservice.kafka.KafkaService;
import ru.noxly.guildservice.model.entity.Expedition;
import ru.noxly.guildservice.model.entity.Hero;
import ru.noxly.guildservice.model.entity.Task;
import ru.noxly.guildservice.model.entity.TaskMission;
import ru.noxly.guildservice.model.entity.Team;
import ru.noxly.guildservice.model.entity.TeamHero;
import ru.noxly.guildservice.model.enums.HeroStatus;
import ru.noxly.guildservice.model.enums.TeamStatus;
import ru.noxly.guildservice.model.model.request.CreateExpeditionRequest;
import ru.noxly.resolver.RepoResolver;

import java.time.OffsetDateTime;
import java.util.List;
import java.util.zip.DataFormatException;

import static ru.noxly.guildservice.model.enums.ExpeditionStatus.CREATED;
import static ru.noxly.guildservice.model.enums.ExpeditionStatus.IN_EXPEDITION;

@Service
@RequiredArgsConstructor
public class ExpeditionService {

    private final RepoResolver resolver;
    private final KafkaService kafkaService;

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

    public Expedition startExpedition(Long id, List<Long> team) {
        val sentTime = OffsetDateTime.now();
        val expedition = resolver.resolve(Expedition.class).findById(id);
        val entity = expedition.toBuilder()
                .setExpeditionStatus(IN_EXPEDITION)
                .setSentTime(sentTime)
                .build();
        resolver.resolve(Expedition.class).save(entity);

        val teamExp = expedition.getTeam().toBuilder()
                .setStatus(TeamStatus.IN_EXPEDITION)
                .setSentTime(sentTime)
                .build();
        resolver.resolve(Team.class).save(teamExp);
        if (teamExp.getTeamHeroes().isEmpty() && team.isEmpty()) {
            throw new GeneralException(400, "Empty team cannot start expedition");
        }
        if (!team.isEmpty()) {
            resolver.getTeamHeroRepository().deleteAllByTeam(teamExp);
            val heroes = resolver.getHeroRepository().getHeroesByIdIn(team)
                    .stream()
                    .map(o -> o.toBuilder()
                            .setStatus(HeroStatus.IN_EXPEDITION)
                            .build()).toList();
            resolver.resolve(Hero.class).saveAll(heroes);
            val teamHeroes = heroes
                    .stream()
                    .map(o -> TeamHero.init()
                            .setHero(o)
                            .setTeam(teamExp)
                            .build()
                    ).toList();
            resolver.resolve(TeamHero.class).saveAll(teamHeroes);
        } else {
            val heroes = teamExp
                    .getTeamHeroes()
                    .stream()
                    .map(TeamHero::getHero)
                    .map(o -> o.toBuilder()
                            .setStatus(HeroStatus.IN_EXPEDITION)
                            .build()).toList();
            resolver.resolve(Hero.class).saveAll(heroes);
        }

        return entity;
    }

    public void calculateExpedition(Long id) {
        kafkaService.sendCalculateRequest(id);
    }
}
