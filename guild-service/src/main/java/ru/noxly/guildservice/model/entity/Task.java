package ru.noxly.guildservice.model.entity;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Getter
@ToString
@RequiredArgsConstructor
@NoArgsConstructor(force = true)
@Builder(builderMethodName = "init", setterPrefix = "set", toBuilder = true)
@Table(name = "tasks")
public class Task {

    @Id
    @Column(name = "id")
    private final Long id;

    @Column
    private final String name;

    @ManyToOne
    @JoinColumn(name = "expedition_id")
    private final Expedition expedition;

    @ManyToOne
    @JoinColumn(name = "task_mission_id")
    private final TaskMission taskMission;
}
