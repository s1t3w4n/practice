package ru.bellintegrator.practice.service.office;

import lombok.AllArgsConstructor;
import ma.glasnost.orika.MapperFactory;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.bellintegrator.practice.dao.office.OfficeDao;
import ru.bellintegrator.practice.dao.organization.OrganizationDao;
import ru.bellintegrator.practice.exception.EntityNotFoundException;
import ru.bellintegrator.practice.exception.WrongDataException;
import ru.bellintegrator.practice.model.Office;
import ru.bellintegrator.practice.model.Organization;
import ru.bellintegrator.practice.view.global.ResultSuccessView;
import ru.bellintegrator.practice.view.office.OfficeView;
import ru.bellintegrator.practice.view.office.OfficeViewFilter;
import ru.bellintegrator.practice.view.office.OfficeViewList;
import ru.bellintegrator.practice.view.office.OfficeViewSave;
import ru.bellintegrator.practice.view.office.OfficeViewUpdate;

import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

/**
 * {@inheritDoc}
 */
@Service
@AllArgsConstructor
public class OfficeServiceImpl implements OfficeService {
    private final OfficeDao officeDao;
    private final OrganizationDao organizationDao;
    private final MapperFactory mapperFactory;

    /**
     * {@inheritDoc}
     */
    @Override
    @Transactional(readOnly = true)
    public List<OfficeViewList> getAllOfficeBy(OfficeViewFilter filter) {
        return officeDao.findAllOfficeBy(filter).stream()
                .map(mapperFactory.getMapperFacade(Office.class, OfficeViewList.class)::map)
                .collect(Collectors.toList());
    }

    /**
     * {@inheritDoc}
     */
    @Override
    @Transactional(readOnly = true)
    public OfficeView getOfficeById(Long id) {
        OfficeView view = mapperFactory.getMapperFacade().map(officeDao.findOfficeById(id), OfficeView.class);
        if (Objects.nonNull(view)) {
            return view;
        } else {
            throw new EntityNotFoundException(Office.class.getSimpleName());
        }
    }

    /**
     * {@inheritDoc}
     */
    @Override
    @Transactional
    public ResultSuccessView updateOffice(OfficeViewUpdate view) {
        Office office = officeDao.findOfficeById(view.getId());
        if (Objects.nonNull(office)) {
            mapperFactory.classMap(OfficeViewUpdate.class, Office.class)
                    .mapNulls(false)
                    .byDefault()
                    .register();
            mapperFactory.getMapperFacade().map(view, office);
            return new ResultSuccessView();
        } else {
            throw new EntityNotFoundException(Office.class.getSimpleName());
        }
    }

    /**
     * {@inheritDoc}
     */
    @Override
    @Transactional
    public ResultSuccessView saveOffice(OfficeViewSave view) {
        Office office = mapperFactory.getMapperFacade().map(view, Office.class);
        try {
            office.setOrganization(checkIfExistsOrganization(organizationDao.findOrganizationById(view.getOrgId())));
        } catch (RuntimeException e) {
            throw new WrongDataException(e.getMessage());
        }
        officeDao.saveOffice(office);
        return new ResultSuccessView();
    }

    private Organization checkIfExistsOrganization(Organization organization) {
        if (Objects.nonNull(organization)) {
            return organization;
        } else {
            throw new EntityNotFoundException(Organization.class.getSimpleName());
        }
    }
}
