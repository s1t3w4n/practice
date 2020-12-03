package ru.bellintegrator.practice.view.office;

import lombok.Data;

/**
 * возвращаемое View офиса после фильтрации
 */
@Data
public class OfficeViewListOut {

    /**
     * Уникальный идентификатор офиса
     */
    private Long id;

    /**
     * Название офиса
     */
    private String name;

    /**
     * Статус офиса
     */
    private Boolean isActive;
}
