package org.document.common.model;

import lombok.Getter;
import lombok.Setter;
import org.document.common.auditware.Auditable;
import org.document.common.enums.Gender;
import org.document.common.enums.UserStatus;

import javax.persistence.*;
import java.io.Serializable;
import java.time.Instant;

@Getter
@Setter
@Entity
@Table(name = "users")
public class Users extends Auditable<String> implements Serializable {
    @Id
    @SequenceGenerator(name = "user_pk_seq", sequenceName = "user_id_seq", allocationSize = 1)
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "user_pk_seq")
    @Column(name = "user_id")
    private Long userId;

    @Column(name = "user_uuid")
    private String userUuid;

    @Column(name = "user_name")
    private String userName;

    @Column(name = "email")
    private String email;

    @Column(name = "first_name")
    private String firstName;

    @Column(name = "middle_name")
    private String middleName;

    @Column(name = "last_name")
    private String lastName;

    @Column(name = "age")
    private Long age;

    @Column(name = "date_of_birth")
    private Instant dob;

    @Enumerated(EnumType.STRING)
    @Column(name = "gender")
    private Gender gender;

    @Enumerated(EnumType.STRING)
    @Column(name = "status")
    private UserStatus status;
}