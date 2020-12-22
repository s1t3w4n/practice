package ru.bellintegrator.practice.view.office;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotNull;

/**
 * входящее View офиса с параметрами фильтра
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class OfficeViewFilter {

    /**
     * Уникальный идентификатор организации
     */
    @NotNull
    private Long orgId;

    /**
     * Название офиса
     */
    private String name;


    /**
     * Телефон офиса
     */
    private String phone;

    /**
     * Статус офиса
     */
    private Boolean isActive;
}
