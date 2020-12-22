package ru.bellintegrator.practice.view.organization;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

/**
 * View организации для сохранения по api
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
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
    @Size(max = 10, min = 10, message = "ИНН организации составляет 10 символов")
    private String inn;

    /**
     * КПП организации
     */
    @NotNull(message = "КПП организации обязательный параметр")
    @Size(min = 9, max = 9, message = "КПП организации составляет 9 символов")
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
