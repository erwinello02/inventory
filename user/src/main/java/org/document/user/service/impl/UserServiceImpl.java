package org.document.user.service.impl;

import org.document.common.enums.UserStatus;
import org.document.common.model.Users;
import org.document.common.utils.QueryResults;
import org.document.user.dto.UpdateUserDTO;
import org.document.user.dto.UserDTO;
import org.document.user.repository.UserRepository;
import org.document.user.service.UserService;
import org.document.user.utils.UserBuilder;
import org.document.user.utils.UserCondition;
import org.document.user.utils.UserValidation;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.sql.Date;
import java.time.Instant;
import java.util.List;
import java.util.stream.Collectors;

import static org.document.common.utils.ErrorCodes.USR006;
import static org.document.common.utils.ErrorCodes.USR009;

@Service
public class UserServiceImpl implements UserService {

    private final Logger logger = LoggerFactory.getLogger(this.getClass());

    @Autowired
    private UserRepository userRepository;

    @Override
    public Users addUser(String userName, UserDTO userDTO) throws Exception {
        Instant createUserStart = Instant.now();
        UserValidation.addUserValidation(userDTO);
        Users addUser = UserBuilder.addUserBuilder(userDTO);
        Users saveUser = userRepository.save(addUser);
        logger.info("CreateUserStart" + createUserStart);
        return saveUser;
    }

    @Override
    public Users updateUser(String userName, UpdateUserDTO updateUserDTO) throws Exception {
        Instant updateUserStart = Instant.now();
        Users user = userRepository.findByUserUuid(updateUserDTO.getUserUuid())
                .orElseThrow(() -> new IllegalArgumentException(USR006(updateUserDTO.getUserUuid())));
        Users updateUser = UserCondition.updateUserCondition(userName, updateUserDTO, user);
        Users saveUpdateUser = userRepository.save(updateUser);
        logger.info("UpdateUserStart" + updateUserStart);
        return saveUpdateUser;
    }

    @Override
    public QueryResults<Users> getUsers(String userName, int pageNumber, int pageSize, Sort.Direction sort) throws Exception {
        Pageable pageable = PageRequest.of(pageNumber - 1, pageSize, Sort.by(sort, "lastModifiedDate"));
        userRepository.findByUserName(userName).orElseThrow(() -> new IllegalArgumentException(USR009(userName)));
        Page<Users> users = userRepository.findAll(pageable);
        Long totalCount = users.getTotalElements();
        List<Users> result = users.stream().collect(Collectors.toList());
        return new QueryResults<>(result, totalCount);
    }

    @Override
    public Users deactivateUser(String userName, String userUuid) throws Exception {
        Instant deactivateUserStart = Instant.now();
        userRepository.findByUserName(userName).orElseThrow(() -> new IllegalArgumentException(USR009(userName)));
        Users user = userRepository.findByUserUuid(userUuid)
                .orElseThrow(() -> new IllegalArgumentException(USR006(userUuid)));
        user.setStatus(UserStatus.DEACTIVATE);
        user.setLastModifiedDate(Date.from(Instant.now()));
        user.setLastModifiedBy(userName);
        Users deactivateUser = userRepository.save(user);
        logger.info("DeactivateUserStart" + deactivateUserStart);
        return deactivateUser;
    }
}
