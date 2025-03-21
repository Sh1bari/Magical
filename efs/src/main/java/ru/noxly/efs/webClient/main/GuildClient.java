package ru.noxly.efs.webClient.main;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import lombok.val;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.web.util.UriComponentsBuilder;
import ru.noxly.efs.webClient.main.model.dto.ExpeditionDto;
import ru.noxly.efs.webClient.main.model.dto.HeroDto;
import ru.noxly.efs.webClient.main.model.dto.MissionDto;
import ru.noxly.efs.webClient.main.model.dto.TaskDto;
import ru.noxly.efs.webClient.main.model.request.CreateExpeditionRequest;
import ru.noxly.efs.webClient.main.model.request.CreateHeroRequest;
import ru.noxly.efs.webClient.main.model.request.CreateMissionRequest;
import ru.noxly.efs.webClient.main.model.request.CreateTaskRequest;
import ru.noxly.efs.webClient.main.model.request.GetExpeditionReq;
import ru.noxly.efs.webClient.main.model.request.GetHeroReq;
import ru.noxly.efs.webClient.main.model.request.GetMissionReq;
import ru.noxly.efs.webClient.main.model.request.HeroFilter;
import ru.noxly.efs.webClient.main.model.request.StartExpeditionReq;
import ru.noxly.efs.webClient.main.model.response.ExpeditionPageRes;
import ru.noxly.efs.webClient.main.model.response.HeroPageRes;
import ru.noxly.efs.webClient.main.model.response.MissionPageRes;

import java.util.List;

@Slf4j
@Service
@RequiredArgsConstructor
public class GuildClient {

    private final GuildWebClient guildWebClient;

    public TaskDto postTask(final CreateTaskRequest request) {
        val uri = "/api/task";
        val response = guildWebClient.post(
                uri,
                request,
                TaskDto.class
        );

        return response;
    }

    public TaskDto getTaskById(final String id) {
        val uri = "/api/task/{id}";

        val response = guildWebClient.get(
                uri.replace("{id}", id),
                TaskDto.class
        );

        return response;
    }

    public MissionDto postMission(final CreateMissionRequest request) {
        val uri = "/api/mission";
        val response = guildWebClient.post(
                uri,
                request,
                MissionDto.class
        );

        return response;
    }

    public MissionDto getMissionById(final String id) {
        val uri = "/api/mission/{id}";

        val response = guildWebClient.get(
                uri.replace("{id}", id),
                MissionDto.class
        );

        return response;
    }

    public MissionPageRes getMissionWithFilters(GetMissionReq req) {
        val uri = "/api/mission/filters";

        val response = guildWebClient.post(
                uri,
                req,
                MissionPageRes.class);

        return response;
    }

    public ExpeditionDto postExpedition(final CreateExpeditionRequest request) {
        val uri = "/api/expedition";
        val response = guildWebClient.post(
                uri,
                request,
                ExpeditionDto.class
        );

        return response;
    }

    public ExpeditionDto getExpeditionById(final String id) {
        val uri = "/api/expedition/{id}";

        val response = guildWebClient.get(
                uri.replace("{id}", id),
                ExpeditionDto.class
        );

        return response;
    }

    public ExpeditionPageRes getExpeditionWithFilters(final GetExpeditionReq req) {
        val uri = "/api/expedition/filters";

        val response = guildWebClient.post(
                uri,
                req,
                ExpeditionPageRes.class);

        return response;
    }

    public HeroDto postHero(final CreateHeroRequest request) {
        val uri = "/api/hero";
        val response = guildWebClient.post(
                uri,
                request,
                HeroDto.class
        );

        return response;
    }

    public HeroDto getHeroById(final String id) {
        val uri = "/api/hero/{id}";

        val response = guildWebClient.get(
                uri.replace("{id}", id),
                HeroDto.class
        );

        return response;
    }

    public HeroPageRes getHeroesWithFilters(final GetHeroReq req) {
        val uri = "/api/hero/filters";

        val response = guildWebClient.post(
                uri,
                req,
                HeroPageRes.class);

        return response;
    }

    public ExpeditionDto startExpedition(final Long id, final List<Long> team) {
        val uri = "/api/expedition/" + id + "/start";
        val request = StartExpeditionReq.init().setTeam(team).build();

        val response =  guildWebClient.put(
                uri.replace("{id}", id.toString()),
                request,
                ExpeditionDto.class
        );

        return response;
    }

    public String calculateExpedition(final Long id) {
        val uri = "/api/expedition/{id}/calculate";

        val response = guildWebClient.get(
                uri.replace("{id}", id.toString()),
                String.class);

        return response;
    }
}
