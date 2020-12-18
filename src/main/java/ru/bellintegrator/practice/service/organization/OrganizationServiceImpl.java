package ru.bellintegrator.practice.service.organization;

import lombok.AllArgsConstructor;
import ma.glasnost.orika.MapperFactory;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.bellintegrator.practice.dao.organization.OrganizationDao;
import ru.bellintegrator.practice.exception.IdNotFound;
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
        OrganizationView view = mapperFactory.getMapperFacade().map(organizationDao.findOrganizationById(id), OrganizationView.class);
        if (Objects.nonNull(view)) {
            return view;
        } else {
            throw new IdNotFound();
        }
    }

    /**
     * {@inheritDoc}
     */
    @Override
    @Transactional
    public ResultSuccessView updateOrganization(OrganizationViewUpdate view) {
        Organization organization = organizationDao.findOrganizationById(view.getId());
        if (Objects.nonNull(organization)) {
            mapperFactory.classMap(OrganizationViewUpdate.class, Organization.class)
                    .mapNulls(false)
                    .byDefault()
                    .register();
            mapperFactory.getMapperFacade().map(view, organization);
            return new ResultSuccessView();
        } else {
            throw new IdNotFound();
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
