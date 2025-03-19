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
@Table(name = "task_mission")
public class TaskMission {

    @Id
    @Column
    private final Long id;

    @OneToMany(mappedBy = "taskMission", orphanRemoval = true)
    private final List<Task> tasks;

    @OneToMany(mappedBy = "taskMission", orphanRemoval = true)
    private final List<Mission> missions;
}
