package ru.bellintegrator.practice.service.user;

import ru.bellintegrator.practice.view.user.UserView;
import ru.bellintegrator.practice.view.user.UserViewFilter;
import ru.bellintegrator.practice.view.user.UserViewList;

import java.util.List;

/**
 * Сервис для работы с пользователями
 */
public interface UserService {
    /**
     * Получить список пользователей
     *
     * @param filter фильтр
     * @return список пользователей соответствующих характеристикам фильтра
     */
    List<UserViewList> getAllUserBy(UserViewFilter filter);

    /**
     * Получить пользователя по id
     * @param id уникальный идентификатор пользователя
     * @return возвращаемый view пользователя
     */
    UserView getUserById(Long id);
}
