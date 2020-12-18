package ru.bellintegrator.practice.dao.organization;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Repository;
import ru.bellintegrator.practice.application.SystemDao;
import ru.bellintegrator.practice.model.Organization;
import ru.bellintegrator.practice.view.organization.OrganizationViewFilter;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;
import java.util.ArrayList;
import java.util.List;

/**
 * {@inheritDoc}
 */
@Repository
@AllArgsConstructor
public class OrganizationDaoImpl extends SystemDao<Organization> implements OrganizationDao {

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

        putStringFieldPredicate("name", filter.getName(), predicates, builder, organizationRoot);
        putStringFieldPredicate("inn", filter.getInn(), predicates, builder, organizationRoot);
        putBooleanFieldPredicate(filter.getIsActive(), predicates, builder, organizationRoot);

        return getResultList(predicates, criteriaQuery, organizationRoot, entityManager);
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
