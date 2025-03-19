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
@Table(name = "tasks")
public class Task {

    @Id
    @Column(name = "id")
    private final Long id;

    @Column
    private final String name;

    @OneToMany(mappedBy = "task", orphanRemoval = true)
    private final List<TaskMission> taskMissions;

    @ManyToOne
    @JoinColumn(name = "expedition_id")
    private final Expedition expedition;
}
