package ru.bellintegrator.practice.model;

import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Version;

/**
 * Документ
 */
@Data
@NoArgsConstructor
@Entity(name = "Doc")
public class Doc {

    @Id
    @Column(name = "code", nullable = false)
    private int code;

    /**
     * Служебное поле hibernate
     */
    @Version
    private Integer version;

    /**
     * Название документа
     */
    @Column(name = "name", nullable = false)
    private String name;
}
