package ru.noxly.guildservice.service;

import lombok.*;
import org.springframework.stereotype.Service;
import ru.noxly.guildservice.model.entity.Hero;
import ru.noxly.guildservice.model.model.request.CreateHeroRequest;
import ru.noxly.resolver.RepoResolver;

import static ru.noxly.guildservice.model.enums.HeroStatus.FREE;

@Service
@RequiredArgsConstructor
public class HeroService {

    private final RepoResolver resolver;

    public Hero create(CreateHeroRequest request) {
        val hero = Hero.init()
                .setName(request.getName())
                .setType(request.getType())
                .setCharacteristic(request.getHeroCharacteristic())
                .setLevel(request.getLevel())
                .setStatus(FREE)
                .build();
        resolver.resolve(Hero.class).save(hero);

        return hero;
    }

    public Hero findById(String id) {
        val hero = resolver.resolve(Hero.class).findById(id);

        return hero;
    }
}
