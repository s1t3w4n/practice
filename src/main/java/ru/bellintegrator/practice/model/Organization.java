package ru.bellintegrator.practice.model;

import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

/**
 * Организация
 */
@Data
@NoArgsConstructor
@Entity(name = "Organization")
public class Organization {

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
     * Название организации
     */
    @Column(name = "name", nullable = false)
    private String name;

    /**
     * Полное название организации
     */
    @Column(name = "full_name", nullable = false)
    private String fullName;

    /**
     * ИНН организации
     */
    @Column(name = "inn", nullable = false)
    private String inn;

    /**
     * КПП организации
     */
    @Column(name = "kpp", nullable = false)
    private String kpp;

    /**
     * Адрес организации
     */
    @Column(name = "address", nullable = false)
    private String address;

    /**
     * Телефон организации
     */
    @Column(name = "phone")
    private String phone;

    /**
     * Статус организации
     */
    @Column(name = "is_active")
    private boolean isActive;
}
