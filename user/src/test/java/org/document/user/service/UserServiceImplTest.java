package org.document.user.service;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertSame;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.time.LocalDate;
import java.time.ZoneOffset;
import java.util.ArrayList;
import java.util.Date;
import java.util.Optional;

import org.document.common.enums.Gender;
import org.document.common.enums.UserStatus;
import org.document.common.model.Users;
import org.document.common.utils.QueryResults;
import org.document.user.dto.UpdateUserDTO;
import org.document.user.dto.UserDTO;
import org.document.user.repository.UserRepository;
import org.document.user.service.impl.UserServiceImpl;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit.jupiter.SpringExtension;

@ContextConfiguration(classes = {UserServiceImpl.class})
@ExtendWith(SpringExtension.class)
class UserServiceImplTest {
    @MockBean
    private UserRepository userRepository;

    @Autowired
    private UserServiceImpl userServiceImpl;

    /**
     * Method under test: {@link UserServiceImpl#addUser(String, UserDTO)}
     */
    @Test
    void testAddUser() throws Exception {
        // Arrange
        Users users = new Users();
        users.setAge(1L);
        users.setCreatedBy("Jan 1, 2020 8:00am GMT+0100");
        users.setCreationDate(Date.from(LocalDate.of(1970, 1, 1).atStartOfDay().atZone(ZoneOffset.UTC).toInstant()));
        users.setDob(LocalDate.of(1970, 1, 1).atStartOfDay().atZone(ZoneOffset.UTC).toInstant());
        users.setEmail("jane.doe@example.org");
        users.setFirstName("Jane");
        users.setGender(Gender.MALE);
        users.setLastModifiedBy("Jan 1, 2020 9:00am GMT+0100");
        users.setLastModifiedDate(Date.from(LocalDate.of(1970, 1, 1).atStartOfDay().atZone(ZoneOffset.UTC).toInstant()));
        users.setLastName("Doe");
        users.setMiddleName("Middle Name");
        users.setStatus(UserStatus.ACTIVE);
        users.setUserId(1L);
        users.setUserName("janedoe");
        users.setUserUuid("01234567-89AB-CDEF-FEDC-BA9876543210");
        when(userRepository.save(Mockito.<Users>any())).thenReturn(users);

        // Act
        Users actualAddUserResult = userServiceImpl.addUser("janedoe",
                new UserDTO("Jane", "Middle Name", "Doe", 1L,
                        Date.from(LocalDate.of(1970, 1, 1).atStartOfDay().atZone(ZoneOffset.UTC).toInstant()), Gender.MALE, "janedoe",
                        "jane.doe@example.org"));

        // Assert
        verify(userRepository).save(Mockito.<Users>any());
        assertSame(users, actualAddUserResult);
    }

