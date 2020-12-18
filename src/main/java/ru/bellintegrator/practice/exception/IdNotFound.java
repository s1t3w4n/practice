package ru.bellintegrator.practice.exception;

public class IdNotFound extends RuntimeException {

    public IdNotFound() {
        super("Не найдена сущность с таким id");
    }
}
