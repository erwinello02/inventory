package org.document.user.controller;

import static org.mockito.Mockito.when;

import com.fasterxml.jackson.databind.ObjectMapper;

import java.time.LocalDate;
import java.time.ZoneOffset;
import java.util.Date;

import org.document.common.enums.Gender;
import org.document.common.enums.UserStatus;
import org.document.common.model.Users;
import org.document.user.dto.UpdateUserDTO;
import org.document.user.dto.UserDTO;
import org.document.user.service.UserService;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.data.domain.Sort;
import org.springframework.http.MediaType;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.ResultActions;
import org.springframework.test.web.servlet.request.MockHttpServletRequestBuilder;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

@ContextConfiguration(classes = {UserController.class})
@ExtendWith(SpringExtension.class)
class UserControllerTest {
    @Autowired
    private UserController userController;

    @MockBean
    private UserService userService;

    /**
     * Method under test: {@link UserController#addUser(String, UserDTO)}
     */
    @Test
    @Disabled("TODO: Complete this test")
    void testAddUser() throws Exception {
        // TODO: Complete this test.
        //   Reason: R013 No inputs found that don't throw a trivial exception.
        //   Diffblue Cover tried to run the arrange/act section, but the method under
        //   test threw
        //   com.fasterxml.jackson.databind.exc.InvalidDefinitionException: Java 8 date/time type `java.time.Instant` not supported by default: add Module "com.fasterxml.jackson.datatype:jackson-datatype-jsr310" to enable handling (through reference chain: org.document.user.dto.UserDTO["dob"])
        //       at com.fasterxml.jackson.databind.exc.InvalidDefinitionException.from(InvalidDefinitionException.java:77)
        //       at com.fasterxml.jackson.databind.SerializerProvider.reportBadDefinition(SerializerProvider.java:1300)
        //       at com.fasterxml.jackson.databind.ser.impl.UnsupportedTypeSerializer.serialize(UnsupportedTypeSerializer.java:35)
        //       at com.fasterxml.jackson.databind.ser.BeanPropertyWriter.serializeAsField(BeanPropertyWriter.java:728)
        //       at com.fasterxml.jackson.databind.ser.std.BeanSerializerBase.serializeFields(BeanSerializerBase.java:774)
        //       at com.fasterxml.jackson.databind.ser.BeanSerializer.serialize(BeanSerializer.java:178)
        //       at com.fasterxml.jackson.databind.ser.DefaultSerializerProvider._serialize(DefaultSerializerProvider.java:480)
        //       at com.fasterxml.jackson.databind.ser.DefaultSerializerProvider.serializeValue(DefaultSerializerProvider.java:319)
        //       at com.fasterxml.jackson.databind.ObjectMapper._writeValueAndClose(ObjectMapper.java:4568)
        //       at com.fasterxml.jackson.databind.ObjectMapper.writeValueAsString(ObjectMapper.java:3821)
        //   See https://diff.blue/R013 to resolve this issue.

        // Arrange
        MockHttpServletRequestBuilder contentTypeResult = MockMvcRequestBuilders.post("/user/add")
                .header("X-USER-NAME", "X-USER-NAME")
                .contentType(MediaType.APPLICATION_JSON);

        ObjectMapper objectMapper = new ObjectMapper();
        MockHttpServletRequestBuilder requestBuilder = contentTypeResult
                .content(objectMapper.writeValueAsString(new UserDTO("Jane", "Middle Name", "Doe", 1L,
                        Date.from(LocalDate.of(1970, 1, 1).atStartOfDay().atZone(ZoneOffset.UTC).toInstant()), Gender.MALE, "janedoe",
                        "jane.doe@example.org")));

        // Act
        MockMvcBuilders.standaloneSetup(userController).build().perform(requestBuilder);
    }

