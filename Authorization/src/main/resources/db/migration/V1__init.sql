CREATE TABLE user_info
(
    id          BIGINT GENERATED BY DEFAULT AS IDENTITY NOT NULL,
    user_id     UUID,
    name        VARCHAR(255),
    middle_name VARCHAR(255),
    surname     VARCHAR(255),
    full_name   VARCHAR(255),
    CONSTRAINT pk_userinfo PRIMARY KEY (id)
);

CREATE TABLE user_roles
(
    user_id UUID NOT NULL,
    role    VARCHAR(255)
);

CREATE TABLE users
(
    id            UUID NOT NULL,
    mail          VARCHAR(255),
    username      VARCHAR(255),
    password      VARCHAR(255),
    user_status   VARCHAR(255),
    invite_status VARCHAR(255),
    create_time   TIMESTAMP WITHOUT TIME ZONE,
    CONSTRAINT pk_users PRIMARY KEY (id)
);

ALTER TABLE users
    ADD CONSTRAINT uc_users_mail UNIQUE (mail);

ALTER TABLE users
    ADD CONSTRAINT uc_users_username UNIQUE (username);

ALTER TABLE user_info
    ADD CONSTRAINT FK_USERINFO_ON_USER FOREIGN KEY (user_id) REFERENCES users (id);

ALTER TABLE user_roles
    ADD CONSTRAINT fk_user_roles_on_user FOREIGN KEY (user_id) REFERENCES users (id);