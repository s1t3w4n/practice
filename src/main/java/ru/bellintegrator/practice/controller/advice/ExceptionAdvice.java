package ru.bellintegrator.practice.controller.advice;


import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import ru.bellintegrator.practice.view.ErrorView;

@ControllerAdvice
public class ExceptionAdvice {
    @ExceptionHandler(value = Exception.class)
    public ResponseEntity<ErrorView> exception(Exception exception) {
        return new ResponseEntity<>(new ErrorView(exception),
                HttpStatus.INTERNAL_SERVER_ERROR);
    }
}
