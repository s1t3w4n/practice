package ru.bellintegrator.practice.util;

import javax.persistence.criteria.Predicate;
import java.util.List;

/**
 * Вспомогательный функциональный класс для операций в слое Dao
 */
public class DaoUtil {
    private DaoUtil() {
    }

    public static Predicate[] toPredicatesArray(List<Predicate> predicates) {
        Predicate[] array = new Predicate[predicates.size()];
        for (int i = 0; i < predicates.size(); i++) {
            array[i] = predicates.get(i);
        }
        return array;
    }
}
