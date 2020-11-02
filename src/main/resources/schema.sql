CREATE TABLE IF NOT EXISTS Doc (
    code        INTEGER      NOT NULL UNIQUE          COMMENT 'Код документа',
    version     INTEGER      NOT NULL                 COMMENT 'Служебное поле hibernate',
    name        VARCHAR(113) NOT NULL UNIQUE          COMMENT 'Название документа'
);
COMMENT ON TABLE Doc IS 'Документ';

CREATE TABLE IF NOT EXISTS Country (
    code        INTEGER      NOT NULL UNIQUE          COMMENT 'Код страны',
    version     INTEGER      NOT NULL                 COMMENT 'Служебное поле hibernate',
    name        VARCHAR(60)  NOT NULL UNIQUE          COMMENT 'Название страны'
);
COMMENT ON TABLE Country IS 'Страна';

CREATE TABLE IF NOT EXISTS Organization (
    id          INTEGER                               COMMENT 'Уникальный идентификатор' PRIMARY KEY AUTO_INCREMENT,
    version     INTEGER       NOT NULL                COMMENT 'Служебное поле hibernate',
    name        VARCHAR(30)   NOT NULL                COMMENT 'Название организации',
    full_name   VARCHAR(100)  NOT NULL                COMMENT 'Полное название организации',
    inn         VARCHAR(10)   NOT NULL UNIQUE         COMMENT 'ИНН организации',
    kpp         VARCHAR(9)    NOT NULL                COMMENT 'КПП организации',
    address     VARCHAR(255)  NOT NULL                COMMENT 'Адрес организации',
    phone       VARCHAR(11)                           COMMENT 'Телефон организации',
    is_active   BIT                    DEFAULT TRUE   COMMENT 'Статус организации'
);
COMMENT ON TABLE Organization IS 'Организация';

CREATE TABLE IF NOT EXISTS Office (
    id          INTEGER                               COMMENT 'Уникальный идентификатор' PRIMARY KEY AUTO_INCREMENT,
    version     INTEGER       NOT NULL                COMMENT 'Служебное поле hibernate',
    org_id      INTEGER       NOT NULL                COMMENT 'Уникальный идентификатор организации',
    name        VARCHAR(30)   NOT NULL                COMMENT 'Название офиса',
    address     VARCHAR(255)  NOT NULL                COMMENT 'Адрес офиса',
    phone       VARCHAR(11)                           COMMENT 'Телефон офиса',
    is_active   BIT                    DEFAULT TRUE   COMMENT 'Статус офиса',

    FOREIGN KEY (org_id) REFERENCES Organization (id)
);
COMMENT ON TABLE Office IS 'Офис';
CREATE INDEX IX_Office_Org_Id ON Office (org_id);

CREATE TABLE IF NOT EXISTS User (
    id                  INTEGER                               COMMENT 'Уникальный идентификатор' PRIMARY KEY AUTO_INCREMENT,
    version             INTEGER       NOT NULL                COMMENT 'Служебное поле hibernate',
    office_id           INTEGER       NOT NULL                COMMENT 'Уникальный идентификатор офиса',
    first_name          VARCHAR(30)   NOT NULL                COMMENT 'Имя пользователя',
    second_name         VARCHAR(30)                           COMMENT 'Фамилия пользователя',
    middle_name         VARCHAR(30)                           COMMENT 'Отчество пользователя',
    position            VARCHAR(30)   NOT NULL                COMMENT 'Должность пользователя',
    phone               VARCHAR(11)                           COMMENT 'Телефон пользователя',
    citizenship_code    INTEGER                               COMMENT 'Код гражданства',
    is_identified       BIT                    DEFAULT FALSE  COMMENT 'Статус идентификации',

    FOREIGN KEY (office_id)        REFERENCES Office (id),
    FOREIGN KEY (citizenship_code) REFERENCES Country (code)
);
COMMENT ON TABLE User IS 'Пользователь';
CREATE INDEX IX_User_Office_Id        ON User (office_id);
CREATE INDEX IX_User_Citizenship_Code ON User (citizenship_code);

CREATE TABLE IF NOT EXISTS Identity (
    id                  INTEGER                               COMMENT 'Уникальный идентификатор' PRIMARY KEY AUTO_INCREMENT,
    version             INTEGER       NOT NULL                COMMENT 'Служебное поле hibernate',
    doc_code            INTEGER                               COMMENT 'Код документа',
    doc_number          VARCHAR(20)                           COMMENT 'Номер документа пользователя',
    doc_date            DATE                                  COMMENT 'Дата регистрации документа пользователя',

    FOREIGN KEY (doc_code) REFERENCES Doc (code)
);
COMMENT ON TABLE Identity IS 'Персональный документ';
CREATE INDEX IX_Identity_Doc_Code      ON Identity (doc_code);

CREATE TABLE IF NOT EXISTS User_Identity (
    user_id        INTEGER  NOT NULL COMMENT 'Уникальный идентификатор пользователя',
    identity_id    INTEGER  NOT NULL COMMENT 'Уникальный идентификатор персонального документа',

    PRIMARY KEY (user_id, identity_id)
);
COMMENT ON TABLE User_Identity IS 'join-таблица для связи пользователя и персонального документа';

CREATE INDEX IX_User_Identity_Id ON User_Identity (identity_id);
ALTER TABLE User_Identity ADD FOREIGN KEY (identity_id) REFERENCES Identity(id);

CREATE INDEX IX_Identity_User_Id ON User_Identity (user_id);
ALTER TABLE User_Identity ADD FOREIGN KEY (user_id) REFERENCES USER (id);

