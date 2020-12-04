package ru.bellintegrator.practice.view.user;

import lombok.Data;

import java.util.Date;

/**
 * View пользователя для отправки по api
 */
@Data
public class UserView {
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

    /**
     * Телефон пользователя
     */
    private String phone;

    /**
     * Название документа
     */
    private String docName;

    /**
     * Номер документа пользователя
     */
    private String docNumber;

    /**
     * Дата регистрации документа пользователя
     */
    private Date docDate;

    /**
     * Название страны
     */
    private String citizenshipName;

    /**
     * Код страны
     */
    private Integer citizenshipCode;

    /**
     * Статус идентификации
     */
    private Boolean isIdentified;
}
