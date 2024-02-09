package org.document.user.service;

import org.document.common.model.Users;
import org.document.common.utils.QueryResults;
import org.document.user.dto.UpdateUserDTO;
import org.document.user.dto.UserDTO;
import org.springframework.data.domain.Sort;

public interface UserService {
    Users addUser(String userName, UserDTO userDTO) throws Exception;
    Users updateUser(String userName, UpdateUserDTO updateUserDTO) throws Exception;
    QueryResults<Users> getUsers(String userName, int pageNumber, int pageSize, Sort.Direction sort) throws Exception;
    Users deactivateUser(String userName, String userUuid) throws Exception;
}
