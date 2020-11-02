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
    @Column(name = "Id")
    private Long id;

    /**
     * Служебное поле hibernate
     */
    @Version
    private Integer version;

    /**
     * Название организации
     */
    @Column(name = "name")
    private String name;

    /**
     * Полное название организации
     */
    @Column(name = "full_name")
    private String fullName;

    /**
     * ИНН организации
     */
    @Column(name = "inn")
    private String inn;

    /**
     * КПП организации
     */
    @Column(name = "kpp")
    private String kpp;

    /**
     * Адрес организации
     */
    @Column(name = "address")
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
