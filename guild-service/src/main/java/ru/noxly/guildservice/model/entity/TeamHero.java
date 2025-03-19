package ru.noxly.guildservice.model.entity;

import jakarta.persistence.*;
import lombok.*;

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

    @ManyToOne
    @JoinColumn(name = "hero_id")
    private final Hero hero;

    @ManyToOne
    @JoinColumn(name = "team_id")
    private final Team team;
}
