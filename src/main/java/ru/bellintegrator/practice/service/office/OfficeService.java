package ru.bellintegrator.practice.service.office;

import ru.bellintegrator.practice.view.global.ResultSuccessView;
import ru.bellintegrator.practice.view.office.*;

import java.util.List;

/**
 * Сервис для работы с офисами
 */
public interface OfficeService {
    /**
     * Получить список офисов
     * @param filterIn фильтр
     * @return список офисов соответствующих характеристикам фильтра
     */
    List<OfficeViewList> getAllOfficeBy(OfficeViewFilter filterIn);

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
