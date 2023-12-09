package org.document.user.service;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertSame;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.Mockito.any;
import static org.mockito.Mockito.mock;
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
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
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
     * Method under test: {@link UserServiceImpl#addUser(UserDTO)}
     */
    @Test
    void testAddUser() throws Exception {
        Users users = new Users();
        users.setAge(1L);
        users.setCreatedBy("Jan 1, 2020 8:00am GMT+0100");
        users.setCreationDate(Date.from(LocalDate.of(1970, 1, 1).atStartOfDay().atZone(ZoneOffset.UTC).toInstant()));
        users.setDob(LocalDate.of(1970, 1, 1).atStartOfDay().atZone(ZoneOffset.UTC).toInstant());
        users.setFirstName("Jane");
        users.setGender(Gender.MALE);
        users.setLastModifiedBy("Jan 1, 2020 9:00am GMT+0100");
        users.setLastModifiedDate(Date.from(LocalDate.of(1970, 1, 1).atStartOfDay().atZone(ZoneOffset.UTC).toInstant()));
        users.setLastName("Doe");
        users.setMiddleName("Middle Name");
        users.setStatus(UserStatus.ACTIVE);
        users.setUserId(1L);
        users.setUserUuid("01234567-89AB-CDEF-FEDC-BA9876543210");
        when(userRepository.save((Users) any())).thenReturn(users);
        assertSame(users, userServiceImpl.addUser(new UserDTO("Jane", "Middle Name", "Doe", 1L,
                LocalDate.of(1970, 1, 1).atStartOfDay().atZone(ZoneOffset.UTC).toInstant(), Gender.MALE)));
        verify(userRepository).save((Users) any());
    }

    /**
     * Method under test: {@link UserServiceImpl#addUser(UserDTO)}
     */
    @Test
    @Disabled("TODO: Complete this test")
    void testAddUser2() throws Exception {
        // TODO: Complete this test.
        //   Reason: R013 No inputs found that don't throw a trivial exception.
        //   Diffblue Cover tried to run the arrange/act section, but the method under
        //   test threw
        //   java.lang.NullPointerException
        //       at org.document.user.utils.UserValidation.addUserValidation(UserValidation.java:9)
        //       at org.document.user.service.impl.UserServiceImpl.addUser(UserServiceImpl.java:41)
        //   See https://diff.blue/R013 to resolve this issue.

        Users users = new Users();
        users.setAge(1L);
        users.setCreatedBy("Jan 1, 2020 8:00am GMT+0100");
        users.setCreationDate(Date.from(LocalDate.of(1970, 1, 1).atStartOfDay().atZone(ZoneOffset.UTC).toInstant()));
        users.setDob(LocalDate.of(1970, 1, 1).atStartOfDay().atZone(ZoneOffset.UTC).toInstant());
        users.setFirstName("Jane");
        users.setGender(Gender.MALE);
        users.setLastModifiedBy("Jan 1, 2020 9:00am GMT+0100");
        users.setLastModifiedDate(Date.from(LocalDate.of(1970, 1, 1).atStartOfDay().atZone(ZoneOffset.UTC).toInstant()));
        users.setLastName("Doe");
        users.setMiddleName("Middle Name");
        users.setStatus(UserStatus.ACTIVE);
        users.setUserId(1L);
        users.setUserUuid("01234567-89AB-CDEF-FEDC-BA9876543210");
        when(userRepository.save((Users) any())).thenReturn(users);
        userServiceImpl.addUser(null);
    }

    /**
     * Method under test: {@link UserServiceImpl#addUser(UserDTO)}
     */
    @Test
    @Disabled("TODO: Complete this test")
    void testAddUser3() throws Exception {
        // TODO: Complete this test.
        //   Reason: R013 No inputs found that don't throw a trivial exception.
        //   Diffblue Cover tried to run the arrange/act section, but the method under
        //   test threw
        //   java.lang.IllegalArgumentException
        //       at org.document.user.utils.UserBuilder.addUserBuilder(UserBuilder.java:11)
        //       at org.document.user.service.impl.UserServiceImpl.addUser(UserServiceImpl.java:42)
        //   See https://diff.blue/R013 to resolve this issue.

        Users users = new Users();
        users.setAge(1L);
        users.setCreatedBy("Jan 1, 2020 8:00am GMT+0100");
        users.setCreationDate(Date.from(LocalDate.of(1970, 1, 1).atStartOfDay().atZone(ZoneOffset.UTC).toInstant()));
        users.setDob(LocalDate.of(1970, 1, 1).atStartOfDay().atZone(ZoneOffset.UTC).toInstant());
        users.setFirstName("Jane");
        users.setGender(Gender.MALE);
        users.setLastModifiedBy("Jan 1, 2020 9:00am GMT+0100");
        users.setLastModifiedDate(Date.from(LocalDate.of(1970, 1, 1).atStartOfDay().atZone(ZoneOffset.UTC).toInstant()));
        users.setLastName("Doe");
        users.setMiddleName("Middle Name");
        users.setStatus(UserStatus.ACTIVE);
        users.setUserId(1L);
        users.setUserUuid("01234567-89AB-CDEF-FEDC-BA9876543210");
        when(userRepository.save((Users) any())).thenReturn(users);
        UserDTO userDTO = mock(UserDTO.class);
        when(userDTO.getMiddleName()).thenThrow(new IllegalArgumentException());
        when(userDTO.getGender()).thenReturn(Gender.MALE);
        when(userDTO.getAge()).thenReturn(1L);
        when(userDTO.getDob()).thenReturn(LocalDate.of(1970, 1, 1).atStartOfDay().atZone(ZoneOffset.UTC).toInstant());
        when(userDTO.getLastName()).thenReturn("Doe");
        when(userDTO.getFirstName()).thenReturn("Jane");
        userServiceImpl.addUser(userDTO);
    }

    /**
     * Method under test: {@link UserServiceImpl#updateUser(UpdateUserDTO)}
     */
    @Test
    void testUpdateUser() throws Exception {
        Users users = new Users();
        users.setAge(1L);
        users.setCreatedBy("Jan 1, 2020 8:00am GMT+0100");
        users.setCreationDate(Date.from(LocalDate.of(1970, 1, 1).atStartOfDay().atZone(ZoneOffset.UTC).toInstant()));
        users.setDob(LocalDate.of(1970, 1, 1).atStartOfDay().atZone(ZoneOffset.UTC).toInstant());
        users.setFirstName("Jane");
        users.setGender(Gender.MALE);
        users.setLastModifiedBy("Jan 1, 2020 9:00am GMT+0100");
        users.setLastModifiedDate(Date.from(LocalDate.of(1970, 1, 1).atStartOfDay().atZone(ZoneOffset.UTC).toInstant()));
        users.setLastName("Doe");
        users.setMiddleName("Middle Name");
        users.setStatus(UserStatus.ACTIVE);
        users.setUserId(1L);
        users.setUserUuid("01234567-89AB-CDEF-FEDC-BA9876543210");
        Optional<Users> ofResult = Optional.of(users);

        Users users1 = new Users();
        users1.setAge(1L);
        users1.setCreatedBy("Jan 1, 2020 8:00am GMT+0100");
        users1.setCreationDate(Date.from(LocalDate.of(1970, 1, 1).atStartOfDay().atZone(ZoneOffset.UTC).toInstant()));
        users1.setDob(LocalDate.of(1970, 1, 1).atStartOfDay().atZone(ZoneOffset.UTC).toInstant());
        users1.setFirstName("Jane");
        users1.setGender(Gender.MALE);
        users1.setLastModifiedBy("Jan 1, 2020 9:00am GMT+0100");
        users1.setLastModifiedDate(Date.from(LocalDate.of(1970, 1, 1).atStartOfDay().atZone(ZoneOffset.UTC).toInstant()));
        users1.setLastName("Doe");
        users1.setMiddleName("Middle Name");
        users1.setStatus(UserStatus.ACTIVE);
        users1.setUserId(1L);
        users1.setUserUuid("01234567-89AB-CDEF-FEDC-BA9876543210");
        when(userRepository.save((Users) any())).thenReturn(users1);
        when(userRepository.findByUserUuid((String) any())).thenReturn(ofResult);

        UpdateUserDTO updateUserDTO = new UpdateUserDTO();
        updateUserDTO.setAge(1L);
        updateUserDTO.setDob(LocalDate.of(1970, 1, 1).atStartOfDay().atZone(ZoneOffset.UTC).toInstant());
        updateUserDTO.setFirstName("Jane");
        updateUserDTO.setGender(Gender.MALE);
        updateUserDTO.setLastName("Doe");
        updateUserDTO.setMiddleName("Middle Name");
        updateUserDTO.setStatus(UserStatus.ACTIVE);
        updateUserDTO.setUserUuid("01234567-89AB-CDEF-FEDC-BA9876543210");
        assertSame(users1, userServiceImpl.updateUser(updateUserDTO));
        verify(userRepository).save((Users) any());
        verify(userRepository).findByUserUuid((String) any());
    }

    /**
     * Method under test: {@link UserServiceImpl#updateUser(UpdateUserDTO)}
     */
    @Test
    void testUpdateUser2() throws Exception {
        Users users = new Users();
        users.setAge(1L);
        users.setCreatedBy("Jan 1, 2020 8:00am GMT+0100");
        users.setCreationDate(Date.from(LocalDate.of(1970, 1, 1).atStartOfDay().atZone(ZoneOffset.UTC).toInstant()));
        users.setDob(LocalDate.of(1970, 1, 1).atStartOfDay().atZone(ZoneOffset.UTC).toInstant());
        users.setFirstName("Jane");
        users.setGender(Gender.MALE);
        users.setLastModifiedBy("Jan 1, 2020 9:00am GMT+0100");
        users.setLastModifiedDate(Date.from(LocalDate.of(1970, 1, 1).atStartOfDay().atZone(ZoneOffset.UTC).toInstant()));
        users.setLastName("Doe");
        users.setMiddleName("Middle Name");
        users.setStatus(UserStatus.ACTIVE);
        users.setUserId(1L);
        users.setUserUuid("01234567-89AB-CDEF-FEDC-BA9876543210");
        Optional<Users> ofResult = Optional.of(users);
        when(userRepository.save((Users) any())).thenThrow(new IllegalArgumentException());
        when(userRepository.findByUserUuid((String) any())).thenReturn(ofResult);

        UpdateUserDTO updateUserDTO = new UpdateUserDTO();
        updateUserDTO.setAge(1L);
        updateUserDTO.setDob(LocalDate.of(1970, 1, 1).atStartOfDay().atZone(ZoneOffset.UTC).toInstant());
        updateUserDTO.setFirstName("Jane");
        updateUserDTO.setGender(Gender.MALE);
        updateUserDTO.setLastName("Doe");
        updateUserDTO.setMiddleName("Middle Name");
        updateUserDTO.setStatus(UserStatus.ACTIVE);
        updateUserDTO.setUserUuid("01234567-89AB-CDEF-FEDC-BA9876543210");
        assertThrows(IllegalArgumentException.class, () -> userServiceImpl.updateUser(updateUserDTO));
        verify(userRepository).save((Users) any());
        verify(userRepository).findByUserUuid((String) any());
    }

    /**
     * Method under test: {@link UserServiceImpl#updateUser(UpdateUserDTO)}
     */
    @Test
    void testUpdateUser3() throws Exception {
        Users users = new Users();
        users.setAge(1L);
        users.setCreatedBy("Jan 1, 2020 8:00am GMT+0100");
        users.setCreationDate(Date.from(LocalDate.of(1970, 1, 1).atStartOfDay().atZone(ZoneOffset.UTC).toInstant()));
        users.setDob(LocalDate.of(1970, 1, 1).atStartOfDay().atZone(ZoneOffset.UTC).toInstant());
        users.setFirstName("Jane");
        users.setGender(Gender.MALE);
        users.setLastModifiedBy("Jan 1, 2020 9:00am GMT+0100");
        users.setLastModifiedDate(Date.from(LocalDate.of(1970, 1, 1).atStartOfDay().atZone(ZoneOffset.UTC).toInstant()));
        users.setLastName("Doe");
        users.setMiddleName("Middle Name");
        users.setStatus(UserStatus.ACTIVE);
        users.setUserId(1L);
        users.setUserUuid("01234567-89AB-CDEF-FEDC-BA9876543210");
        when(userRepository.save((Users) any())).thenReturn(users);
        when(userRepository.findByUserUuid((String) any())).thenReturn(Optional.empty());

        UpdateUserDTO updateUserDTO = new UpdateUserDTO();
        updateUserDTO.setAge(1L);
        updateUserDTO.setDob(LocalDate.of(1970, 1, 1).atStartOfDay().atZone(ZoneOffset.UTC).toInstant());
        updateUserDTO.setFirstName("Jane");
        updateUserDTO.setGender(Gender.MALE);
        updateUserDTO.setLastName("Doe");
        updateUserDTO.setMiddleName("Middle Name");
        updateUserDTO.setStatus(UserStatus.ACTIVE);
        updateUserDTO.setUserUuid("01234567-89AB-CDEF-FEDC-BA9876543210");
        assertThrows(IllegalArgumentException.class, () -> userServiceImpl.updateUser(updateUserDTO));
        verify(userRepository).findByUserUuid((String) any());
    }

    /**
     * Method under test: {@link UserServiceImpl#getUsers(int, int, Sort.Direction)}
     */
    @Test
    void testGetUsers() throws Exception {
        when(userRepository.findAll((Pageable) any())).thenReturn(new PageImpl<>(new ArrayList<>()));
        QueryResults<Users> actualUsers = userServiceImpl.getUsers(10, 3, Sort.Direction.ASC);
        assertEquals(0L, actualUsers.countOfResults.longValue());
        assertTrue(actualUsers.results.isEmpty());
        verify(userRepository).findAll((Pageable) any());
    }

    /**
     * Method under test: {@link UserServiceImpl#getUsers(int, int, Sort.Direction)}
     */
    @Test
    @Disabled("TODO: Complete this test")
    void testGetUsers2() throws Exception {
        // TODO: Complete this test.
        //   Reason: R013 No inputs found that don't throw a trivial exception.
        //   Diffblue Cover tried to run the arrange/act section, but the method under
        //   test threw
        //   java.lang.NullPointerException
        //       at org.document.user.service.impl.UserServiceImpl.getUsers(UserServiceImpl.java:63)
        //   See https://diff.blue/R013 to resolve this issue.

        when(userRepository.findAll((Pageable) any())).thenReturn(null);
        userServiceImpl.getUsers(10, 3, Sort.Direction.ASC);
    }

    /**
     * Method under test: {@link UserServiceImpl#getUsers(int, int, Sort.Direction)}
     */
    @Test
    @Disabled("TODO: Complete this test")
    void testGetUsers3() throws Exception {
        // TODO: Complete this test.
        //   Reason: R013 No inputs found that don't throw a trivial exception.
        //   Diffblue Cover tried to run the arrange/act section, but the method under
        //   test threw
        //   java.lang.IllegalArgumentException: Page index must not be less than zero
        //       at org.document.user.service.impl.UserServiceImpl.getUsers(UserServiceImpl.java:61)
        //   See https://diff.blue/R013 to resolve this issue.

        when(userRepository.findAll((Pageable) any())).thenReturn(new PageImpl<>(new ArrayList<>()));
        userServiceImpl.getUsers(0, 3, Sort.Direction.ASC);
    }

    /**
     * Method under test: {@link UserServiceImpl#getUsers(int, int, Sort.Direction)}
     */
    @Test
    void testGetUsers4() throws Exception {
        when(userRepository.findAll((Pageable) any())).thenReturn(new PageImpl<>(new ArrayList<>()));
        QueryResults<Users> actualUsers = userServiceImpl.getUsers(10, 3, Sort.Direction.DESC);
        assertEquals(0L, actualUsers.countOfResults.longValue());
        assertTrue(actualUsers.results.isEmpty());
        verify(userRepository).findAll((Pageable) any());
    }

    /**
     * Method under test: {@link UserServiceImpl#getUsers(int, int, Sort.Direction)}
     */
    @Test
    void testGetUsers5() throws Exception {
        when(userRepository.findAll((Pageable) any())).thenThrow(new IllegalArgumentException());
        assertThrows(IllegalArgumentException.class, () -> userServiceImpl.getUsers(10, 3, Sort.Direction.ASC));
        verify(userRepository).findAll((Pageable) any());
    }

    /**
     * Method under test: {@link UserServiceImpl#deactivateUser(String)}
     */
    @Test
    void testDeactivateUser() throws Exception {
        Users users = new Users();
        users.setAge(1L);
        users.setCreatedBy("Jan 1, 2020 8:00am GMT+0100");
        users.setCreationDate(Date.from(LocalDate.of(1970, 1, 1).atStartOfDay().atZone(ZoneOffset.UTC).toInstant()));
        users.setDob(LocalDate.of(1970, 1, 1).atStartOfDay().atZone(ZoneOffset.UTC).toInstant());
        users.setFirstName("Jane");
        users.setGender(Gender.MALE);
        users.setLastModifiedBy("Jan 1, 2020 9:00am GMT+0100");
        users.setLastModifiedDate(Date.from(LocalDate.of(1970, 1, 1).atStartOfDay().atZone(ZoneOffset.UTC).toInstant()));
        users.setLastName("Doe");
        users.setMiddleName("Middle Name");
        users.setStatus(UserStatus.ACTIVE);
        users.setUserId(1L);
        users.setUserUuid("01234567-89AB-CDEF-FEDC-BA9876543210");
        Optional<Users> ofResult = Optional.of(users);

        Users users1 = new Users();
        users1.setAge(1L);
        users1.setCreatedBy("Jan 1, 2020 8:00am GMT+0100");
        users1.setCreationDate(Date.from(LocalDate.of(1970, 1, 1).atStartOfDay().atZone(ZoneOffset.UTC).toInstant()));
        users1.setDob(LocalDate.of(1970, 1, 1).atStartOfDay().atZone(ZoneOffset.UTC).toInstant());
        users1.setFirstName("Jane");
        users1.setGender(Gender.MALE);
        users1.setLastModifiedBy("Jan 1, 2020 9:00am GMT+0100");
        users1.setLastModifiedDate(Date.from(LocalDate.of(1970, 1, 1).atStartOfDay().atZone(ZoneOffset.UTC).toInstant()));
        users1.setLastName("Doe");
        users1.setMiddleName("Middle Name");
        users1.setStatus(UserStatus.ACTIVE);
        users1.setUserId(1L);
        users1.setUserUuid("01234567-89AB-CDEF-FEDC-BA9876543210");
        when(userRepository.save((Users) any())).thenReturn(users1);
        when(userRepository.findByUserUuid((String) any())).thenReturn(ofResult);
        assertSame(users1, userServiceImpl.deactivateUser("01234567-89AB-CDEF-FEDC-BA9876543210"));
        verify(userRepository).save((Users) any());
        verify(userRepository).findByUserUuid((String) any());
    }

    /**
     * Method under test: {@link UserServiceImpl#deactivateUser(String)}
     */
    @Test
    void testDeactivateUser2() throws Exception {
        Users users = new Users();
        users.setAge(1L);
        users.setCreatedBy("Jan 1, 2020 8:00am GMT+0100");
        users.setCreationDate(Date.from(LocalDate.of(1970, 1, 1).atStartOfDay().atZone(ZoneOffset.UTC).toInstant()));
        users.setDob(LocalDate.of(1970, 1, 1).atStartOfDay().atZone(ZoneOffset.UTC).toInstant());
        users.setFirstName("Jane");
        users.setGender(Gender.MALE);
        users.setLastModifiedBy("Jan 1, 2020 9:00am GMT+0100");
        users.setLastModifiedDate(Date.from(LocalDate.of(1970, 1, 1).atStartOfDay().atZone(ZoneOffset.UTC).toInstant()));
        users.setLastName("Doe");
        users.setMiddleName("Middle Name");
        users.setStatus(UserStatus.ACTIVE);
        users.setUserId(1L);
        users.setUserUuid("01234567-89AB-CDEF-FEDC-BA9876543210");
        Optional<Users> ofResult = Optional.of(users);
        when(userRepository.save((Users) any())).thenThrow(new IllegalArgumentException());
        when(userRepository.findByUserUuid((String) any())).thenReturn(ofResult);
        assertThrows(IllegalArgumentException.class,
                () -> userServiceImpl.deactivateUser("01234567-89AB-CDEF-FEDC-BA9876543210"));
        verify(userRepository).save((Users) any());
        verify(userRepository).findByUserUuid((String) any());
    }

    /**
     * Method under test: {@link UserServiceImpl#deactivateUser(String)}
     */
    @Test
    void testDeactivateUser3() throws Exception {
        Users users = new Users();
        users.setAge(1L);
        users.setCreatedBy("Jan 1, 2020 8:00am GMT+0100");
        users.setCreationDate(Date.from(LocalDate.of(1970, 1, 1).atStartOfDay().atZone(ZoneOffset.UTC).toInstant()));
        users.setDob(LocalDate.of(1970, 1, 1).atStartOfDay().atZone(ZoneOffset.UTC).toInstant());
        users.setFirstName("Jane");
        users.setGender(Gender.MALE);
        users.setLastModifiedBy("Jan 1, 2020 9:00am GMT+0100");
        users.setLastModifiedDate(Date.from(LocalDate.of(1970, 1, 1).atStartOfDay().atZone(ZoneOffset.UTC).toInstant()));
        users.setLastName("Doe");
        users.setMiddleName("Middle Name");
        users.setStatus(UserStatus.ACTIVE);
        users.setUserId(1L);
        users.setUserUuid("01234567-89AB-CDEF-FEDC-BA9876543210");
        when(userRepository.save((Users) any())).thenReturn(users);
        when(userRepository.findByUserUuid((String) any())).thenReturn(Optional.empty());
        assertThrows(IllegalArgumentException.class,
                () -> userServiceImpl.deactivateUser("01234567-89AB-CDEF-FEDC-BA9876543210"));
        verify(userRepository).findByUserUuid((String) any());
    }
}

