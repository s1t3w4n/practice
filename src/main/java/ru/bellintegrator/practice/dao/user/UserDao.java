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

    /**
     * Получить пользователя по уникальному идентификатору
     * @param id уникальный идентификатор пользователя
     * @return пользователь с запрашиваемым уникальным идентификатором
     */
    User findUserById(Long id);

    /**
     * Обновить пользователя
     * @param user данные для обновления
     * @return возвращаемое значение результата операции
     */
    boolean updateUser(User user);
}
