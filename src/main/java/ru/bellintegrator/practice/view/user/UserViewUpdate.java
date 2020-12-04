package ru.bellintegrator.practice.view.user;

import lombok.Data;

import javax.validation.constraints.NotNull;
import java.util.Date;

/**
 * View пользователя для обновления по api
 */
@Data
public class UserViewUpdate {
    /**
     * Уникальный идентификатор пользователя
     */
    private Long id;

    /**
     * Уникальный идентификатор офиса
     */
    @NotNull(message = "Уникальный идентификатор офиса обязательный параметр")
    private Long officeId;

    /**
     * Имя пользователя
     */
    @NotNull(message = "Имя пользователя обязательный параметр")
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
    @NotNull(message = "Должность пользователя обязательный параметр")
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
     * Код страны
     */
    private Integer citizenshipCode;

    /**
     * Статус идентификации
     */
    private Boolean isIdentified;
}
