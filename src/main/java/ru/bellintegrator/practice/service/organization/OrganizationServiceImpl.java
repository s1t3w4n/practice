package ru.bellintegrator.practice.service.organization;

import lombok.AllArgsConstructor;
import ma.glasnost.orika.MapperFactory;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.bellintegrator.practice.dao.organization.OrganizationDao;
import ru.bellintegrator.practice.model.Organization;
import ru.bellintegrator.practice.view.global.ResultSuccessView;
import ru.bellintegrator.practice.view.organization.OrganizationView;
import ru.bellintegrator.practice.view.organization.OrganizationViewFilter;
import ru.bellintegrator.practice.view.organization.OrganizationViewList;
import ru.bellintegrator.practice.view.organization.OrganizationViewSave;
import ru.bellintegrator.practice.view.organization.OrganizationViewUpdate;

import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

/**
 * {@inheritDoc}
 */
@Service
@AllArgsConstructor
public class OrganizationServiceImpl implements OrganizationService {

    private final OrganizationDao organizationDao;
    private final MapperFactory mapperFactory;

    /**
     * {@inheritDoc}
     */
    @Override
    @Transactional(readOnly = true)
    public List<OrganizationViewList> getAllOrganizationsBy(OrganizationViewFilter filter) {
        return organizationDao.findAllOrganizationBy(filter).stream()
                .map(mapperFactory.getMapperFacade(Organization.class, OrganizationViewList.class)::map)
                .collect(Collectors.toList());
    }

    /**
     * {@inheritDoc}
     */
    @Override
    @Transactional(readOnly = true)
    public OrganizationView getOrganizationById(Long id) {
        return mapperFactory.getMapperFacade().map(organizationDao.findOrganizationById(id), OrganizationView.class);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    @Transactional
    public ResultSuccessView updateOrganization(OrganizationViewUpdate organization) {
        if (Objects.nonNull(organization.getId()) && organization.getId() > 0) {
            Organization persisted = organizationDao.findOrganizationById(organization.getId());
            if (Objects.nonNull(persisted)) {
                mapperFactory.getMapperFacade().map(organization, persisted);
                if (organizationDao.updateOrganization(persisted)) {
                    return new ResultSuccessView();
                } else {
                    throw new RuntimeException("Обновление организации не выполнено");
                }
            } else {
                throw new RuntimeException("Нет организации с таким id");
            }
        } else {
            throw new RuntimeException("id обязательный параметр");
        }
    }

    /**
     * {@inheritDoc}
     */
    @Override
    @Transactional
    public ResultSuccessView saveOrganization(OrganizationViewSave organization) {
        organizationDao.saveOrganization(mapperFactory.getMapperFacade().map(organization, Organization.class));
        return new ResultSuccessView();
    }
}
