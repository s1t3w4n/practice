package ru.bellintegrator.practice.view.doc;

import lombok.Data;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

/**
 * View документа для отправки по api
 */
@Data
public class DocView {
    /**
     * Код документа
     */
    @NotNull(message = "Код документа обязательный параметр")
    private int code;

    /**
     * Название документа
     */
    @Size(max = 113)
    @NotNull(message = "Название документа обязательный параметр")
    private String name;
}
