package ru.bellintegrator.practice.model;

import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Version;

/**
 * Страна
 */
@Data
@NoArgsConstructor
@Entity(name = "Country")
public class Country {

    @Id
    @Column(name = "code")
    private int code;

    /**
     * Служебное поле hibernate
     */
    @Version
    private Integer version;

    /**
     * Название страны
     */
    @Column(name = "name")
    private String name;
}
