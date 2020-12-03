package ru.bellintegrator.practice.view.office;

import lombok.Data;

/**
 * View офиса для отправки по api
 */
@Data
public class OfficeView {

    /**
     * Уникальный идентификатор офиса
     */
    private Long id;

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
