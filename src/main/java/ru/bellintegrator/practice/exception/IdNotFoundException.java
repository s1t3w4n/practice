package ru.bellintegrator.practice.exception;

public class IdNotFoundException extends RuntimeException {
    public IdNotFoundException(String className) {
        super("Не найдена сущность " + className + " с запрашиваемым id");
    }
}
