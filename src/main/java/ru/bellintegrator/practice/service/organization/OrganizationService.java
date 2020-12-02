package ru.bellintegrator.practice.service.organization;

import ru.bellintegrator.practice.view.organization.OrganizationView;
import ru.bellintegrator.practice.view.organization.OrganizationViewWithFilterIn;
import ru.bellintegrator.practice.view.organization.OrganizationViewWithFilterOut;

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
    List<OrganizationViewWithFilterOut> getAllOrganizationsBy(OrganizationViewWithFilterIn filterIn);

    /**
     * Получить организацию по id
     * @param id уникальный идентификатор организации
     * @return возвращаемый view организации
     */
    OrganizationView getOrganizationById(Long id);
}
