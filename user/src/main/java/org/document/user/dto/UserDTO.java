package org.document.user.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;
import org.document.common.enums.Gender;
import java.util.Date;

@Getter
@Setter
@AllArgsConstructor
public class UserDTO {
    private String firstName;
    private String middleName;
    private String lastName;
    private Long age;
    private Date dob;
    private Gender gender;
    private String userName;
    private String email;
}
