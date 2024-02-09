package org.document.user.dto;

import lombok.Getter;
import lombok.Setter;
import org.document.common.enums.Gender;
import org.document.common.enums.UserStatus;

import java.util.Date;

@Getter
@Setter
public class UpdateUserDTO {
    private String userUuid;
    private String firstName;
    private String middleName;
    private String lastName;
    private Long age;
    private Date dob;
    private Gender gender;
    private UserStatus status;
    private String email;
}
