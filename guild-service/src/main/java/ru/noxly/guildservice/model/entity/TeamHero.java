package ru.noxly.guildservice.model.entity;

import jakarta.persistence.*;
import lombok.*;

import java.util.List;

@Entity
@Getter
@ToString
@RequiredArgsConstructor
@NoArgsConstructor(force = true)
@Builder(builderMethodName = "init", setterPrefix = "set", toBuilder = true)
@Table(name = "team_hero")
public class TeamHero {

    @Id
    @Column(name = "id")
    private final Long id;

    @OneToMany(mappedBy = "teamHero", orphanRemoval = true)
    private final List<Hero> heroes;

    @OneToMany(mappedBy = "teamHero", orphanRemoval = true)
    private final List<Team> teams;
}
