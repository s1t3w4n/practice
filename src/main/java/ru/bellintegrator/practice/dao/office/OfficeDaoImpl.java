package ru.bellintegrator.practice.dao.office;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Repository;
import ru.bellintegrator.practice.model.Office;
import ru.bellintegrator.practice.view.office.OfficeViewFilter;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Path;
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
public class OfficeDaoImpl implements OfficeDao {

    @PersistenceContext
    private final EntityManager entityManager;

    /**
     * {@inheritDoc}
     */
    @Override
    public List<Office> findAllOfficeBy(OfficeViewFilter filter) {
        CriteriaBuilder builder = entityManager.getCriteriaBuilder();
        CriteriaQuery<Office> criteriaQuery = builder.createQuery(Office.class);
        Root<Office> officeRoot = criteriaQuery.from(Office.class);
        List<Predicate> predicates = new ArrayList<>();

        if (Objects.nonNull(filter.getOrgId()) && filter.getOrgId() > 0) {
            Path<Integer> orgId = officeRoot.join("organization").get("id");
            predicates.add(builder.equal(orgId, filter.getOrgId()));
        }
        if (Objects.nonNull(filter.getName()) && !filter.getName().isEmpty()) {
            predicates.add(builder.equal(officeRoot.get("name"), filter.getName()));
        }
        if (Objects.nonNull(filter.getPhone()) && !filter.getPhone().isEmpty()) {
            predicates.add(builder.equal(officeRoot.get("phone"), filter.getPhone()));
        }
        if (Objects.nonNull(filter.getIsActive())) {
            predicates.add(builder.equal(officeRoot.get("isActive"), filter.getIsActive()));
        }

        if (predicates.isEmpty()) {
            return Collections.emptyList();
        } else {
            criteriaQuery.select(officeRoot).where(predicates.toArray(new Predicate[0]));
            TypedQuery<Office> query = entityManager.createQuery(criteriaQuery);
            return query.getResultList();
        }
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Office findOfficeById(Long id) {
        return entityManager.find(Office.class, id);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public boolean updateOffice(Office office) {
        return Objects.nonNull(entityManager.merge(office));
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void saveOffice(Office office) {
        entityManager.persist(office);
    }
}
