package ru.bellintegrator.practice.dao.user;

import ru.bellintegrator.practice.model.User;
import ru.bellintegrator.practice.view.user.UserViewFilter;

import java.util.List;

/**
 * DAO для работы с пользователями
 */
public interface UserDao {
    /**
     * Получить список пользователей, соответствующих параметрам фильтра
     *
     * @param filter параметры фильтра
     * @return список пользователей соответствующих параметрам фильтра
     */
    List<User> findAllUserBy(UserViewFilter filter);
}
