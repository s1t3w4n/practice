package ru.bellintegrator.practice.dao.user;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Repository;
import ru.bellintegrator.practice.model.User;
import ru.bellintegrator.practice.view.user.UserViewFilter;

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
public class UserDaoImpl implements UserDao {

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

        if (predicates.isEmpty()) {
            return Collections.emptyList();
        } else {
            criteriaQuery.select(userRoot).where(predicates.toArray(new Predicate[0]));
            TypedQuery<User> query = entityManager.createQuery(criteriaQuery);
            return query.getResultList();
        }
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
    public boolean updateUser(User user) {
        return Objects.nonNull(entityManager.merge(user));
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void saveUser(User user) {
        entityManager.persist(user);
    }

    private static void putStringFieldPredicate(String fieldName,
                                                String fieldValue,
                                                List<Predicate> predicates,
                                                CriteriaBuilder builder,
                                                Root<User> userRoot) {
        if (Objects.nonNull(fieldValue) && !fieldValue.isEmpty()) {
            predicates.add(builder.equal(userRoot.get(fieldName), fieldValue));
        }
    }

    private static void putNumberFieldPredicate(String tableName,
                                                String fieldName,
                                                Number fieldValue,
                                                List<Predicate> predicates,
                                                CriteriaBuilder builder,
                                                Root<User> userRoot) {
        if (Objects.nonNull(fieldValue) && fieldValue.longValue() > 0) {
            Path<Integer> path = userRoot.join(tableName).get(fieldName);
            predicates.add(builder.equal(path, fieldValue));
        }
    }
}
