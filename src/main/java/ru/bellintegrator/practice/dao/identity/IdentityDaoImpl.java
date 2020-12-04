package ru.bellintegrator.practice.dao.identity;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Repository;
import ru.bellintegrator.practice.model.Identity;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

/**
 * {@inheritDoc}
 */
@Repository
@AllArgsConstructor
public class IdentityDaoImpl implements IdentityDao {
    @PersistenceContext
    private final EntityManager entityManager;

    /**
     * {@inheritDoc}
     */
    @Override
    public Identity findIdentityById(Long id) {
        return entityManager.find(Identity.class, id);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void saveIdentity(Identity identity) {
        entityManager.persist(identity);
    }
}
