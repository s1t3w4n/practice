package ru.bellintegrator.practice.service.organization;

import ru.bellintegrator.practice.view.global.ResultSuccessView;
import ru.bellintegrator.practice.view.organization.OrganizationView;
import ru.bellintegrator.practice.view.organization.OrganizationViewFilter;
import ru.bellintegrator.practice.view.organization.OrganizationViewList;
import ru.bellintegrator.practice.view.organization.OrganizationViewSave;
import ru.bellintegrator.practice.view.organization.OrganizationViewUpdate;

import java.util.List;

/**
 * Сервис для работы с организациями
 */
public interface OrganizationService {
    /**
     * Получить список организаций
     * @param filter фильтр
     * @return список организаций соответствующих характеристикам фильтра
     */
    List<OrganizationViewList> getAllOrganizationsBy(OrganizationViewFilter filter);

    /**
     * Получить организацию по id
     * @param id уникальный идентификатор организации
     * @return возвращаемый view организации
     */
    OrganizationView getOrganizationById(Long id);

    /**
     * Обновить организацию
     * @param view данные для обновления
     * @return возвращаемый view успешного обновления
     */
    ResultSuccessView updateOrganization(OrganizationViewUpdate view);

    /**
     * Сохранить организацию
     * @param organization данные для сохранения
     * @return возвращаемый view успешного сохранения
     */
    ResultSuccessView saveOrganization(OrganizationViewSave organization);
}
