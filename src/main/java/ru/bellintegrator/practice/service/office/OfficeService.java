package ru.bellintegrator.practice.service.office;

import ru.bellintegrator.practice.view.office.OfficeViewListIn;
import ru.bellintegrator.practice.view.office.OfficeViewListOut;

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
    List<OfficeViewListOut> getAllOfficeBy(OfficeViewListIn filterIn);
}
