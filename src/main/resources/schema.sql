CREATE TABLE IF NOT EXISTS Doc (
    id          INTEGER                               COMMENT 'Уникальный идентификатор' PRIMARY KEY AUTO_INCREMENT,
    version     INTEGER      NOT NULL                 COMMENT 'Служебное поле hibernate',
    code        INTEGER      NOT NULL UNIQUE          COMMENT 'Код документа',
    name        VARCHAR(256) NOT NULL UNIQUE          COMMENT 'Название документа'
);
COMMENT ON TABLE Doc IS 'Документ';
CREATE INDEX IX_Doc_Code       ON Doc (code);

CREATE TABLE IF NOT EXISTS Country (
    id          INTEGER                               COMMENT 'Уникальный идентификатор' PRIMARY KEY AUTO_INCREMENT,
    version     INTEGER      NOT NULL                 COMMENT 'Служебное поле hibernate',
    code        INTEGER      NOT NULL UNIQUE          COMMENT 'Код страны',
    name        VARCHAR(128)  NOT NULL UNIQUE          COMMENT 'Название страны'
);
COMMENT ON TABLE Country IS 'Страна';
CREATE INDEX IX_Country_Code   ON Country (code);

CREATE TABLE IF NOT EXISTS Organization (
    id          INTEGER                               COMMENT 'Уникальный идентификатор' PRIMARY KEY AUTO_INCREMENT,
    version     INTEGER       NOT NULL                COMMENT 'Служебное поле hibernate',
    name        VARCHAR(64)   NOT NULL                COMMENT 'Название организации',
    full_name   VARCHAR(128)  NOT NULL                COMMENT 'Полное название организации',
    inn         VARCHAR(10)   NOT NULL UNIQUE         COMMENT 'ИНН организации',
    kpp         VARCHAR(9)    NOT NULL                COMMENT 'КПП организации',
    address     VARCHAR(255)  NOT NULL                COMMENT 'Адрес организации',
    phone       VARCHAR(11)                           COMMENT 'Телефон организации',
    is_active   BIT                    DEFAULT TRUE   COMMENT 'Статус организации'
);
COMMENT ON TABLE Organization IS 'Организация';
CREATE INDEX IX_Organization_Name       ON Organization (name);
CREATE INDEX IX_Organization_inn        ON Organization (inn);
CREATE INDEX IX_Organization_is_active  ON Organization (is_active);

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
CREATE INDEX IX_Office_Org_Id    ON Office (org_id);
CREATE INDEX IX_Office_Name      ON Office (name);
CREATE INDEX IX_Office_Phone     ON Office (phone);
CREATE INDEX IX_Office_Is_Active ON Office (is_active);

CREATE TABLE IF NOT EXISTS User (
    id                  INTEGER                               COMMENT 'Уникальный идентификатор' PRIMARY KEY AUTO_INCREMENT,
    version             INTEGER       NOT NULL                COMMENT 'Служебное поле hibernate',
    office_id           INTEGER       NOT NULL                COMMENT 'Уникальный идентификатор офиса',
    first_name          VARCHAR(30)   NOT NULL                COMMENT 'Имя пользователя',
    second_name         VARCHAR(30)                           COMMENT 'Фамилия пользователя',
    middle_name         VARCHAR(30)                           COMMENT 'Отчество пользователя',
    position            VARCHAR(30)   NOT NULL                COMMENT 'Должность пользователя',
    phone               VARCHAR(11)                           COMMENT 'Телефон пользователя',
    citizenship_id      INTEGER                               COMMENT 'Уникальный идентификатор страны',
    is_identified       BIT                    DEFAULT FALSE  COMMENT 'Статус идентификации',

    FOREIGN KEY (office_id)         REFERENCES Office (id),
    FOREIGN KEY (citizenship_id)    REFERENCES Country (id)
);
COMMENT ON TABLE User IS 'Пользователь';
CREATE INDEX IX_User_Office_Id        ON User (office_id);
CREATE INDEX IX_User_Citizenship_Id   ON User (citizenship_id);
CREATE INDEX IX_User_First_Name       ON User (first_name);
CREATE INDEX IX_User_Second_Name      ON User (second_name);
CREATE INDEX IX_User_Middle_Name      ON User (middle_name);
CREATE INDEX IX_User_Position         ON User (position);

CREATE TABLE IF NOT EXISTS Identity (
    user_id             INTEGER       NOT NULL                COMMENT 'Уникальный идентификатор пользователя' PRIMARY KEY AUTO_INCREMENT,
    version             INTEGER       NOT NULL                COMMENT 'Служебное поле hibernate',
    doc_id              INTEGER       NOT NULL                COMMENT 'Уникальный идентификатор документа',
    doc_number          VARCHAR(20)                           COMMENT 'Номер документа пользователя',
    doc_date            DATE                                  COMMENT 'Дата регистрации документа пользователя',


    FOREIGN KEY (doc_id)          REFERENCES Doc (id),
    FOREIGN KEY (user_id)         REFERENCES User (id)
);
COMMENT ON TABLE Identity IS 'Персональный документ';
CREATE INDEX IX_Identity_Doc_Id      ON Identity (doc_id);
CREATE INDEX IX_Identity_User_Id     ON Identity (user_id);
