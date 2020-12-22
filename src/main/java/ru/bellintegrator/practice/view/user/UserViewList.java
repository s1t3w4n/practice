package ru.bellintegrator.practice.view.user;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * возвращаемое View пользователя после фильтрации
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
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