    /**
     * Method under test: {@link UserServiceImpl#updateUser(String, UpdateUserDTO)}
     */
    @Test
    void testUpdateUser() throws Exception {
        // Arrange
        Users users = new Users();
        users.setAge(1L);
        users.setCreatedBy("Jan 1, 2020 8:00am GMT+0100");
        users.setCreationDate(Date.from(LocalDate.of(1970, 1, 1).atStartOfDay().atZone(ZoneOffset.UTC).toInstant()));
        users.setDob(LocalDate.of(1970, 1, 1).atStartOfDay().atZone(ZoneOffset.UTC).toInstant());
        users.setEmail("jane.doe@example.org");
        users.setFirstName("Jane");
        users.setGender(Gender.MALE);
        users.setLastModifiedBy("Jan 1, 2020 9:00am GMT+0100");
        users.setLastModifiedDate(Date.from(LocalDate.of(1970, 1, 1).atStartOfDay().atZone(ZoneOffset.UTC).toInstant()));
        users.setLastName("Doe");
        users.setMiddleName("Middle Name");
        users.setStatus(UserStatus.ACTIVE);
        users.setUserId(1L);
        users.setUserName("janedoe");
        users.setUserUuid("01234567-89AB-CDEF-FEDC-BA9876543210");
        Optional<Users> ofResult = Optional.of(users);

        Users users2 = new Users();
        users2.setAge(1L);
        users2.setCreatedBy("Jan 1, 2020 8:00am GMT+0100");
        users2.setCreationDate(Date.from(LocalDate.of(1970, 1, 1).atStartOfDay().atZone(ZoneOffset.UTC).toInstant()));
        users2.setDob(LocalDate.of(1970, 1, 1).atStartOfDay().atZone(ZoneOffset.UTC).toInstant());
        users2.setEmail("jane.doe@example.org");
        users2.setFirstName("Jane");
        users2.setGender(Gender.MALE);
        users2.setLastModifiedBy("Jan 1, 2020 9:00am GMT+0100");
        users2.setLastModifiedDate(Date.from(LocalDate.of(1970, 1, 1).atStartOfDay().atZone(ZoneOffset.UTC).toInstant()));
        users2.setLastName("Doe");
        users2.setMiddleName("Middle Name");
        users2.setStatus(UserStatus.ACTIVE);
        users2.setUserId(1L);
        users2.setUserName("janedoe");
        users2.setUserUuid("01234567-89AB-CDEF-FEDC-BA9876543210");
        when(userRepository.save(Mockito.<Users>any())).thenReturn(users2);
        when(userRepository.findByUserUuid(Mockito.<String>any())).thenReturn(ofResult);

        UpdateUserDTO updateUserDTO = new UpdateUserDTO();
        updateUserDTO.setAge(1L);
        updateUserDTO.setDob(Date.from(LocalDate.of(1970, 1, 1).atStartOfDay().atZone(ZoneOffset.UTC).toInstant()));
        updateUserDTO.setFirstName("Jane");
        updateUserDTO.setGender(Gender.MALE);
        updateUserDTO.setLastName("Doe");
        updateUserDTO.setMiddleName("Middle Name");
        updateUserDTO.setStatus(UserStatus.ACTIVE);
        updateUserDTO.setUserUuid("01234567-89AB-CDEF-FEDC-BA9876543210");

        // Act
        Users actualUpdateUserResult = userServiceImpl.updateUser("janedoe", updateUserDTO);

        // Assert
        verify(userRepository).findByUserUuid(Mockito.<String>any());
        verify(userRepository).save(Mockito.<Users>any());
        assertSame(users2, actualUpdateUserResult);
    }

    /**
     * Method under test: {@link UserServiceImpl#updateUser(String, UpdateUserDTO)}
     */
    @Test
    void testUpdateUser2() throws Exception {
        // Arrange
        Users users = new Users();
        users.setAge(1L);
        users.setCreatedBy("Jan 1, 2020 8:00am GMT+0100");
        users.setCreationDate(Date.from(LocalDate.of(1970, 1, 1).atStartOfDay().atZone(ZoneOffset.UTC).toInstant()));
        users.setDob(LocalDate.of(1970, 1, 1).atStartOfDay().atZone(ZoneOffset.UTC).toInstant());
        users.setEmail("jane.doe@example.org");
        users.setFirstName("Jane");
        users.setGender(Gender.MALE);
        users.setLastModifiedBy("Jan 1, 2020 9:00am GMT+0100");
        users.setLastModifiedDate(Date.from(LocalDate.of(1970, 1, 1).atStartOfDay().atZone(ZoneOffset.UTC).toInstant()));
        users.setLastName("Doe");
        users.setMiddleName("Middle Name");
        users.setStatus(UserStatus.ACTIVE);
        users.setUserId(1L);
        users.setUserName("janedoe");
        users.setUserUuid("01234567-89AB-CDEF-FEDC-BA9876543210");
        Optional<Users> ofResult = Optional.of(users);
        when(userRepository.save(Mockito.<Users>any())).thenThrow(new IllegalArgumentException("UpdateUserStart"));
        when(userRepository.findByUserUuid(Mockito.<String>any())).thenReturn(ofResult);

        UpdateUserDTO updateUserDTO = new UpdateUserDTO();
        updateUserDTO.setAge(1L);
        updateUserDTO.setDob(Date.from(LocalDate.of(1970, 1, 1).atStartOfDay().atZone(ZoneOffset.UTC).toInstant()));
        updateUserDTO.setFirstName("Jane");
        updateUserDTO.setGender(Gender.MALE);
        updateUserDTO.setLastName("Doe");
        updateUserDTO.setMiddleName("Middle Name");
        updateUserDTO.setStatus(UserStatus.ACTIVE);
        updateUserDTO.setUserUuid("01234567-89AB-CDEF-FEDC-BA9876543210");

        // Act and Assert
        assertThrows(IllegalArgumentException.class, () -> userServiceImpl.updateUser("janedoe", updateUserDTO));
        verify(userRepository).findByUserUuid(Mockito.<String>any());
        verify(userRepository).save(Mockito.<Users>any());
    }

