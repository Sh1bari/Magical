package ru.noxly.guildservice.model.entity;

import jakarta.persistence.*;
import lombok.*;
import ru.noxly.guildservice.model.entity.embeded.CostCharacteristic;
import ru.noxly.guildservice.model.enums.LevelEnum;
import ru.noxly.guildservice.model.enums.MissionType;

import java.util.List;

@Entity
@Getter
@ToString
@RequiredArgsConstructor
@NoArgsConstructor(force = true)
@Builder(builderMethodName = "init", setterPrefix = "set", toBuilder = true)
@Table(name = "missions")
public class Mission {

    @Id
    @Column(name = "id")
    private final Long id;

    @Column
    private final String name;

    @Enumerated(EnumType.STRING)
    @Column
    private final MissionType missionType;

    @Enumerated(EnumType.STRING)
    @Column
    private final LevelEnum levelEnum;

    @Embedded
    private final CostCharacteristic characteristic;

    @OneToMany(mappedBy = "mission", orphanRemoval = true)
    private final List<TaskMission> taskMissions;
}
