package ru.bellintegrator.practice.view.office;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotNull;

/**
 * View офиса для обновления по api
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class OfficeViewUpdate {

    /**
     * Уникальный идентификатор офиса
     */
    @NotNull(message = "Уникальный идентификатор офиса обязательный параметр")
    private Long id;

    /**
     * Название офиса
     */
    @NotNull(message = "Название офиса обязательный параметр")
    private String name;

    /**
     * Адрес офиса
     */
    @NotNull(message = "Адрес офиса обязательный параметр")
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
