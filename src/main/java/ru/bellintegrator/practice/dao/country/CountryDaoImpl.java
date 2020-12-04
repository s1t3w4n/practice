package ru.bellintegrator.practice.dao.country;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Repository;
import ru.bellintegrator.practice.model.Country;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;
import java.util.List;

/**
 * {@inheritDoc}
 */
@AllArgsConstructor
@Repository
public class CountryDaoImpl implements CountryDao {

    @PersistenceContext
    private final EntityManager entityManager;

    /**
     * {@inheritDoc}
     */
    @Override
    public List<Country> getAll() {
        TypedQuery<Country> query = entityManager.createQuery("SELECT с FROM Country с", Country.class);
        return query.getResultList();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Country findCountryByCode(int code) {
        CriteriaBuilder criteriaBuilder = entityManager.getCriteriaBuilder();
        CriteriaQuery<Country> query = criteriaBuilder.createQuery(Country.class);
        Root<Country> countryRoot = query.from(Country.class);
        Predicate filter = criteriaBuilder.equal(countryRoot.get("code"), code);
        query.select(countryRoot).where(filter);
        TypedQuery<Country> typedQuery = entityManager.createQuery(query);
        return typedQuery.getSingleResult();
    }
}
