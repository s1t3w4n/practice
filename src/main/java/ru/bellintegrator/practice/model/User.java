package ru.bellintegrator.practice.model;

import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

/**
 * Пользователь
 */
@Data
@NoArgsConstructor
@Entity(name = "User")
public class User {

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
     * Уникальный идентификатор офиса
     */
    @Column(name = "office_id", nullable = false)
    private Long officeId;

    /**
     * Имя пользователя
     */
    @Column(name = "first_name", nullable = false)
    private String firstName;

    /**
     * Фамилия пользователя
     */
    @Column(name = "second_name")
    private String secondName;

    /**
     * Отчество пользователя
     */
    @Column(name = "middle_name")
    private String middleName;

    /**
     * Должность пользователя
     */
    @Column(name = "position", nullable = false)
    private String position;

    /**
     * Телефон пользователя
     */
    @Column(name = "phone")
    private String phone;

    /**
     * Статус идентификации
     */
    @Column(name = "is_active", nullable = false)
    private boolean isActive;

    @ManyToOne(targetEntity = Office.class)
    @JoinColumn(name = "office_id")
    private Office office;

}
