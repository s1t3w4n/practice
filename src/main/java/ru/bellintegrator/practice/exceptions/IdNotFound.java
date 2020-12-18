package ru.bellintegrator.practice.exceptions;

public class IdNotFound extends RuntimeException {

    public IdNotFound() {
        super("Не найдена сущность с таким id");
    }
}
