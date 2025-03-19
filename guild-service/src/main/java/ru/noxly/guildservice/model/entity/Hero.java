package ru.noxly.guildservice.model.entity;

import jakarta.persistence.*;
import lombok.*;
import ru.noxly.guildservice.model.enums.HeroStatus;
import ru.noxly.guildservice.model.enums.HeroType;
import ru.noxly.guildservice.model.enums.LevelEnum;

@Entity
@Getter
@ToString
@RequiredArgsConstructor
@NoArgsConstructor(force = true)
@Builder(builderMethodName = "init", setterPrefix = "set", toBuilder = true)
@Table(name = "heroes")
public class Hero {

    @Id
    @Column(name = "id")
    private final Long id;

    @Column
    private final String name;

    @Enumerated(EnumType.STRING)
    @Column
    private final HeroType type;

    @Column
    private final LevelEnum level;

    @Embedded
    private final HeroCharacteristic characteristic;

    @Enumerated(EnumType.STRING)
    @Column
    private final HeroStatus status;

    @ManyToOne
    @JoinColumn(name = "team_hero_id")
    private TeamHero teamHero;
}
