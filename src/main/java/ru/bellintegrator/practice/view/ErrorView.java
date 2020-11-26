package ru.bellintegrator.practice.view;

import lombok.Data;

import java.util.UUID;

@Data
public class ErrorView {
    private final String error;

    public ErrorView(Exception exception) {
        this.error = exception.getMessage() + " UUID: " + UUID.randomUUID();
    }
}
