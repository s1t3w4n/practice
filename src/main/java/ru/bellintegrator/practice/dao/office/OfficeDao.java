package ru.bellintegrator.practice.dao.office;

import ru.bellintegrator.practice.model.Office;
import ru.bellintegrator.practice.view.office.OfficeViewFilter;

import java.util.List;

/**
 * DAO для работы с офисами
 */
public interface OfficeDao {
    /**
     * Получить список офисов, соответствующих параметрам фильтра
     *
     * @param filter параметры фильтра
     * @return список офисов соответствующих параметрам фильтра
     */
    List<Office> findAllOfficeBy(OfficeViewFilter filter);

    /**
     * Получить офис по уникальному идентификатору
     * @param id уникальный идентификатор офиса
     * @return офис с запрашиваемым уникальным идентификатором
     */
    Office findOfficeById(Long id);

    /**
     * Обновить офис
     * @param office данные для обновления
     * @return возвращаемое значение результата операции
     */
    boolean updateOffice(Office office);

    /**
     * Сохранить офис
     * @param office данные для сохранения
     */
    void saveOffice(Office office);
}
