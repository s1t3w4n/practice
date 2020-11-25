package ru.bellintegrator.practice.service.doc;

import ru.bellintegrator.practice.view.doc.DocView;

import java.util.List;


/**
 * Сервис для работы с документами
 */
public interface DocService {
    /**
     * Получить список документов
     *
     * @return список документов
     */
    List<DocView> getAllDoc();
}
