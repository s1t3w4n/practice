package ru.bellintegrator.practice.service.organization;

import lombok.AllArgsConstructor;
import ma.glasnost.orika.MapperFactory;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.bellintegrator.practice.dao.organization.OrganizationDao;
import ru.bellintegrator.practice.model.Organization;
import ru.bellintegrator.practice.view.global.ResultSuccessView;
import ru.bellintegrator.practice.view.organization.OrganizationViewIn;
import ru.bellintegrator.practice.view.organization.OrganizationViewListIn;
import ru.bellintegrator.practice.view.organization.OrganizationViewListOut;
import ru.bellintegrator.practice.view.organization.OrganizationViewUpdateIn;

import javax.validation.Valid;
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
    public List<OrganizationViewListOut> getAllOrganizationsBy(@Valid OrganizationViewListIn filterIn) {
        return organizationDao.findAllOrganizationBy(filterIn).stream()
                .map(mapperFactory.getMapperFacade(Organization.class, OrganizationViewListOut.class)::map)
                .collect(Collectors.toList());
    }

    /**
     * {@inheritDoc}
     */
    @Override
    @Transactional(readOnly = true)
    public OrganizationViewIn getOrganizationById(Long id) {
        return mapperFactory.getMapperFacade().map(organizationDao.findOrganizationById(id), OrganizationViewIn.class);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    @Transactional
    public ResultSuccessView updateOrganization(OrganizationViewUpdateIn organization) {
        if (Objects.nonNull(organization.getId()) && organization.getId() > 0) {
            Organization persisted = organizationDao.findOrganizationById(organization.getId());
            if (Objects.nonNull(persisted)) {
                mapperFactory.getMapperFacade().map(organization, persisted);
                boolean b = organizationDao.updateOrganization(persisted);
                if (b) {
                    return new ResultSuccessView();
                } else {
                    throw new RuntimeException("Обновление организации не выполнено");
                }
            } else {
                throw new RuntimeException("id обязательный параметр");
            }
        } else {
            throw new RuntimeException("id обязательный параметр");
        }
    }
}
