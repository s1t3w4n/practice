package ru.bellintegrator.practice;

import org.h2.tools.Console;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.sql.SQLException;

@SpringBootApplication
public class PracticeApplication {

    public static void main(String[] args) throws SQLException {
        SpringApplication.run(PracticeApplication.class, args);
        Console.main(args);
    }
}
