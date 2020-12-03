package ru.bellintegrator.practice.view.organization;

import lombok.Data;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

/**
 * View организации для сохранения по api
 */
@Data
public class OrganizationViewSave {
    /**
     * Название организации
     */
    @NotEmpty(message = "Название организации Не должно быть пустое")
    @NotNull(message = "Название организации обязательный параметр")
    private String name;

    /**
     * Полное название организации
     */
    @NotNull(message = "Полное название организации обязательный параметр")
    private String fullName;

    /**
     * ИНН организации
     */
    @NotNull(message = "ИНН организации обязательный параметр")
    private String inn;

    /**
     * КПП организации
     */
    @NotNull(message = "КПП организации обязательный параметр")
    private String kpp;

    /**
     * Адрес организации
     */
    @NotNull(message = "Адрес организации обязательный параметр")
    private String address;

    /**
     * Телефон организации
     */
    private String phone;

    /**
     * Статус организации
     */
    private Boolean isActive;
}
