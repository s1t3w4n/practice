package ru.bellintegrator.practice.view.office;

import lombok.Data;

import javax.validation.constraints.NotNull;

/**
 * входящее View офиса с параметрами фильтра
 */
@Data
public class OfficeViewListIn {

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
