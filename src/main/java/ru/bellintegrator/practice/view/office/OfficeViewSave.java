package ru.bellintegrator.practice.view.office;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotNull;

/**
 * View организации для сохранения по api
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
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
