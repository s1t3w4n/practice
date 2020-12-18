package ru.bellintegrator.practice.application;

import javax.persistence.EntityManager;
import javax.persistence.TypedQuery;
import javax.persistence.criteria.*;
import java.util.Collections;
import java.util.List;
import java.util.Objects;

/**
 * Системный dao класс, с набором часто используемых методов
 */
public abstract class SystemDao<E> {
    /**
     *  Создаёт предикат для запроса в строковом поле
     * @param fieldName имя поля
     * @param fieldValue значение строкового поля
     * @param predicates набор наполняемых предикатов для запроса
     * @param builder компоновщик запроса
     * @param root ветка сущности
     */
    protected void putStringFieldPredicate(String fieldName,
                                           String fieldValue,
                                           List<Predicate> predicates,
                                           CriteriaBuilder builder,
                                           Root<E> root) {
        if (Objects.nonNull(fieldValue) && !fieldValue.isEmpty()) {
            predicates.add(builder.equal(root.get(fieldName), fieldValue));
        }
    }

    /**
     * Создаёт предикат для запроса в числовом поле
     * @param tableName имя таблицы
     * @param fieldName имя поля
     * @param fieldValue значение числового поля
     * @param predicates набор наполняемых предикатов для запроса
     * @param builder компоновщик запроса
     * @param root ветка сущности
     */
    protected void putNumberFieldPredicate(String tableName,
                                           String fieldName,
                                           Number fieldValue,
                                           List<Predicate> predicates,
                                           CriteriaBuilder builder,
                                           Root<E> root) {
        if (Objects.nonNull(fieldValue) && fieldValue.longValue() > 0) {
            Path<Integer> path = root.join(tableName).get(fieldName);
            predicates.add(builder.equal(path, fieldValue));
        }
    }

    /**
     * Создаёт предикат для запроса в логическом поле
     * @param fieldValue значение логического поля
     * @param predicates набор наполняемых предикатов для запроса
     * @param builder компоновщик запроса
     * @param root ветка сущности
     */
    protected void putBooleanFieldPredicate(Boolean fieldValue,
                                            List<Predicate> predicates,
                                            CriteriaBuilder builder,
                                            Root<E> root) {
        if (Objects.nonNull(fieldValue)) {
            predicates.add(builder.equal(root.get("isActive"), fieldValue));
        }
    }

    /**
     * Формирует результат согласно построенному запросу
     * @param predicates условия
     * @param criteriaQuery  запрос
     * @param root ветка сущностей
     * @param entityManager менеджер сущностей
     * @return результат запроса
     */
    protected List<E> getResultList(List<Predicate> predicates,
                                    CriteriaQuery<E> criteriaQuery,
                                    Root<E> root,
                                    EntityManager entityManager) {
        if (predicates.isEmpty()) {
            return Collections.emptyList();
        } else {
            criteriaQuery.select(root).where(predicates.toArray(new Predicate[0]));
            TypedQuery<E> query = entityManager.createQuery(criteriaQuery);
            return query.getResultList();
        }
    }
}
