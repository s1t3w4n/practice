package ru.bellintegrator.practice.controller.advice;

import org.springframework.core.MethodParameter;
import org.springframework.http.MediaType;
import org.springframework.http.server.ServerHttpRequest;
import org.springframework.http.server.ServerHttpResponse;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.servlet.mvc.method.annotation.ResponseBodyAdvice;
import ru.bellintegrator.practice.view.global.BodyView;
import ru.bellintegrator.practice.view.global.ErrorView;
import ru.bellintegrator.practice.view.global.ResultSuccessView;

/**
 * Преобразователь всех контроллеров
 */
@RestControllerAdvice
public class BodyAdvice implements ResponseBodyAdvice<Object> {

    @Override
    public boolean supports(MethodParameter methodParameter, Class aClass) {
        return !methodParameter.getParameterType().equals(ErrorView.class)
                && !methodParameter.getParameterType().equals(ResultSuccessView.class);
    }

    @Override
    public Object beforeBodyWrite(Object body, MethodParameter methodParameter, MediaType mediaType, Class aClass, ServerHttpRequest serverHttpRequest, ServerHttpResponse serverHttpResponse) {
        return new BodyView<>(body);
    }
}
