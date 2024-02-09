package org.document.user.utils;

import org.document.user.dto.UserDTO;

import static org.document.common.utils.ErrorCodes.*;

public class UserValidation {
    public static void addUserValidation(UserDTO userDTO) throws Exception {
        if(userDTO.getFirstName() == null){
            throw new IllegalAccessException(USR001);
        } else if(userDTO.getLastName() == null){
            throw new IllegalAccessException(USR002);
        } else if(userDTO.getAge() == null){
            throw new IllegalAccessException(USR003);
        } else if(userDTO.getDob() == null){
            throw new IllegalAccessException(USR004);
        } else if(userDTO.getGender() == null){
            throw new IllegalAccessException(USR005);
        } else if(userDTO.getUserName() == null){
            throw new IllegalAccessException(USR007);
        } else if(userDTO.getEmail() == null){
            throw new IllegalAccessException(USR008);
        }

    }
}
