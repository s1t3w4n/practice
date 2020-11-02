package ru.bellintegrator.practice.model;

import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.persistence.Version;
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
     * Уникальный идентификатор документа
     */
    @Column(name = "doc_id")
    private int docCode;

    /**
     * Номер документа пользователя
     */
    @Column(name = "doc_number")
    private String docNumber;

    /**
     * Дата регистрации документа пользователя
     */
    @Column(name = "doc_date")
    @Temporal(TemporalType.DATE)
    private Date docDate;

    @ManyToOne(targetEntity = Doc.class)
    @JoinColumn(name = "doc_id")
    private Doc doc;

    @OneToOne(targetEntity = User.class)
    private User user;
}
