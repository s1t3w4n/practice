package ru.bellintegrator.practice.service.organization;

import ru.bellintegrator.practice.view.global.ResultSuccessView;
import ru.bellintegrator.practice.view.organization.OrganizationViewIn;
import ru.bellintegrator.practice.view.organization.OrganizationViewListIn;
import ru.bellintegrator.practice.view.organization.OrganizationViewListOut;
import ru.bellintegrator.practice.view.organization.OrganizationViewUpdateIn;

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
    OrganizationViewIn getOrganizationById(Long id);

    /**
     * Обновить организацию
     * @param organization данные для обновления
     * @return возвращаемый view успешного сохранения
     */
    ResultSuccessView updateOrganization(OrganizationViewUpdateIn organization);
}
