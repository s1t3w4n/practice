package ru.bellintegrator.practice.view.office;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * возвращаемое View офиса после фильтрации
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class OfficeViewList {

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
