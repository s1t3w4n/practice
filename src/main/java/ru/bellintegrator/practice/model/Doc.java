package ru.bellintegrator.practice.model;

import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.List;

/**
 * Документ
 */
@Data
@NoArgsConstructor
@Entity(name = "Doc")
public class Doc {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "Id", nullable = false)
    private Long id;

    /**
     * Служебное поле hibernate
     */
    @Version
    private Integer version;

    /**
     * Код документа
     */
    @Column(name = "code", nullable = false)
    private int code;

    /**
     * Название документа
     */
    @Column(name = "name", nullable = false)
    private String name;

    @OneToMany(targetEntity = Identity.class)
    private List<Identity> offices;
}
