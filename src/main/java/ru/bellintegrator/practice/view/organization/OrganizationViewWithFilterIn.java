package ru.bellintegrator.practice.view.organization;

import lombok.Data;

/**
 * входящее View организации с параметрами фильтра
 */
@Data
public class OrganizationViewWithFilterIn {
    /**
     * Наименование организации
     */
    private String name;

    /**
     * ИНН организации
     */
    private String inn;

    /**
     * Статус организации
     */
    private Boolean isActive;
}
