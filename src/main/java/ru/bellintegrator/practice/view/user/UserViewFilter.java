package ru.bellintegrator.practice.view.user;

import lombok.Data;

import javax.validation.constraints.NotNull;

/**
 * входящее View пользователя с параметрами фильтра
 */
@Data
public class UserViewFilter {
    /**
     * Уникальный идентификатор офиса
     */
    @NotNull(message = "Уникальный идентификатор офиса обязательный параметр")
    private Long officeId;

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

    /**
     * Код документа
     */
    private Integer docCode;

    /**
     * Код страны
     */
    private Integer citizenshipCode;
}
