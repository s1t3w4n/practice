package ru.bellintegrator.practice.controller.advice;

import org.springframework.core.MethodParameter;
import org.springframework.core.ResolvableType;
import org.springframework.http.MediaType;
import org.springframework.http.server.ServerHttpRequest;
import org.springframework.http.server.ServerHttpResponse;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.servlet.mvc.method.annotation.ResponseBodyAdvice;
import ru.bellintegrator.practice.view.global.BodyView;
import ru.bellintegrator.practice.view.global.ErrorView;

/**
 * Преобразователь всех пользовательских контроллеров
 */
@RestControllerAdvice(basePackages = "ru.bellintegrator.practice.controller")
public class BodyAdvice implements ResponseBodyAdvice<Object> {

    /**
     * Отфильтровать все ответы ошибок
     */
    @Override
    public boolean supports(MethodParameter methodParameter, Class aClass) {
        return ResolvableType.forMethodParameter(methodParameter).getGeneric().toClass() != ErrorView.class;
    }

    /**
     * Обернуть все ответы в data[]
     */
    @Override
    public Object beforeBodyWrite(Object body, MethodParameter methodParameter, MediaType mediaType, Class aClass, ServerHttpRequest serverHttpRequest, ServerHttpResponse serverHttpResponse) {
        return new BodyView<>(body);
    }
}
