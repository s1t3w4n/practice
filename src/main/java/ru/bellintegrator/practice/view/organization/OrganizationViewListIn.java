package ru.bellintegrator.practice.view.organization;

import lombok.Data;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

/**
 * входящее View организации с параметрами фильтра
 */
@Data
public class OrganizationViewListIn {
    /**
     * Наименование организации
     */
    @NotNull(message = "Наименование организации обязательный параметр")
    private String name;

    /**
     * ИНН организации
     */
    @Size(max = 10, min = 10, message = "ИНН организации составляет 10 символов")
    private String inn;

    /**
     * Статус организации
     */
    private Boolean isActive;
}
