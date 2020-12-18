package ru.bellintegrator.practice.dao.organization;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Repository;
import ru.bellintegrator.practice.model.Organization;
import ru.bellintegrator.practice.view.organization.OrganizationViewFilter;

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
    public List<Organization> findAllOrganizationBy(OrganizationViewFilter filter) {
        CriteriaBuilder builder = entityManager.getCriteriaBuilder();
        CriteriaQuery<Organization> criteriaQuery = builder.createQuery(Organization.class);
        Root<Organization> organizationRoot = criteriaQuery.from(Organization.class);
        List<Predicate> predicates = new ArrayList<>();

        if (Objects.nonNull(filter.getName()) && !filter.getName().isEmpty()) {
            predicates.add(builder.equal(organizationRoot.get("name"), filter.getName()));
        }
        if (Objects.nonNull(filter.getInn()) && !filter.getInn().isEmpty()) {
            predicates.add(builder.equal(organizationRoot.get("inn"), filter.getInn()));
        }
        if (Objects.nonNull(filter.getIsActive())) {
            predicates.add(builder.equal(organizationRoot.get("isActive"), filter.getIsActive()));
        }

        if (predicates.isEmpty()) {
            return Collections.emptyList();
        } else {
            criteriaQuery.select(organizationRoot).where(predicates.toArray(new Predicate[0]));
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
    public void saveOrganization(Organization organization) {
        entityManager.persist(organization);
    }

}