    /**
     * Method under test: {@link UserServiceImpl#updateUser(String, UpdateUserDTO)}
     */
    @Test
    void testUpdateUser3() throws Exception {
        // Arrange
        Optional<Users> emptyResult = Optional.empty();
        when(userRepository.findByUserUuid(Mockito.<String>any())).thenReturn(emptyResult);

        UpdateUserDTO updateUserDTO = new UpdateUserDTO();
        updateUserDTO.setAge(1L);
        updateUserDTO.setDob(Date.from(LocalDate.of(1970, 1, 1).atStartOfDay().atZone(ZoneOffset.UTC).toInstant()));
        updateUserDTO.setFirstName("Jane");
        updateUserDTO.setGender(Gender.MALE);
        updateUserDTO.setLastName("Doe");
        updateUserDTO.setMiddleName("Middle Name");
        updateUserDTO.setStatus(UserStatus.ACTIVE);
        updateUserDTO.setUserUuid("01234567-89AB-CDEF-FEDC-BA9876543210");

        // Act and Assert
        assertThrows(IllegalArgumentException.class, () -> userServiceImpl.updateUser("janedoe", updateUserDTO));
        verify(userRepository).findByUserUuid(Mockito.<String>any());
    }

    /**
     * Method under test:
     * {@link UserServiceImpl#getUsers(String, int, int, Sort.Direction)}
     */
    @Test
    void testGetUsers() throws Exception {
        // Arrange
        Users users = new Users();
        users.setAge(1L);
        users.setCreatedBy("Jan 1, 2020 8:00am GMT+0100");
        users.setCreationDate(Date.from(LocalDate.of(1970, 1, 1).atStartOfDay().atZone(ZoneOffset.UTC).toInstant()));
        users.setDob(LocalDate.of(1970, 1, 1).atStartOfDay().atZone(ZoneOffset.UTC).toInstant());
        users.setEmail("jane.doe@example.org");
        users.setFirstName("Jane");
        users.setGender(Gender.MALE);
        users.setLastModifiedBy("Jan 1, 2020 9:00am GMT+0100");
        users.setLastModifiedDate(Date.from(LocalDate.of(1970, 1, 1).atStartOfDay().atZone(ZoneOffset.UTC).toInstant()));
        users.setLastName("Doe");
        users.setMiddleName("Middle Name");
        users.setStatus(UserStatus.ACTIVE);
        users.setUserId(1L);
        users.setUserName("janedoe");
        users.setUserUuid("01234567-89AB-CDEF-FEDC-BA9876543210");
        Optional<Users> ofResult = Optional.of(users);
        ArrayList<Users> content = new ArrayList<>();
        when(userRepository.findAll(Mockito.<Pageable>any())).thenReturn(new PageImpl<>(content));
        when(userRepository.findByUserName(Mockito.<String>any())).thenReturn(ofResult);

        // Act
        QueryResults<Users> actualUsers = userServiceImpl.getUsers("janedoe", 10, 3, Sort.Direction.ASC);

        // Assert
        verify(userRepository).findByUserName(Mockito.<String>any());
        verify(userRepository).findAll(Mockito.<Pageable>any());
        assertEquals(0L, actualUsers.countOfResults.longValue());
        assertEquals(content, actualUsers.results);
    }

    /**
     * Method under test:
     * {@link UserServiceImpl#getUsers(String, int, int, Sort.Direction)}
     */
    @Test
    void testGetUsers2() throws Exception {
        // Arrange
        Users users = new Users();
        users.setAge(1L);
        users.setCreatedBy("Jan 1, 2020 8:00am GMT+0100");
        users.setCreationDate(Date.from(LocalDate.of(1970, 1, 1).atStartOfDay().atZone(ZoneOffset.UTC).toInstant()));
        users.setDob(LocalDate.of(1970, 1, 1).atStartOfDay().atZone(ZoneOffset.UTC).toInstant());
        users.setEmail("jane.doe@example.org");
        users.setFirstName("Jane");
        users.setGender(Gender.MALE);
        users.setLastModifiedBy("Jan 1, 2020 9:00am GMT+0100");
        users.setLastModifiedDate(Date.from(LocalDate.of(1970, 1, 1).atStartOfDay().atZone(ZoneOffset.UTC).toInstant()));
        users.setLastName("Doe");
        users.setMiddleName("Middle Name");
        users.setStatus(UserStatus.ACTIVE);
        users.setUserId(1L);
        users.setUserName("janedoe");
        users.setUserUuid("01234567-89AB-CDEF-FEDC-BA9876543210");
        Optional<Users> ofResult = Optional.of(users);
        when(userRepository.findAll(Mockito.<Pageable>any())).thenThrow(new IllegalArgumentException("lastModifiedDate"));
        when(userRepository.findByUserName(Mockito.<String>any())).thenReturn(ofResult);

        // Act and Assert
        assertThrows(IllegalArgumentException.class, () -> userServiceImpl.getUsers("janedoe", 10, 3, Sort.Direction.ASC));
        verify(userRepository).findByUserName(Mockito.<String>any());
        verify(userRepository).findAll(Mockito.<Pageable>any());
    }

