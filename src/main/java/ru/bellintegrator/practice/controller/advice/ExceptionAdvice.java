package ru.bellintegrator.practice.controller.advice;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import ru.bellintegrator.practice.exception.EntityNotFoundException;
import ru.bellintegrator.practice.exception.WrongDataException;
import ru.bellintegrator.practice.view.global.ErrorView;

import java.util.UUID;

/**
 * Преобразователь всех ответов, которые возвращают исключение
 */
@RestControllerAdvice
public class ExceptionAdvice {

    private static final Logger LOGGER = LoggerFactory.getLogger(ExceptionAdvice.class);

    private static final String INTERNAL_SERVER_ERROR = "Внутрення ошибка сервера";

    @ExceptionHandler(value = Exception.class)
    public ResponseEntity<ErrorView> exception(Exception exception) {
        String evaluatedMessage = exception.getMessage() + " UUID: " + UUID.randomUUID();
        LOGGER.warn(evaluatedMessage, exception);
        if (exception instanceof EntityNotFoundException) {
            return makeErrorResponse(evaluatedMessage, HttpStatus.NOT_FOUND);
        } else if (exception instanceof WrongDataException) {
            return makeErrorResponse(evaluatedMessage, HttpStatus.BAD_REQUEST);
        } else {
            return makeErrorResponse(INTERNAL_SERVER_ERROR, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    private ResponseEntity<ErrorView> makeErrorResponse(String message, HttpStatus status) {
        return new ResponseEntity<>(new ErrorView(message), status);
    }
}
