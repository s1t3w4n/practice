package ru.bellintegrator.practice.dao.organization;

import ru.bellintegrator.practice.model.Organization;
import ru.bellintegrator.practice.view.organization.OrganizationViewWithFilterIn;

import java.util.List;

/**
 * DAO для работы с Организацией
 */
public interface OrganizationDao {
    /**
     * Получить список организаций, соответствующих параметрам фильтра
     * @param filterIn параметры фильтра
     * @return список организаций соответствующих параметрам фильтра
     */
    List<Organization> findAllOrganizationBy(OrganizationViewWithFilterIn filterIn);
}
