package ru.bellintegrator.practice.model;

import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Version;
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

    @ManyToOne(targetEntity = Organization.class, fetch = FetchType.LAZY)
    @JoinColumn(name = "org_id")
    private Organization organization;

    @OneToMany(targetEntity = User.class, fetch = FetchType.LAZY)
    private List<User> users;
}
