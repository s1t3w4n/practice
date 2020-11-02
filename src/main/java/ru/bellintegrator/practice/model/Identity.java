package ru.bellintegrator.practice.model;

import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.Date;

/**
 * Персональный документ
 */
@Data
@NoArgsConstructor
@Entity(name = "Identity")
public class Identity {

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
    @Column(name = "doc_code", nullable = false)
    private int docCode;

    /**
     * Номер документа пользователя
     */
    @Column(name = "doc_number")
    private String docNumber;

    /**
     * Дата регистрации документа пользователя
     */
    @Column(name = "citizenship_code")
    private Date docDate;

    @ManyToOne(targetEntity = Country.class)
    @JoinColumn(name = "doc_code")
    private Doc doc;

}
