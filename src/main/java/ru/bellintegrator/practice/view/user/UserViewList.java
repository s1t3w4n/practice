package ru.bellintegrator.practice.view.user;

import lombok.Data;

/**
 * возвращаемое View пользователя после фильтрации
 */
@Data
public class UserViewList {
    /**
     * Уникальный идентификатор пользователя
     */
    private Long id;

    /**
     * Имя пользователя
     */
    private String firstName;

    /**
     * Фамилия пользователя
     */
    private String secondName;

    /**
     * Отчество пользователя
     */
    private String middleName;

    /**
     * Должность пользователя
     */
    private String position;
}