    /**
     * Method under test:
     * {@link UserServiceImpl#getUsers(String, int, int, Sort.Direction)}
     */
    @Test
    void testGetUsers3() throws Exception {
        // Arrange
        Optional<Users> emptyResult = Optional.empty();
        when(userRepository.findByUserName(Mockito.<String>any())).thenReturn(emptyResult);

        // Act and Assert
        assertThrows(IllegalArgumentException.class, () -> userServiceImpl.getUsers("janedoe", 10, 3, Sort.Direction.ASC));
        verify(userRepository).findByUserName(Mockito.<String>any());
    }

    /**
     * Method under test:
     * {@link UserServiceImpl#getUsers(String, int, int, Sort.Direction)}
     */
    @Test
    void testGetUsers4() throws Exception {
        // Arrange
        Users users = new Users();
        users.setAge(1L);
        users.setCreatedBy("Jan 1, 2020 8:00am GMT+0100");
        users.setCreationDate(Date.from(LocalDate.of(1970, 1, 1).atStartOfDay().atZone(ZoneOffset.UTC).toInstant()));
        users.setDob(LocalDate.of(1970, 1, 1).atStartOfDay().atZone(ZoneOffset.UTC).toInstant());
        users.setEmail("jane.doe@example.org");
        users.setFirstName("Jane");
        users.setGender(Gender.MALE);
        users.setLastModifiedBy("Jan 1, 2020 9:00am GMT+0100");
        users.setLastModifiedDate(Date.from(LocalDate.of(1970, 1, 1).atStartOfDay().atZone(ZoneOffset.UTC).toInstant()));
        users.setLastName("Doe");
        users.setMiddleName("Middle Name");
        users.setStatus(UserStatus.ACTIVE);
        users.setUserId(1L);
        users.setUserName("janedoe");
        users.setUserUuid("01234567-89AB-CDEF-FEDC-BA9876543210");
        Optional<Users> ofResult = Optional.of(users);
        ArrayList<Users> content = new ArrayList<>();
        when(userRepository.findAll(Mockito.<Pageable>any())).thenReturn(new PageImpl<>(content));
        when(userRepository.findByUserName(Mockito.<String>any())).thenReturn(ofResult);

        // Act
        QueryResults<Users> actualUsers = userServiceImpl.getUsers("janedoe", 10, 3, Sort.Direction.DESC);

        // Assert
        verify(userRepository).findByUserName(Mockito.<String>any());
        verify(userRepository).findAll(Mockito.<Pageable>any());
        assertEquals(0L, actualUsers.countOfResults.longValue());
        assertEquals(content, actualUsers.results);
    }

