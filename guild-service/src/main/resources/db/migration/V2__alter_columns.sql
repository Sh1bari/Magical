ALTER TABLE heroes
    ADD fight INTEGER;

ALTER TABLE heroes
    ADD magic INTEGER;

ALTER TABLE heroes
    ADD strategy INTEGER;

ALTER TABLE missions
    ADD fight INTEGER;

ALTER TABLE missions
    ADD level VARCHAR(255);

ALTER TABLE missions
    ADD magic INTEGER;

ALTER TABLE missions
    ADD strategy INTEGER;

ALTER TABLE missions
    ADD total INTEGER;

ALTER TABLE heroes
    DROP COLUMN characteristic_fight;

ALTER TABLE heroes
    DROP COLUMN characteristic_magic;

ALTER TABLE heroes
    DROP COLUMN characteristic_strategy;

ALTER TABLE missions
    DROP COLUMN cost_fight;

ALTER TABLE missions
    DROP COLUMN cost_magic;

ALTER TABLE missions
    DROP COLUMN cost_strategy;

ALTER TABLE missions
    DROP COLUMN cost_total;

ALTER TABLE missions
    DROP COLUMN level_enum;