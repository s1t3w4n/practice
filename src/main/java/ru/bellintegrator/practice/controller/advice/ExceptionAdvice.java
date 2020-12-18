package ru.bellintegrator.practice.controller.advice;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import ru.bellintegrator.practice.exception.IdNotFound;
import ru.bellintegrator.practice.view.global.ErrorView;

/**
 * Преобразователь всех ответов, которые возвращают исключение
 */
@RestControllerAdvice
public class ExceptionAdvice {
    @ExceptionHandler(value = Exception.class)
    public ResponseEntity<ErrorView> exception(Exception exception) {
        if (exception instanceof IdNotFound) {
            return makeErrorResponse(exception, HttpStatus.NOT_FOUND);
        } else {
            return makeErrorResponse(exception, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    private ResponseEntity<ErrorView> makeErrorResponse(Exception exception, HttpStatus status) {
        return new ResponseEntity<>(new ErrorView(exception.getMessage()), status);
    }
}
