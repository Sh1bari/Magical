package ru.noxly.guildservice.kafka;

import jakarta.annotation.PostConstruct;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import lombok.val;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.core.convert.ConversionService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.noxly.guildservice.kafka.model.ExpeditionKafkaDto;
import ru.noxly.guildservice.kafka.model.KafkaInModelDto;
import ru.noxly.guildservice.kafka.out.KafkaProducerService;
import ru.noxly.guildservice.model.entity.*;
import ru.noxly.guildservice.model.enums.TeamStatus;
import ru.noxly.guildservice.model.model.dto.ExpeditionDto;
import ru.noxly.guildservice.redis.ExpeditionPublisher;
import ru.noxly.resolver.RepoResolver;

import java.awt.*;
import java.util.List;
import java.util.Objects;

import static ru.noxly.guildservice.utils.CommonUtils.nullOrApply;
import static ru.noxly.guildservice.utils.JsonUtil.toJson;

@Service
@RequiredArgsConstructor
@Slf4j
@ConditionalOnProperty(name = "kafka.enabled", havingValue = "true")
public class KafkaService {

    @Value("${kafka.topics.out.guild-calc-calculate-team}")
    private String topic;

    private final KafkaProducerService kafkaProducerService;

    private final RepoResolver resolver;

    private final ExpeditionPublisher expeditionPublisher;

    private final ConversionService conversionService;

    @Transactional
    public void sendCalculateRequest(final Long expeditionId) {
        val expedition = resolver.resolve(Expedition.class).findById(expeditionId);
        val model = buildModel(expedition);
        kafkaProducerService.sendMessage(topic, null, model);
    }

    @Transactional
    public void handleTeam(final KafkaInModelDto model) {
        val expedition = resolver.resolve(Expedition.class).findById(model.getExpeditionId());
        val heroes = resolver.getHeroRepository().getHeroesByIdIn(model.getHeroes());
        val team = expedition.getTeam();
        heroes.forEach(hero -> {
            val teamHero = TeamHero.init().setTeam(team).setHero(hero).build();
            resolver.resolve(TeamHero.class).save(teamHero);
        });
        val newTeam = team.toBuilder().setStatus(TeamStatus.PREPARED).build();
        resolver.resolve(Team.class).save(newTeam);
        val entity = resolver.resolve(Expedition.class).findById(model.getExpeditionId());
        val dto = conversionService.convert(entity, ExpeditionDto.class);
        expeditionPublisher.publishUpdate(dto);
    }

    private ExpeditionKafkaDto buildModel(Expedition expedition) {
        val missions = extractMissions(expedition);
        return ExpeditionKafkaDto.init()
                .setId(expedition.getId())
                .setName(expedition.getName())
                .setMissions(
                        nullOrApply(
                                missions,
                                m -> m.stream()
                                        .map(this::map)
                                        .toList()
                        )
                )
                .build();
    }

    private ExpeditionKafkaDto.MissionKafkaDto map(Mission mission) {
        return ExpeditionKafkaDto.MissionKafkaDto.init()
                .setId(mission.getId())
                .setName(mission.getName())
                .setType(mission.getMissionType())
                .setLevel(mission.getLevel())
                .setCost(
                        ExpeditionKafkaDto.MissionKafkaDto.CostCharacteristicKafkaDto.init()
                                .setStrategy(mission.getCost().getStrategy())
                                .setFight(mission.getCost().getFight())
                                .setMagic(mission.getCost().getMagic())
                                .setTotal(mission.getCost().getTotal())
                                .build()
                )
                .build();
    }

    private List<Mission> extractMissions(Expedition expedition) {
        return nullOrApply(
                expedition.getTasks(),
                tasks -> tasks.stream()
                        .flatMap(task -> nullOrApply(
                                        task.getTaskMissions(),
                                        taskMissions -> taskMissions.stream().map(TaskMission::getMission)
                                )
                        )
                        .filter(Objects::nonNull)
                        .toList()
        );
    }


}
