package ru.bellintegrator.practice.service.organization;

import lombok.AllArgsConstructor;
import ma.glasnost.orika.MapperFactory;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.bellintegrator.practice.dao.organization.OrganizationDao;
import ru.bellintegrator.practice.model.Organization;
import ru.bellintegrator.practice.view.organization.OrganizationView;
import ru.bellintegrator.practice.view.organization.OrganizationViewWithFilterIn;
import ru.bellintegrator.practice.view.organization.OrganizationViewWithFilterOut;

import javax.validation.Valid;
import java.util.List;
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
    public List<OrganizationViewWithFilterOut> getAllOrganizationsBy(@Valid OrganizationViewWithFilterIn filterIn) {
        return organizationDao.findAllOrganizationBy(filterIn).stream()
                .map(mapperFactory.getMapperFacade(Organization.class, OrganizationViewWithFilterOut.class)::map)
                .collect(Collectors.toList());
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public OrganizationView getOrganizationById(Long id) {
        return mapperFactory.getMapperFacade().map(organizationDao.findOrganizationById(id), OrganizationView.class);
    }
}
