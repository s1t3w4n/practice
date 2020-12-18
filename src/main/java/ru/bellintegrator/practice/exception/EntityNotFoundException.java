package ru.bellintegrator.practice.exception;

public class EntityNotFoundException extends RuntimeException {
    public EntityNotFoundException(String className) {
        super("Не найдена сущность " + className + " с запрашиваемыми параметрами");
    }
}
