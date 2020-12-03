package ru.bellintegrator.practice.view.office;

import lombok.Data;

import javax.validation.constraints.NotNull;

/**
 * View организации для сохранения по api
 */
@Data
public class OfficeViewSave {
    /**
     * Уникальный идентификатор организации
     */
    @NotNull(message = "Уникальный идентификатор организации обязательный параметр")
    private Long orgId;

    /**
     * Название офиса
     */
    private String name;

    /**
     * Адрес офиса
     */
    private String address;

    /**
     * Телефон офиса
     */
    private String phone;

    /**
     * Статус офиса
     */
    private Boolean isActive;
}
