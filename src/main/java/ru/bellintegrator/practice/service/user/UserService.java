package ru.bellintegrator.practice.service.user;

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
}
