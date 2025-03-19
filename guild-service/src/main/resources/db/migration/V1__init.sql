CREATE TABLE expeditions
(
    id                BIGINT NOT NULL,
    name              VARCHAR,
    expedition_status VARCHAR(255),
    create_time       TIMESTAMP WITHOUT TIME ZONE,
    sent_time         TIMESTAMP WITHOUT TIME ZONE,
    result_time       TIMESTAMP WITHOUT TIME ZONE,
    CONSTRAINT pk_expeditions PRIMARY KEY (id)
);

CREATE TABLE heroes
(
    id       BIGINT NOT NULL,
    name     VARCHAR,
    type     VARCHAR(255),
    level    VARCHAR(255),
    status   VARCHAR(255),
    fight    INTEGER,
    strategy INTEGER,
    magic    INTEGER,
    CONSTRAINT pk_heroes PRIMARY KEY (id)
);

CREATE TABLE missions
(
    id           BIGINT NOT NULL,
    name         VARCHAR,
    mission_type VARCHAR(255),
    level        VARCHAR(255),
    fight        INTEGER,
    strategy     INTEGER,
    magic        INTEGER,
    total        INTEGER,
    CONSTRAINT pk_missions PRIMARY KEY (id)
);

CREATE TABLE task_mission
(
    id         BIGINT NOT NULL,
    mission_id BIGINT,
    task_id    BIGINT,
    CONSTRAINT pk_task_mission PRIMARY KEY (id)
);

CREATE TABLE tasks
(
    id            BIGINT NOT NULL,
    name          VARCHAR,
    expedition_id BIGINT,
    CONSTRAINT pk_tasks PRIMARY KEY (id)
);

CREATE TABLE team_hero
(
    id      BIGINT NOT NULL,
    hero_id BIGINT,
    team_id BIGINT,
    CONSTRAINT pk_team_hero PRIMARY KEY (id)
);

CREATE TABLE teams
(
    id            BIGINT NOT NULL,
    status        VARCHAR(255),
    create_time   TIMESTAMP WITHOUT TIME ZONE,
    sent_time     TIMESTAMP WITHOUT TIME ZONE,
    result_time   TIMESTAMP WITHOUT TIME ZONE,
    expedition_id BIGINT,
    CONSTRAINT pk_teams PRIMARY KEY (id)
);

ALTER TABLE tasks
    ADD CONSTRAINT FK_TASKS_ON_EXPEDITION FOREIGN KEY (expedition_id) REFERENCES expeditions (id);

ALTER TABLE task_mission
    ADD CONSTRAINT FK_TASK_MISSION_ON_MISSION FOREIGN KEY (mission_id) REFERENCES missions (id);

ALTER TABLE task_mission
    ADD CONSTRAINT FK_TASK_MISSION_ON_TASK FOREIGN KEY (task_id) REFERENCES tasks (id);

ALTER TABLE teams
    ADD CONSTRAINT FK_TEAMS_ON_EXPEDITION FOREIGN KEY (expedition_id) REFERENCES expeditions (id);

ALTER TABLE team_hero
    ADD CONSTRAINT FK_TEAM_HERO_ON_HERO FOREIGN KEY (hero_id) REFERENCES heroes (id);

ALTER TABLE team_hero
    ADD CONSTRAINT FK_TEAM_HERO_ON_TEAM FOREIGN KEY (team_id) REFERENCES teams (id);