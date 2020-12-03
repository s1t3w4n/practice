package ru.bellintegrator.practice.service.office;

import ru.bellintegrator.practice.view.global.ResultSuccessView;
import ru.bellintegrator.practice.view.office.OfficeView;
import ru.bellintegrator.practice.view.office.OfficeViewFilter;
import ru.bellintegrator.practice.view.office.OfficeViewList;
import ru.bellintegrator.practice.view.office.OfficeViewSave;
import ru.bellintegrator.practice.view.office.OfficeViewUpdate;

import java.util.List;

/**
 * Сервис для работы с офисами
 */
public interface OfficeService {
    /**
     * Получить список офисов
     * @param filter фильтр
     * @return список офисов соответствующих характеристикам фильтра
     */
    List<OfficeViewList> getAllOfficeBy(OfficeViewFilter filter);

    /**
     * Получить офис по id
     * @param id уникальный идентификатор офиса
     * @return возвращаемый view офиса
     */
    OfficeView getOfficeById(Long id);

    /**
     * Обновить офис
     * @param office данные для обновления
     * @return возвращаемый view успешного обновления
     */
    ResultSuccessView updateOffice(OfficeViewUpdate office);

    /**
     * Сохранить офис
     * @param office данные для сохранения
     * @return возвращаемый view успешного сохранения
     */
    ResultSuccessView saveOffice(OfficeViewSave office);
}
