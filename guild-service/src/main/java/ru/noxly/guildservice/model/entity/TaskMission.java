package ru.noxly.guildservice.model.entity;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Getter
@ToString
@RequiredArgsConstructor
@NoArgsConstructor(force = true)
@Builder(builderMethodName = "init", setterPrefix = "set", toBuilder = true)
@Table(name = "task_mission")
public class TaskMission {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column
    private final Long id;

    @ManyToOne
    @JoinColumn(name = "mission_id")
    private final Mission mission;

    @ManyToOne
    @JoinColumn(name = "task_id")
    private final Task task;
}
