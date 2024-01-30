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

import java.time.Instant;
import java.util.List;
import java.util.stream.Collectors;

import static org.document.common.utils.ErrorCodes.USR006;

@Service
public class UserServiceImpl implements UserService {

    private final Logger logger = LoggerFactory.getLogger(this.getClass());

    @Autowired
    private UserRepository userRepository;

    @Override
    public Users addUser(UserDTO userDTO) throws Exception {
        Instant createUserStart = Instant.now();
        UserValidation.addUserValidation(userDTO);
        Users addUser = UserBuilder.addUserBuilder(userDTO);
        Users saveUser = userRepository.save(addUser);
        logger.info("CreateUserStart" + createUserStart);
        return saveUser;
    }

    @Override
    public Users updateUser(UpdateUserDTO updateUserDTO) throws Exception {
        Instant updateUserStart = Instant.now();
        Users user = userRepository.findByUserUuid(updateUserDTO.getUserUuid())
                .orElseThrow(() -> new IllegalArgumentException(USR006(updateUserDTO.getUserUuid())));
        Users updateUser = UserCondition.updateUserCondition(updateUserDTO, user);
        Users saveUpdateUser = userRepository.save(updateUser);
        logger.info("UpdateUserStart" + updateUserStart);
        return saveUpdateUser;
    }

    @Override
    public QueryResults<Users> getUsers(int pageNumber, int pageSize, Sort.Direction sort) throws Exception {
        Pageable pageable = PageRequest.of(pageNumber - 1, pageSize, Sort.by(sort, "firstName"));
        Page<Users> users = userRepository.findAll(pageable);
        Long totalCount = users.getTotalElements();
        List<Users> result = users.stream().collect(Collectors.toList());
        return new QueryResults<>(result, totalCount);
    }

    @Override
    public Users deactivateUser(String userUuid) throws Exception {
        Instant deactivateUserStart = Instant.now();
        Users user = userRepository.findByUserUuid(userUuid)
                .orElseThrow(() -> new IllegalArgumentException(USR006(userUuid)));
        user.setStatus(UserStatus.DEACTIVATE);
        Users deactivateUser = userRepository.save(user);
        logger.info("DeactivateUserStart" + deactivateUserStart);
        return deactivateUser;
    }
}
