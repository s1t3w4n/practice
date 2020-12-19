package ru.bellintegrator.practice.view.country;

import lombok.Data;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

/**
 * View страны для отправки по api
 */
@Data
public class CountryView {
    /**
     * Код страны
     */
    @NotNull(message = "Код страны обязательный параметр")
    private int code;

    /**
     * Название страны
     */
    @Size(max = 64)
    @NotNull(message = "Название страны обязательный параметр")
    private String name;
}
