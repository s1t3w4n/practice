package ru.bellintegrator.practice.controller.advice;

import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import ru.bellintegrator.practice.view.global.ErrorView;

/**
 * Преобразователь всех контроллеров, которые возвращают исключение
 */
@RestControllerAdvice
public class ExceptionAdvice {
    @ExceptionHandler(value = Exception.class)
    public ErrorView exception(Exception exception) {
        return new ErrorView(exception.getMessage());
    }
}