    /**
     * Method under test: {@link UserController#deactivateUser(String, String)}
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
        when(userService.deactivateUser(Mockito.<String>any(), Mockito.<String>any())).thenReturn(users);
        MockHttpServletRequestBuilder requestBuilder = MockMvcRequestBuilders
                .delete("/user/de-activate/{userUuid}", "01234567-89AB-CDEF-FEDC-BA9876543210")
                .header("X-USER-NAME", "X-USER-NAME");

        // Act
        ResultActions actualPerformResult = MockMvcBuilders.standaloneSetup(userController).build().perform(requestBuilder);

        // Assert
        actualPerformResult.andExpect(MockMvcResultMatchers.status().isCreated())
                .andExpect(MockMvcResultMatchers.content().contentType("application/json"))
                .andExpect(MockMvcResultMatchers.content()
                        .string(
                                "{\"createdBy\":\"Jan 1, 2020 8:00am GMT+0100\",\"creationDate\":0,\"lastModifiedBy\":\"Jan 1, 2020 9:00am"
                                        + " GMT+0100\",\"lastModifiedDate\":0,\"userId\":1,\"userUuid\":\"01234567-89AB-CDEF-FEDC-BA9876543210\",\"userName"
                                        + "\":\"janedoe\",\"email\":\"jane.doe@example.org\",\"firstName\":\"Jane\",\"middleName\":\"Middle Name\",\"lastName\":"
                                        + "\"Doe\",\"age\":1,\"dob\":0.0,\"gender\":\"MALE\",\"status\":\"ACTIVE\"}"));
    }

    /**
     * Method under test:
     * {@link UserController#getUsers(String, int, int, Sort.Direction)}
     */
    @Test
    @Disabled("TODO: Complete this test")
    void testGetUsers() throws Exception {
        // TODO: Complete this test.
        //   Diffblue AI was unable to find a test

        // Arrange
        // TODO: Populate arranged inputs
        MockHttpServletRequestBuilder getResult = MockMvcRequestBuilders.get("/user");
        MockHttpServletRequestBuilder paramResult = getResult.param("pageNumber", String.valueOf(1));
        MockHttpServletRequestBuilder paramResult2 = paramResult.param("pageSize", String.valueOf(1));
        MockHttpServletRequestBuilder requestBuilder = paramResult2.param("sort", String.valueOf(Sort.Direction.ASC))
                .header("X-USER-NAME", "X-USER-NAME");
        MockMvc buildResult = MockMvcBuilders.standaloneSetup(userController).build();

        // Act
        ResultActions actualPerformResult = buildResult.perform(requestBuilder);

        // Assert
        // TODO: Add assertions on result
    }

    /**
     * Method under test: {@link UserController#updateUser(String, UpdateUserDTO)}
     */
    @Test
    @Disabled("TODO: Complete this test")
    void testUpdateUser() throws Exception {
        // TODO: Complete this test.
        //   Reason: R013 No inputs found that don't throw a trivial exception.
        //   Diffblue Cover tried to run the arrange/act section, but the method under
        //   test threw
        //   com.fasterxml.jackson.databind.exc.InvalidDefinitionException: Java 8 date/time type `java.time.Instant` not supported by default: add Module "com.fasterxml.jackson.datatype:jackson-datatype-jsr310" to enable handling (through reference chain: org.document.user.dto.UpdateUserDTO["dob"])
        //       at com.fasterxml.jackson.databind.exc.InvalidDefinitionException.from(InvalidDefinitionException.java:77)
        //       at com.fasterxml.jackson.databind.SerializerProvider.reportBadDefinition(SerializerProvider.java:1300)
        //       at com.fasterxml.jackson.databind.ser.impl.UnsupportedTypeSerializer.serialize(UnsupportedTypeSerializer.java:35)
        //       at com.fasterxml.jackson.databind.ser.BeanPropertyWriter.serializeAsField(BeanPropertyWriter.java:728)
        //       at com.fasterxml.jackson.databind.ser.std.BeanSerializerBase.serializeFields(BeanSerializerBase.java:774)
        //       at com.fasterxml.jackson.databind.ser.BeanSerializer.serialize(BeanSerializer.java:178)
        //       at com.fasterxml.jackson.databind.ser.DefaultSerializerProvider._serialize(DefaultSerializerProvider.java:480)
        //       at com.fasterxml.jackson.databind.ser.DefaultSerializerProvider.serializeValue(DefaultSerializerProvider.java:319)
        //       at com.fasterxml.jackson.databind.ObjectMapper._writeValueAndClose(ObjectMapper.java:4568)
        //       at com.fasterxml.jackson.databind.ObjectMapper.writeValueAsString(ObjectMapper.java:3821)
        //   See https://diff.blue/R013 to resolve this issue.

        // Arrange
        UpdateUserDTO updateUserDTO = new UpdateUserDTO();
        updateUserDTO.setAge(1L);
        updateUserDTO.setDob(Date.from(LocalDate.of(1970, 1, 1).atStartOfDay().atZone(ZoneOffset.UTC).toInstant()));
        updateUserDTO.setFirstName("Jane");
        updateUserDTO.setGender(Gender.MALE);
        updateUserDTO.setLastName("Doe");
        updateUserDTO.setMiddleName("Middle Name");
        updateUserDTO.setStatus(UserStatus.ACTIVE);
        updateUserDTO.setUserUuid("01234567-89AB-CDEF-FEDC-BA9876543210");
        String content = (new ObjectMapper()).writeValueAsString(updateUserDTO);
        MockHttpServletRequestBuilder requestBuilder = MockMvcRequestBuilders.patch("/user/update")
                .header("X-USER-NAME", "X-USER-NAME")
                .contentType(MediaType.APPLICATION_JSON)
                .content(content);

        // Act
        MockMvcBuilders.standaloneSetup(userController).build().perform(requestBuilder);
    }
}
