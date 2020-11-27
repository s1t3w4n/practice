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

    @ManyToOne(targetEntity = Doc.class, fetch = FetchType.LAZY)
    @JoinColumn(name = "doc_id")
    private Doc doc;

    @OneToOne(targetEntity = User.class, fetch = FetchType.LAZY)
    @MapsId
    @JoinColumn(name = "user_id")
    private User user;
}
