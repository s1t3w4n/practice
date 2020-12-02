package ru.bellintegrator.practice.dao.organization;

import ru.bellintegrator.practice.model.Organization;
import ru.bellintegrator.practice.view.organization.OrganizationViewListIn;

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
    List<Organization> findAllOrganizationBy(OrganizationViewListIn filterIn);

    /**
     * Получить организацию по уникальному идентификатору
     * @param id уникальный идентификатор организации
     * @return организация с запрашиваемым уникальным идентификатором
     */
    Organization findOrganizationById(Long id);

    /**
     * Обновить организацию
     * @param organization данные для обновления
     * @return возвращаемое значение результата операции
     */
    boolean updateOrganization(Organization organization);

    /**
     * Сохранить организацию
     * @param organization данные для сохранения
     */
    void saveOrganization(Organization organization);
}
