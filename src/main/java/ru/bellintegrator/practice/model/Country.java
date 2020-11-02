package ru.bellintegrator.practice.model;

import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.List;

/**
 * Страна
 */
@Data
@NoArgsConstructor
@Entity(name = "Country")
public class Country {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "Id", nullable = false)
    private Long id;

    /**
     * Код страны
     */
    @Column(name = "code", nullable = false)
    private int code;

    /**
     * Служебное поле hibernate
     */
    @Version
    private Long version;

    /**
     * Название страны
     */
    @Column(name = "name", nullable = false)
    private String name;

    @OneToMany(targetEntity = User.class)
    private List<User> users;
}
