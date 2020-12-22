package ru.bellintegrator.practice.controller.advice;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
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
        String uuid = " UUID: " + UUID.randomUUID();
        if (exception instanceof EntityNotFoundException) {
            String identifiedMessage = exception.getMessage() + uuid;
            LOGGER.warn(identifiedMessage);
            return makeErrorResponse(identifiedMessage, HttpStatus.NOT_FOUND);
        } else if (exception instanceof WrongDataException) {
            String identifiedMessage = exception.getMessage() + uuid;
            LOGGER.warn(identifiedMessage);
            return makeErrorResponse(identifiedMessage, HttpStatus.BAD_REQUEST);
        } else if (exception instanceof MethodArgumentNotValidException) {
            String identifiedMessage = ((MethodArgumentNotValidException) exception)
                    .getBindingResult().getAllErrors().get(0).getDefaultMessage()
                    + uuid;
            LOGGER.warn(identifiedMessage);
            return makeErrorResponse(identifiedMessage, HttpStatus.BAD_REQUEST);
        } else {
            String identifiedMessage = INTERNAL_SERVER_ERROR + uuid;
            LOGGER.error(identifiedMessage, exception);
            return makeErrorResponse(identifiedMessage, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    private ResponseEntity<ErrorView> makeErrorResponse(String message, HttpStatus status) {
        return new ResponseEntity<>(new ErrorView(message), status);
    }

}
