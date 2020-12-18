package ru.bellintegrator.practice.dao.user;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Repository;
import ru.bellintegrator.practice.application.SystemDao;
import ru.bellintegrator.practice.model.User;
import ru.bellintegrator.practice.view.user.UserViewFilter;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Path;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;
import java.util.ArrayList;
import java.util.List;

/**
 * {@inheritDoc}
 */
@Repository
@AllArgsConstructor
public class UserDaoImpl extends SystemDao<User> implements UserDao {

    @PersistenceContext
    private final EntityManager entityManager;

    /**
     * {@inheritDoc}
     */
    @Override
    public List<User> findAllUserBy(UserViewFilter filter) {
        CriteriaBuilder builder = entityManager.getCriteriaBuilder();
        CriteriaQuery<User> criteriaQuery = builder.createQuery(User.class);
        Root<User> userRoot = criteriaQuery.from(User.class);
        List<Predicate> predicates = new ArrayList<>();

        putNumberFieldPredicate("office", "id", filter.getOfficeId(), predicates, builder, userRoot);
        putStringFieldPredicate("firstName", filter.getFirstName(), predicates, builder, userRoot);
        putStringFieldPredicate("secondName", filter.getSecondName(), predicates, builder, userRoot);
        putStringFieldPredicate("middleName", filter.getMiddleName(), predicates, builder, userRoot);
        putStringFieldPredicate("position", filter.getPosition(), predicates, builder, userRoot);

        putNumberFieldPredicate("citizenship", "code", filter.getCitizenshipCode(), predicates, builder, userRoot);

        Path<Integer> path = userRoot.join("identity").get("doc").get("code");
        predicates.add(builder.equal(path, filter.getDocCode()));

        return getResultList(predicates, criteriaQuery, userRoot, entityManager);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public User findUserById(Long id) {
        return entityManager.find(User.class, id);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void saveUser(User user) {
        entityManager.persist(user);
    }

}
