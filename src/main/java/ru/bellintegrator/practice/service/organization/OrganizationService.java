package ru.bellintegrator.practice.service.organization;

import ru.bellintegrator.practice.view.global.ResultSuccessView;
import ru.bellintegrator.practice.view.organization.*;

import java.util.List;

/**
 * Сервис для работы с организациями
 */
public interface OrganizationService {
    /**
     * Получить список организаций
     * @param filterIn фильтр
     * @return список организаций соответствующих характеристикам фильтра
     */
    List<OrganizationViewListOut> getAllOrganizationsBy(OrganizationViewListIn filterIn);

    /**
     * Получить организацию по id
     * @param id уникальный идентификатор организации
     * @return возвращаемый view организации
     */
    OrganizationView getOrganizationById(Long id);

    /**
     * Обновить организацию
     * @param organization данные для обновления
     * @return возвращаемый view успешного обновления
     */
    ResultSuccessView updateOrganization(OrganizationViewUpdate organization);

    /**
     * Сохранить организацию
     * @param organization данные для сохранения
     * @return возвращаемый view успешного сохранения
     */
    ResultSuccessView saveOrganization(OrganizationViewSave organization);
}