    /**
     * Method under test: {@link UserServiceImpl#deactivateUser(String, String)}
     */
    @Test
    void testDeactivateUser() throws Exception {
        // Arrange
        Users users = new Users();
        users.setAge(1L);
        users.setCreatedBy("Jan 1, 2020 8:00am GMT+0100");
        users.setCreationDate(Date.from(LocalDate.of(1970, 1, 1).atStartOfDay().atZone(ZoneOffset.UTC).toInstant()));
        users.setDob(LocalDate.of(1970, 1, 1).atStartOfDay().atZone(ZoneOffset.UTC).toInstant());
        users.setEmail("jane.doe@example.org");
        users.setFirstName("Jane");
        users.setGender(Gender.MALE);
        users.setLastModifiedBy("Jan 1, 2020 9:00am GMT+0100");
        users.setLastModifiedDate(Date.from(LocalDate.of(1970, 1, 1).atStartOfDay().atZone(ZoneOffset.UTC).toInstant()));
        users.setLastName("Doe");
        users.setMiddleName("Middle Name");
        users.setStatus(UserStatus.ACTIVE);
        users.setUserId(1L);
        users.setUserName("janedoe");
        users.setUserUuid("01234567-89AB-CDEF-FEDC-BA9876543210");
        Optional<Users> ofResult = Optional.of(users);

        Users users2 = new Users();
        users2.setAge(1L);
        users2.setCreatedBy("Jan 1, 2020 8:00am GMT+0100");
        users2.setCreationDate(Date.from(LocalDate.of(1970, 1, 1).atStartOfDay().atZone(ZoneOffset.UTC).toInstant()));
        users2.setDob(LocalDate.of(1970, 1, 1).atStartOfDay().atZone(ZoneOffset.UTC).toInstant());
        users2.setEmail("jane.doe@example.org");
        users2.setFirstName("Jane");
        users2.setGender(Gender.MALE);
        users2.setLastModifiedBy("Jan 1, 2020 9:00am GMT+0100");
        users2.setLastModifiedDate(Date.from(LocalDate.of(1970, 1, 1).atStartOfDay().atZone(ZoneOffset.UTC).toInstant()));
        users2.setLastName("Doe");
        users2.setMiddleName("Middle Name");
        users2.setStatus(UserStatus.ACTIVE);
        users2.setUserId(1L);
        users2.setUserName("janedoe");
        users2.setUserUuid("01234567-89AB-CDEF-FEDC-BA9876543210");
        Optional<Users> ofResult2 = Optional.of(users2);

        Users users3 = new Users();
        users3.setAge(1L);
        users3.setCreatedBy("Jan 1, 2020 8:00am GMT+0100");
        users3.setCreationDate(Date.from(LocalDate.of(1970, 1, 1).atStartOfDay().atZone(ZoneOffset.UTC).toInstant()));
        users3.setDob(LocalDate.of(1970, 1, 1).atStartOfDay().atZone(ZoneOffset.UTC).toInstant());
        users3.setEmail("jane.doe@example.org");
        users3.setFirstName("Jane");
        users3.setGender(Gender.MALE);
        users3.setLastModifiedBy("Jan 1, 2020 9:00am GMT+0100");
        users3.setLastModifiedDate(Date.from(LocalDate.of(1970, 1, 1).atStartOfDay().atZone(ZoneOffset.UTC).toInstant()));
        users3.setLastName("Doe");
        users3.setMiddleName("Middle Name");
        users3.setStatus(UserStatus.ACTIVE);
        users3.setUserId(1L);
        users3.setUserName("janedoe");
        users3.setUserUuid("01234567-89AB-CDEF-FEDC-BA9876543210");
        when(userRepository.save(Mockito.<Users>any())).thenReturn(users3);
        when(userRepository.findByUserUuid(Mockito.<String>any())).thenReturn(ofResult2);
        when(userRepository.findByUserName(Mockito.<String>any())).thenReturn(ofResult);

        // Act
        Users actualDeactivateUserResult = userServiceImpl.deactivateUser("janedoe",
                "01234567-89AB-CDEF-FEDC-BA9876543210");

        // Assert
        verify(userRepository).findByUserName(Mockito.<String>any());
        verify(userRepository).findByUserUuid(Mockito.<String>any());
        verify(userRepository).save(Mockito.<Users>any());
        assertSame(users3, actualDeactivateUserResult);
    }

