package ru.noxly.guildservice.model.entity;

import jakarta.persistence.*;
import lombok.*;
import ru.noxly.guildservice.model.enums.ExpeditionStatus;

import java.time.OffsetDateTime;
import java.util.List;

@Entity
@Getter
@ToString
@RequiredArgsConstructor
@NoArgsConstructor(force = true)
@Builder(builderMethodName = "init", setterPrefix = "set", toBuilder = true)
@Table(name = "expeditions")
public class Expedition {

    @Id
    @Column(name = "id")
    private final Long id;

    @Column
    private final String name;

    @Enumerated(EnumType.STRING)
    @Column
    private final ExpeditionStatus expeditionStatus;

    @Basic
    @Column
    private final OffsetDateTime createTime;

    @Basic
    @Column
    private final OffsetDateTime sentTime;

    @Basic
    @Column
    private final OffsetDateTime resultTime;

    @OneToOne(mappedBy = "expedition", orphanRemoval = true)
    private final Team team;

    @OneToMany(mappedBy = "expedition", orphanRemoval = true)
    private final List<Task> tasks;
}
