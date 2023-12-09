package org.document.user.dto;

import lombok.Getter;
import lombok.Setter;
import org.document.common.enums.Gender;
import org.document.common.enums.UserStatus;

import java.time.Instant;

@Getter
@Setter
public class UpdateUserDTO {
    private String userUuid;
    private String firstName;
    private String middleName;
    private String lastName;
    private Long age;
    private Instant dob;
    private Gender gender;
    private UserStatus status;
}
