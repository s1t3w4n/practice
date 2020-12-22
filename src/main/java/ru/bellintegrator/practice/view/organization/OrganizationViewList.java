package ru.bellintegrator.practice.view.organization;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * возвращаемое View организации после фильтрации
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
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
