package org.document.user.utils;

import org.document.common.model.Users;
import org.document.user.dto.UpdateUserDTO;

public class UserCondition {
    public static Users updateUserCondition(
            UpdateUserDTO updateUserDTO,
            Users user
    ) throws Exception {
        if(updateUserDTO.getFirstName() != null){
            user.setFirstName(updateUserDTO.getFirstName());
        }
        if(updateUserDTO.getMiddleName() != null){
            user.setMiddleName(updateUserDTO.getMiddleName());
        }
        if(updateUserDTO.getLastName() != null){
            user.setLastName(updateUserDTO.getLastName());
        }
        if(updateUserDTO.getAge() != null){
            user.setAge(updateUserDTO.getAge());
        }
        if(updateUserDTO.getDob() != null){
            user.setDob(updateUserDTO.getDob());
        }
        if(updateUserDTO.getGender() != null){
            user.setGender(updateUserDTO.getGender());
        }
        if(updateUserDTO.getStatus() != null){
            user.setStatus(updateUserDTO.getStatus());
        }
        return user;
    }
}
