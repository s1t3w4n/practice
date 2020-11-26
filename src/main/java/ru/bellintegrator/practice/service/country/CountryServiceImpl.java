package ru.bellintegrator.practice.service.country;

import lombok.AllArgsConstructor;
import ma.glasnost.orika.MapperFactory;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.bellintegrator.practice.dao.country.CountryDao;
import ru.bellintegrator.practice.model.Country;
import ru.bellintegrator.practice.view.country.CountryView;

import java.util.List;
import java.util.stream.Collectors;

/**
 * {@inheritDoc}
 */
@Service
@AllArgsConstructor
public class CountryServiceImpl implements CountryService {

    private final CountryDao countryDao;
    private final MapperFactory mapperFactory;

    /**
     * {@inheritDoc}
     */
    @Override
    @Transactional(readOnly = true)
    public List<CountryView> getAllCountries() {
        return countryDao.getAll().stream()
                .map(mapperFactory.getMapperFacade(Country.class, CountryView.class)::map)
                .collect(Collectors.toList());
    }
}
