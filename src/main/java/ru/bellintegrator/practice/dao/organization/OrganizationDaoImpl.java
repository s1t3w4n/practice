package ru.bellintegrator.practice.dao.organization;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Repository;
import ru.bellintegrator.practice.model.Organization;
import ru.bellintegrator.practice.view.organization.OrganizationViewListIn;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Objects;

/**
 * {@inheritDoc}
 */
@Repository
@AllArgsConstructor
public class OrganizationDaoImpl implements OrganizationDao {

    @PersistenceContext
    private final EntityManager entityManager;

    /**
     * {@inheritDoc}
     */
    @Override
    public List<Organization> findAllOrganizationBy(OrganizationViewListIn filterIn) {
        CriteriaBuilder builder = entityManager.getCriteriaBuilder();
        CriteriaQuery<Organization> criteriaQuery = builder.createQuery(Organization.class);
        Root<Organization> organizationRoot = criteriaQuery.from(Organization.class);
        List<Predicate> predicates = new ArrayList<>();

        if (Objects.nonNull(filterIn.getName()) && !filterIn.getName().isEmpty()) {
            predicates.add(builder.equal(organizationRoot.get("name"), filterIn.getName()));
        }
        if (Objects.nonNull(filterIn.getInn()) && !filterIn.getInn().isEmpty()) {
            predicates.add(builder.equal(organizationRoot.get("inn"), filterIn.getInn()));
        }
        if (Objects.nonNull(filterIn.getIsActive())) {
            predicates.add(builder.equal(organizationRoot.get("isActive"), filterIn.getIsActive()));
        }

        if (predicates.isEmpty()) {
            return Collections.emptyList();
        } else {
            criteriaQuery.select(organizationRoot).where(toPredicatesArray(predicates));
            TypedQuery<Organization> query = entityManager.createQuery(criteriaQuery);
            return query.getResultList();
        }
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Organization findOrganizationById(Long id) {
        return entityManager.find(Organization.class, id);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public boolean updateOrganization(Organization organization) {
        return Objects.nonNull(entityManager.merge(organization));
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void saveOrganization(Organization organization) {
        entityManager.persist(organization);
    }

    private static Predicate[] toPredicatesArray(List<Predicate> predicates) {
        Predicate[] array = new Predicate[predicates.size()];
        for (int i = 0; i < predicates.size(); i++) {
            array[i] = predicates.get(i);
        }
        return array;
    }
}
