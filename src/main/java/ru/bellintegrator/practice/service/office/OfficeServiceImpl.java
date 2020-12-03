package ru.bellintegrator.practice.service.office;

import lombok.AllArgsConstructor;
import ma.glasnost.orika.MapperFactory;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.bellintegrator.practice.dao.office.OfficeDao;
import ru.bellintegrator.practice.dao.organization.OrganizationDao;
import ru.bellintegrator.practice.model.Office;
import ru.bellintegrator.practice.model.Organization;
import ru.bellintegrator.practice.view.global.ResultSuccessView;
import ru.bellintegrator.practice.view.office.*;

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
        List<Office> allOfficeBy = officeDao.findAllOfficeBy(filter);
        return allOfficeBy.stream()
                .map(mapperFactory.getMapperFacade(Office.class, OfficeViewList.class)::map)
                .collect(Collectors.toList());
    }

    /**
     * {@inheritDoc}
     */
    @Override
    @Transactional(readOnly = true)
    public OfficeView getOfficeById(Long id) {
        return mapperFactory.getMapperFacade().map(officeDao.findOfficeById(id), OfficeView.class);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    @Transactional
    public ResultSuccessView updateOffice(OfficeViewUpdate office) {
        if (Objects.nonNull(office.getId()) && office.getId() > 0) {
            Office persisted = officeDao.findOfficeById(office.getId());
            if (Objects.nonNull(persisted)) {
                mapperFactory.getMapperFacade().map(office, persisted);
                if (officeDao.updateOffice(persisted)) {
                    return new ResultSuccessView();
                } else {
                    throw new RuntimeException("Обновление офиса не выполнено");
                }
            } else {
                throw new RuntimeException("Нет офиса с таким id");
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
    public ResultSuccessView saveOffice(OfficeViewSave office) {
        Organization organization = organizationDao.findOrganizationById(office.getOrgId());
        if (Objects.nonNull(organization)) {
            Office toPersist = mapperFactory.getMapperFacade().map(office, Office.class);
            toPersist.setOrganization(organization);
            officeDao.saveOffice(toPersist);
            return new ResultSuccessView();
        } else {
            throw new RuntimeException("Нет организации с таким id");
        }
    }
}
