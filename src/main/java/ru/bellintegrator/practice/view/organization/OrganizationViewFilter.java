package ru.bellintegrator.practice.view.organization;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

/**
 * входящее View организации с параметрами фильтра
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class OrganizationViewFilter {
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
