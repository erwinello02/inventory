package org.document.user.utils;

import org.document.common.enums.UserStatus;
import org.document.common.model.Users;
import org.document.common.utils.Utils;
import org.document.user.dto.UserDTO;

import java.time.Instant;
import java.util.Date;

public class UserBuilder {
    public static Users addUserBuilder(UserDTO userDTO) throws Exception {
        Users users = new Users();
        // Auditable details
        users.setCreatedBy(userDTO.getUserName());
        users.setCreationDate(Date.from(Instant.now()));
        users.setLastModifiedBy(userDTO.getUserName());
        users.setLastModifiedDate(Date.from(Instant.now()));
        // User details
        users.setUserUuid(Utils.getGeneratedUuid());
        users.setFirstName(userDTO.getFirstName());
        users.setMiddleName(userDTO.getMiddleName());
        users.setLastName(userDTO.getLastName());
        users.setAge(userDTO.getAge());
        users.setDob(userDTO.getDob().toInstant());
        users.setGender(userDTO.getGender());
        users.setUserName(userDTO.getUserName());
        users.setEmail(userDTO.getEmail());
        users.setStatus(UserStatus.INACTIVE);
        return users;
    }
}
