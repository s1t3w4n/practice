CREATE TABLE IF NOT EXISTS Doc (
    code        INTEGER      NOT NULL UNIQUE COMMENT 'Код документа',
    version     INTEGER      NOT NULL        COMMENT 'Служебное поле hibernate',
    name        VARCHAR(113) NOT NULL UNIQUE COMMENT 'Название документа'
);
COMMENT ON TABLE Doc IS 'Документ';

CREATE TABLE IF NOT EXISTS Country (
    code        INTEGER     NOT NULL UNIQUE  COMMENT 'Код страны',
    version     INTEGER     NOT NULL         COMMENT 'Служебное поле hibernate',
    name        VARCHAR(60) NOT NULL UNIQUE  COMMENT 'Название страны'
);
COMMENT ON TABLE Country IS 'Страна';

CREATE TABLE IF NOT EXISTS Organization (
    id         INTEGER                               COMMENT 'Уникальный идентификатор' PRIMARY KEY AUTO_INCREMENT,
    version    INTEGER       NOT NULL                COMMENT 'Служебное поле hibernate',
    name       VARCHAR(30)   NOT NULL                COMMENT 'Название организации',
    full_name  VARCHAR(100)  NOT NULL                COMMENT 'Полное название организации',
    inn        VARCHAR(10)   NOT NULL                COMMENT 'ИНН организации',
    kpp        VARCHAR(9)    NOT NULL                COMMENT 'КПП организации',
    address    VARCHAR(100)  NOT NULL                COMMENT 'Адрес организации',
    phone      VARCHAR(11)                           COMMENT 'Телефон организации',
    is_active  BIT                    DEFAULT TRUE   COMMENT 'Статус организации'
);
COMMENT ON TABLE Country IS 'Организация';
CREATE INDEX IX_Organization_id ON Organization (id);