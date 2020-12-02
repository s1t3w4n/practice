package ru.bellintegrator.practice.view.global;

import lombok.Data;

/**
 * View всех успешных операций при отправке по api
 */
@Data
public class ResultSuccessView {
    /**
     * Возвращаемое поле result по api успешной операции
     */
    private final String result = "success";
}
