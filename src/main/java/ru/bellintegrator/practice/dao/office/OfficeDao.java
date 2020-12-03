package ru.bellintegrator.practice.dao.office;

import ru.bellintegrator.practice.model.Office;
import ru.bellintegrator.practice.view.office.OfficeViewListIn;

import java.util.List;

/**
 * DAO для работы с Офисом
 */
public interface OfficeDao {
    /**
     * Получить список офисов, соответствующих параметрам фильтра
     *
     * @param filterIn параметры фильтра
     * @return список офисов соответствующих параметрам фильтра
     */
    List<Office> findAllOfficeBy(OfficeViewListIn filterIn);

    /**
     * Получить офис по уникальному идентификатору
     * @param id уникальный идентификатор офиса
     * @return офис с запрашиваемым уникальным идентификатором
     */
    Office findOfficeById(Long id);
}
