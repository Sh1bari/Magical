package ru.noxly.guildservice.model.entity;

import jakarta.persistence.*;
import lombok.*;
import ru.noxly.guildservice.model.enums.TeamStatus;

import java.time.OffsetDateTime;
import java.util.List;

@Entity
@Getter
@ToString
@RequiredArgsConstructor
@NoArgsConstructor(force = true)
@Builder(builderMethodName = "init", setterPrefix = "set", toBuilder = true)
@Table(name = "teams")
public class Team {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private final Long id;

    @Enumerated(EnumType.STRING)
    @Column
    private final TeamStatus status;

    @Basic
    @Column
    private final OffsetDateTime createTime;

    @Basic
    @Column
    private final OffsetDateTime sentTime;

    @Basic
    @Column
    private final OffsetDateTime resultTime;

    @OneToOne(orphanRemoval = true)
    @JoinColumn(name = "expedition_id")
    private final Expedition expedition;

    @OneToMany(mappedBy = "team", orphanRemoval = true)
    private final List<TeamHero> teamHeroes;
}
