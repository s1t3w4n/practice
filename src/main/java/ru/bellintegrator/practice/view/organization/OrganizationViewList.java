package ru.bellintegrator.practice.view.organization;

import lombok.Data;

/**
 * возвращаемое View организации после фильтрации
 */
@Data
public class OrganizationViewList {
    /**
     * Уникальный идентификатор организации
     */
    private Long id;

    /**
     * Наименование
     */
    private String name;

    /**
     * Статус организации
     */
    private Boolean isActive;
}
