package ru.bellintegrator.practice.dao.doc;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Repository;
import ru.bellintegrator.practice.model.Doc;

import javax.persistence.EntityManager;
import javax.persistence.TypedQuery;
import java.util.List;

/**
 * {@inheritDoc}
 */
@AllArgsConstructor
@Repository
public class DocDaoImpl implements DocDao {

    private final EntityManager em;

    /**
     * {@inheritDoc}
     */
    @Override
    public List<Doc> all() {
        TypedQuery<Doc> query = em.createQuery("SELECT d FROM Doc d", Doc.class);
        return query.getResultList();
    }
}
