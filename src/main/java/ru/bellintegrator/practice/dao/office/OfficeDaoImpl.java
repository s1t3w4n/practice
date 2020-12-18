package ru.bellintegrator.practice.dao.office;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Repository;
import ru.bellintegrator.practice.application.SystemDao;
import ru.bellintegrator.practice.model.Office;
import ru.bellintegrator.practice.view.office.OfficeViewFilter;

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
public class OfficeDaoImpl extends SystemDao<Office> implements OfficeDao {

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

        putNumberFieldPredicate("organization", "id", filter.getOrgId(), predicates, builder, officeRoot);
        putStringFieldPredicate("name", filter.getName(), predicates, builder, officeRoot);
        putStringFieldPredicate("phone", filter.getPhone(), predicates, builder, officeRoot);
        putBooleanFieldPredicate(filter.getIsActive(), predicates, builder, officeRoot);

        return getResultList(predicates, criteriaQuery, officeRoot, entityManager);
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
    public void saveOffice(Office office) {
        entityManager.persist(office);
    }
}
