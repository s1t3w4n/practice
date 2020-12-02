package ru.bellintegrator.practice.view.organization;

import lombok.Data;

/**
 * View организации для отправки по api
 */
@Data
public class OrganizationView {
    /**
     * Уникальный идентификатор
     */
    private Long id;

    /**
     * Название организации
     */
    private String name;

    /**
     * Полное название организации
     */
    private String fullName;

    /**
     * ИНН организации
     */
    private String inn;

    /**
     * КПП организации
     */
    private String kpp;

    /**
     * Адрес организации
     */
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
