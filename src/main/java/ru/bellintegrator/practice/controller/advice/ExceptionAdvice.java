package ru.bellintegrator.practice.controller.advice;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import ru.bellintegrator.practice.view.global.ErrorView;

import javax.xml.ws.http.HTTPException;

/**
 * Преобразователь всех ответов, которые возвращают исключение
 */
@RestControllerAdvice
public class ExceptionAdvice {
    @ExceptionHandler(value = Exception.class)
    public ResponseEntity<ErrorView> exception(Exception exception) {
        return new ResponseEntity<>(new ErrorView(exception.getMessage()),
                exception instanceof HTTPException ?
                        HttpStatus.valueOf(((HTTPException)exception).getStatusCode())
                        : HttpStatus.INTERNAL_SERVER_ERROR);
    }
}
