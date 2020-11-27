package ru.bellintegrator.practice.view.global;

import lombok.Data;

import java.util.UUID;
/**
 * View ответа в который оборачиваются все ошибки при отправке по api
 */
@Data
public class ErrorView {
    /**
     * Поле error
     */
    private final String error;

    public ErrorView(String message) {
        this.error = message + " UUID: " + UUID.randomUUID();
    }
}
