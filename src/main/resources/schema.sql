CREATE TABLE IF NOT EXISTS Doc (
    code        INTEGER NOT NULL UNIQUE      COMMENT 'Код документа',
    version     INTEGER NOT NULL             COMMENT 'Служебное поле hibernate',
    name        VARCHAR(113) NOT NULL UNIQUE COMMENT 'Название документа'
);
COMMENT ON TABLE Doc IS 'Документ';

CREATE TABLE IF NOT EXISTS Country (
    code        INTEGER NOT NULL UNIQUE      COMMENT 'Код страны',
    version     INTEGER NOT NULL             COMMENT 'Служебное поле hibernate',
    name        VARCHAR(60) NOT NULL UNIQUE  COMMENT 'Название страны'
);
COMMENT ON TABLE Country IS 'Страна';