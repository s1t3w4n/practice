package ru.bellintegrator.practice.view.global;

import lombok.Data;

/**
 * View ответа в который оборачиваются все ошибки при отправке по api
 */
@Data
public class ErrorView {
    /**
     * Поле error
     */
    private final String error;

}
