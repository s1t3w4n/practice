package ru.bellintegrator.practice.dao.country;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Repository;
import ru.bellintegrator.practice.model.Country;

import javax.persistence.EntityManager;
import javax.persistence.TypedQuery;
import java.util.List;

/**
 * {@inheritDoc}
 */
@AllArgsConstructor
@Repository
public class CountryDaoImpl implements CountryDao {

    private final EntityManager entityManager;

    /**
     * {@inheritDoc}
     */
    @Override
    public List<Country> getAll() {
        TypedQuery<Country> query = entityManager.createQuery("SELECT с FROM Country с", Country.class);
        return query.getResultList();
    }
}
