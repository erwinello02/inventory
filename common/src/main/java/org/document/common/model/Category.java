package org.document.common.model;

import lombok.*;
import lombok.experimental.SuperBuilder;
import org.document.common.auditware.Auditable;
import org.document.common.enums.CategoryStatus;
import org.document.common.enums.UserStatus;

import javax.persistence.*;
import java.io.Serializable;

@Getter
@Setter
@Entity
@NoArgsConstructor
@SuperBuilder
@Table(name = "category")
public class Category extends Auditable<String> implements Serializable {
    @Id
    @SequenceGenerator(name = "category_pk_seq", sequenceName = "category_id_seq", allocationSize = 1)
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "category_pk_seq")
    @Column(name = "category_id")
    private Long categoryId;

    @Column(name = "category_uuid")
    private String categoryUuid;

    @Column(name = "category_name")
    private String categoryName;

    @Column(name = "category_code")
    private String code;

    @Column(name = "category_description")
    private String description;

    @Column(name = "category_image")
    private String image;

    @Enumerated(EnumType.STRING)
    @Column(name = "status")
    private CategoryStatus status;
}