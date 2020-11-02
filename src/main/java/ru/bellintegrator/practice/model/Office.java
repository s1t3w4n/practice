package ru.bellintegrator.practice.model;

import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.List;

/**
 * Офис
 */
@Data
@NoArgsConstructor
@Entity(name = "Office")
public class Office {

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
     * Уникальный идентификатор организации
     */
    @Column(name = "org_id", nullable = false)
    private Long orgId;

    /**
     * Название офиса
     */
    @Column(name = "name", nullable = false)
    private String name;

    /**
     * Адрес офиса
     */
    @Column(name = "address", nullable = false)
    private String address;

    /**
     * Телефон офиса
     */
    @Column(name = "phone")
    private String phone;

    /**
     * Статус офиса
     */
    @Column(name = "is_active", nullable = false)
    private boolean isActive;

    @ManyToOne(targetEntity = Organization.class)
    @JoinColumn(name = "org_id")
    private Organization organization;

    @OneToMany(targetEntity = User.class)
    private List<User> users;
}
