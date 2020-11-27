package ru.bellintegrator.practice.view.global;

import lombok.Data;
/**
 * View ответа в который оборачиваются все возвращаемые типы при отправке по api
 */
@Data
public class BodyView<T> {
    /**
     * Поле data
     */
    private final T data;
}
