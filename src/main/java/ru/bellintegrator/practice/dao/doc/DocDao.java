package ru.bellintegrator.practice.dao.doc;

import ru.bellintegrator.practice.model.Doc;

import java.util.List;

/**
 * DAO для работы с Doc
 */
public interface DocDao {

    /**
     * Получить все объекты Doc
     *
     * @return все документы
     */
    List<Doc> getAll();

    /**
     * Получение документа по уникальному идентификатору
     * @param id уникальный идентификатор документа
     * @return документ, который соответствует уникальному идентификатору
     */
    Doc findDocById(Long id);

    /**
     * Получение типа документа по коду
     * @param code код документа
     * @return документ
     */
    Doc findDocByCode(int code);

    /**
     * Получение типа документа по имени
     * @param name имя документа
     * @return документ
     */
    Doc findDocByName(String name);

    /**
     * Добавление документа
     * @param doc сохраняемый документ
     */
    void saveDoc(Doc doc);
}
