package ru.bellintegrator.practice.view;

import lombok.Data;
/**
 * View ответа в который оборачиваются все возвращаемые типы при отправке по api
 */
@Data
public class MainView {
    /**
     * Поле data
     */
    private final Object data;
}