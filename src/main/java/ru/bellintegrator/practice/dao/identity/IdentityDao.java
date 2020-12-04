package ru.bellintegrator.practice.dao.identity;

import ru.bellintegrator.practice.model.Identity;

/**
 * DAO для работы с персональными документами
 */
public interface IdentityDao {
    /**
     * Получить персональный документ по уникальному идентификатору
     * @param id уникальный идентификатор персонального документа
     * @return персональный документ с запрашиваемым уникальным идентификатором
     */
    Identity findIdentityById(Long id);

    /**
     * Сохранить персональный документ
     * @param identity данные для сохранения
     */
    void saveIdentity(Identity identity);
}
