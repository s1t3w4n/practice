package ru.bellintegrator.practice.model;

import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Version;
import java.util.List;

/**
 * Страна
 */
@Data
@NoArgsConstructor
@Entity(name = "Country")
public class Country {

    @Id
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