    /**
     * Method under test: {@link UserServiceImpl#deactivateUser(String, String)}
     */
    @Test
    void testDeactivateUser2() throws Exception {
        // Arrange
        Users users = new Users();
        users.setAge(1L);
        users.setCreatedBy("Jan 1, 2020 8:00am GMT+0100");
        users.setCreationDate(Date.from(LocalDate.of(1970, 1, 1).atStartOfDay().atZone(ZoneOffset.UTC).toInstant()));
        users.setDob(LocalDate.of(1970, 1, 1).atStartOfDay().atZone(ZoneOffset.UTC).toInstant());
        users.setEmail("jane.doe@example.org");
        users.setFirstName("Jane");
        users.setGender(Gender.MALE);
        users.setLastModifiedBy("Jan 1, 2020 9:00am GMT+0100");
        users.setLastModifiedDate(Date.from(LocalDate.of(1970, 1, 1).atStartOfDay().atZone(ZoneOffset.UTC).toInstant()));
        users.setLastName("Doe");
        users.setMiddleName("Middle Name");
        users.setStatus(UserStatus.ACTIVE);
        users.setUserId(1L);
        users.setUserName("janedoe");
        users.setUserUuid("01234567-89AB-CDEF-FEDC-BA9876543210");
        Optional<Users> ofResult = Optional.of(users);

        Users users2 = new Users();
        users2.setAge(1L);
        users2.setCreatedBy("Jan 1, 2020 8:00am GMT+0100");
        users2.setCreationDate(Date.from(LocalDate.of(1970, 1, 1).atStartOfDay().atZone(ZoneOffset.UTC).toInstant()));
        users2.setDob(LocalDate.of(1970, 1, 1).atStartOfDay().atZone(ZoneOffset.UTC).toInstant());
        users2.setEmail("jane.doe@example.org");
        users2.setFirstName("Jane");
        users2.setGender(Gender.MALE);
        users2.setLastModifiedBy("Jan 1, 2020 9:00am GMT+0100");
        users2.setLastModifiedDate(Date.from(LocalDate.of(1970, 1, 1).atStartOfDay().atZone(ZoneOffset.UTC).toInstant()));
        users2.setLastName("Doe");
        users2.setMiddleName("Middle Name");
        users2.setStatus(UserStatus.ACTIVE);
        users2.setUserId(1L);
        users2.setUserName("janedoe");
        users2.setUserUuid("01234567-89AB-CDEF-FEDC-BA9876543210");
        Optional<Users> ofResult2 = Optional.of(users2);
        when(userRepository.save(Mockito.<Users>any())).thenThrow(new IllegalArgumentException("DeactivateUserStart"));
        when(userRepository.findByUserUuid(Mockito.<String>any())).thenReturn(ofResult2);
        when(userRepository.findByUserName(Mockito.<String>any())).thenReturn(ofResult);

        // Act and Assert
        assertThrows(IllegalArgumentException.class,
                () -> userServiceImpl.deactivateUser("janedoe", "01234567-89AB-CDEF-FEDC-BA9876543210"));
        verify(userRepository).findByUserName(Mockito.<String>any());
        verify(userRepository).findByUserUuid(Mockito.<String>any());
        verify(userRepository).save(Mockito.<Users>any());
    }

    /**
     * Method under test: {@link UserServiceImpl#deactivateUser(String, String)}
     */
    @Test
    void testDeactivateUser3() throws Exception {
        // Arrange
        Users users = new Users();
        users.setAge(1L);
        users.setCreatedBy("Jan 1, 2020 8:00am GMT+0100");
        users.setCreationDate(Date.from(LocalDate.of(1970, 1, 1).atStartOfDay().atZone(ZoneOffset.UTC).toInstant()));
        users.setDob(LocalDate.of(1970, 1, 1).atStartOfDay().atZone(ZoneOffset.UTC).toInstant());
        users.setEmail("jane.doe@example.org");
        users.setFirstName("Jane");
        users.setGender(Gender.MALE);
        users.setLastModifiedBy("Jan 1, 2020 9:00am GMT+0100");
        users.setLastModifiedDate(Date.from(LocalDate.of(1970, 1, 1).atStartOfDay().atZone(ZoneOffset.UTC).toInstant()));
        users.setLastName("Doe");
        users.setMiddleName("Middle Name");
        users.setStatus(UserStatus.ACTIVE);
        users.setUserId(1L);
        users.setUserName("janedoe");
        users.setUserUuid("01234567-89AB-CDEF-FEDC-BA9876543210");
        Optional<Users> ofResult = Optional.of(users);
        Optional<Users> emptyResult = Optional.empty();
        when(userRepository.findByUserUuid(Mockito.<String>any())).thenReturn(emptyResult);
        when(userRepository.findByUserName(Mockito.<String>any())).thenReturn(ofResult);

        // Act and Assert
        assertThrows(IllegalArgumentException.class,
                () -> userServiceImpl.deactivateUser("janedoe", "01234567-89AB-CDEF-FEDC-BA9876543210"));
        verify(userRepository).findByUserName(Mockito.<String>any());
        verify(userRepository).findByUserUuid(Mockito.<String>any());
    }
}
