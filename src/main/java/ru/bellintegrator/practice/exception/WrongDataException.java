package ru.bellintegrator.practice.exception;

public class WrongDataException extends RuntimeException {
    public WrongDataException(String message) {
        super("Операция не выполнена по причине: " + message);
    }
}
