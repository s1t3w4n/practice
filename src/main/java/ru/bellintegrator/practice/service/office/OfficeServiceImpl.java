package ru.bellintegrator.practice.service.office;

import lombok.AllArgsConstructor;
import ma.glasnost.orika.MapperFactory;
import org.springframework.stereotype.Service;
import ru.bellintegrator.practice.dao.office.OfficeDao;
import ru.bellintegrator.practice.model.Office;
import ru.bellintegrator.practice.view.office.OfficeView;
import ru.bellintegrator.practice.view.office.OfficeViewListIn;
import ru.bellintegrator.practice.view.office.OfficeViewListOut;

import java.util.List;
import java.util.stream.Collectors;

/**
 * {@inheritDoc}
 */
@Service
@AllArgsConstructor
public class OfficeServiceImpl implements OfficeService {
    private final OfficeDao officeDao;
    private final MapperFactory mapperFactory;

    /**
     * {@inheritDoc}
     */
    @Override
    public List<OfficeViewListOut> getAllOfficeBy(OfficeViewListIn filterIn) {
        List<Office> allOfficeBy = officeDao.findAllOfficeBy(filterIn);
        return allOfficeBy.stream()
                .map(mapperFactory.getMapperFacade(Office.class, OfficeViewListOut.class)::map)
                .collect(Collectors.toList());
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public OfficeView getOfficeById(Long id) {
        return mapperFactory.getMapperFacade().map(officeDao.findOfficeById(id), OfficeView.class);
    }
}
