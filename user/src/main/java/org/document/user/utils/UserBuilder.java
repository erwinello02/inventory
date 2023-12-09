package org.document.user.utils;

import org.document.common.enums.UserStatus;
import org.document.common.model.Users;
import org.document.user.dto.UserDTO;

public class UserBuilder {
    public static Users addUserBuilder(UserDTO userDTO) throws Exception {
        Users users = new Users();
        users.setFirstName(userDTO.getFirstName());
        users.setMiddleName(userDTO.getMiddleName());
        users.setLastName(userDTO.getLastName());
        users.setAge(userDTO.getAge());
        users.setDob(userDTO.getDob());
        users.setStatus(UserStatus.INACTIVE);
        return users;
    }
}
