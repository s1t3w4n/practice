package ru.bellintegrator.practice.dao.doc;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Repository;
import ru.bellintegrator.practice.model.Doc;

import javax.persistence.EntityManager;
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
public class DocDaoImpl implements DocDao {

    private final EntityManager entityManager;

    /**
     * {@inheritDoc}
     */
    @Override
    public List<Doc> getAll() {
        TypedQuery<Doc> query = entityManager.createQuery("SELECT d FROM Doc d", Doc.class);
        return query.getResultList();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Doc getDocById(Long id) {
        return entityManager.find(Doc.class, id);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Doc getDocByCode(int code) {
        CriteriaBuilder criteriaBuilder = entityManager.getCriteriaBuilder();
        CriteriaQuery<Doc> query = criteriaBuilder.createQuery(Doc.class);
        Root<Doc> docRoot = query.from(Doc.class);
        Predicate filter = criteriaBuilder.equal(docRoot.get("code"), code);
        query.select(docRoot).where(filter);
        TypedQuery<Doc> typedQuery = entityManager.createQuery(query);
        return typedQuery.getSingleResult();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void saveDoc(Doc doc) {
        entityManager.persist(doc);
    }
}
