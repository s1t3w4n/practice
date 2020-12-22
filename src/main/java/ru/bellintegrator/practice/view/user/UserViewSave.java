package ru.bellintegrator.practice.view.user;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotNull;
import java.util.Date;

/**
 * View пользователя для отправки по api
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class UserViewSave {
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
     * Код документа
     */
    private Integer docCode;

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
