package org.document.user.utils;

import org.document.common.model.Users;
import org.document.user.dto.UpdateUserDTO;

import java.time.Instant;
import java.util.Date;

public class UserCondition {
    public static Users updateUserCondition(
            String userName,
            UpdateUserDTO updateUserDTO,
            Users user
    ) throws Exception {
        user.setLastModifiedBy(userName);
        user.setLastModifiedDate(Date.from(Instant.now()));
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
            user.setDob(updateUserDTO.getDob().toInstant());
        }
        if(updateUserDTO.getGender() != null){
            user.setGender(updateUserDTO.getGender());
        }
        if(updateUserDTO.getStatus() != null){
            user.setStatus(updateUserDTO.getStatus());
        }
        if(updateUserDTO.getEmail() != null){
            user.setEmail(updateUserDTO.getEmail());
        }
        return user;
    }
}
