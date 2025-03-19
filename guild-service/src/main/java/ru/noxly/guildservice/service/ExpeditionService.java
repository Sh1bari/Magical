package ru.noxly.guildservice.service;


import lombok.RequiredArgsConstructor;
import lombok.*;
import org.springframework.stereotype.Service;
import ru.noxly.guildservice.model.entity.Expedition;
import ru.noxly.guildservice.model.model.request.CreateExpeditionRequest;
import ru.noxly.resolver.RepoResolver;

import java.time.OffsetDateTime;

import static ru.noxly.guildservice.model.enums.ExpeditionStatus.CREATED;

@Service
@RequiredArgsConstructor
public class ExpeditionService {

    private final RepoResolver resolver;

    public Expedition create(final CreateExpeditionRequest request) {
        val expedition = Expedition.init()
                .setExpeditionStatus(CREATED)
                .setCreateTime(OffsetDateTime.now())
                .setName(request.getName())
                .build();
        resolver.resolve(Expedition.class).save(expedition);

        return expedition;
    }

    public Expedition findById(String id) {
        val expedition = resolver.resolve(Expedition.class).findById(id);

        return expedition;
    }
}
