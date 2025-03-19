package ru.noxly.guildservice.service;

import lombok.RequiredArgsConstructor;
import lombok.val;
import org.springframework.stereotype.Service;
import ru.noxly.guildservice.model.entity.Mission;
import ru.noxly.guildservice.model.model.request.CreateMissionRequest;
import ru.noxly.resolver.RepoResolver;

@Service
@RequiredArgsConstructor
public class MissionService {

    private final RepoResolver resolver;

    public Mission create(CreateMissionRequest request) {
        val mission = Mission.init()
                .setName(request.getName())
                .setMissionType(request.getType())
                .setLevel(request.getLevel())
                .setCost(request.getCost())
                .build();
        resolver.resolve(Mission.class).save(mission);

        return mission;
    }

    public Mission findById(String id) {
        val mission = resolver.resolve(Mission.class).findById(id);

        return mission;
    }
}
