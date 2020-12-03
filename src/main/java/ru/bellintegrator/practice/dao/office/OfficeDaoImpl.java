package ru.bellintegrator.practice.dao.office;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Repository;
import ru.bellintegrator.practice.model.Office;
import ru.bellintegrator.practice.util.DaoUtil;
import ru.bellintegrator.practice.view.office.OfficeViewListIn;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;
import javax.persistence.criteria.*;
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
    public List<Office> findAllOfficeBy(OfficeViewListIn filterIn) {
        CriteriaBuilder builder = entityManager.getCriteriaBuilder();
        CriteriaQuery<Office> criteriaQuery = builder.createQuery(Office.class);
        Root<Office> officeRoot = criteriaQuery.from(Office.class);
        List<Predicate> predicates = new ArrayList<>();

        if (Objects.nonNull(filterIn.getOrgId()) && filterIn.getOrgId() > 0) {
            Path<Integer> orgId = officeRoot.join("organization").get("id");
            predicates.add(builder.equal(orgId, filterIn.getOrgId()));
        }
        if (Objects.nonNull(filterIn.getName()) && !filterIn.getName().isEmpty()) {
            predicates.add(builder.equal(officeRoot.get("name"), filterIn.getName()));
        }
        if (Objects.nonNull(filterIn.getPhone()) && !filterIn.getPhone().isEmpty()) {
            predicates.add(builder.equal(officeRoot.get("phone"), filterIn.getPhone()));
        }
        if (Objects.nonNull(filterIn.getIsActive())) {
            predicates.add(builder.equal(officeRoot.get("isActive"), filterIn.getIsActive()));
        }

        if (predicates.isEmpty()) {
            return Collections.emptyList();
        } else {
            criteriaQuery.select(officeRoot).where(DaoUtil.toPredicatesArray(predicates));
            TypedQuery<Office> query = entityManager.createQuery(criteriaQuery);
            return query.getResultList();
        }
    }
}
