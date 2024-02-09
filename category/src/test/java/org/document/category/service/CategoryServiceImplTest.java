package org.document.category.service;

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

import org.document.category.dto.CategoryDTO;
import org.document.category.dto.UpdateCategoryDTO;
import org.document.category.repository.CategoryRepository;
import org.document.category.service.impl.CategoryServiceImpl;
import org.document.common.enums.CategoryStatus;
import org.document.common.enums.Gender;
import org.document.common.enums.UserStatus;
import org.document.common.model.Category;
import org.document.common.model.Users;
import org.document.common.utils.QueryResults;
import org.document.user.repository.UserRepository;
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

@ContextConfiguration(classes = {CategoryServiceImpl.class})
@ExtendWith(SpringExtension.class)
class CategoryServiceImplTest {
    @MockBean
    private CategoryRepository categoryRepository;

    @Autowired
    private CategoryServiceImpl categoryServiceImpl;

    @MockBean
    private UserRepository userRepository;

    /**
     * Method under test:
     * {@link CategoryServiceImpl#addCategory(String, CategoryDTO)}
     */
    @Test
    void testAddCategory() throws Exception {
        // Arrange
        Category category = new Category();
        category.setCategoryId(1L);
        category.setCategoryUuid("01234567-89AB-CDEF-FEDC-BA9876543210");
        category.setCode("Code");
        category.setCreatedBy("Jan 1, 2020 8:00am GMT+0100");
        category.setCreationDate(Date.from(LocalDate.of(1970, 1, 1).atStartOfDay().atZone(ZoneOffset.UTC).toInstant()));
        category.setDescription("The characteristics of someone or something");
        category.setImage("Image");
        category.setLastModifiedBy("Jan 1, 2020 9:00am GMT+0100");
        category.setLastModifiedDate(Date.from(LocalDate.of(1970, 1, 1).atStartOfDay().atZone(ZoneOffset.UTC).toInstant()));
        category.setStatus(CategoryStatus.ACTIVE);
        when(categoryRepository.save(Mockito.<Category>any())).thenReturn(category);

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
        when(userRepository.findByUserName(Mockito.<String>any())).thenReturn(ofResult);

        CategoryDTO categoryDTO = new CategoryDTO();
        categoryDTO.setCode("Code");
        categoryDTO.setDescription("The characteristics of someone or something");
        categoryDTO.setImage("Image");

        // Act
        Category actualAddCategoryResult = categoryServiceImpl.addCategory("janedoe", categoryDTO);

        // Assert
        verify(userRepository).findByUserName(Mockito.<String>any());
        verify(categoryRepository).save(Mockito.<Category>any());
        assertSame(category, actualAddCategoryResult);
    }

    /**
     * Method under test:
     * {@link CategoryServiceImpl#addCategory(String, CategoryDTO)}
     */
    @Test
    void testAddCategory2() throws Exception {
        // Arrange
        when(categoryRepository.save(Mockito.<Category>any()))
                .thenThrow(new IllegalArgumentException("CreateCategoryStart"));

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
        when(userRepository.findByUserName(Mockito.<String>any())).thenReturn(ofResult);

        CategoryDTO categoryDTO = new CategoryDTO();
        categoryDTO.setCode("Code");
        categoryDTO.setDescription("The characteristics of someone or something");
        categoryDTO.setImage("Image");

        // Act and Assert
        assertThrows(IllegalArgumentException.class, () -> categoryServiceImpl.addCategory("janedoe", categoryDTO));
        verify(userRepository).findByUserName(Mockito.<String>any());
        verify(categoryRepository).save(Mockito.<Category>any());
    }

    /**
     * Method under test:
     * {@link CategoryServiceImpl#addCategory(String, CategoryDTO)}
     */
    @Test
    void testAddCategory3() throws Exception {
        // Arrange
        Optional<Users> emptyResult = Optional.empty();
        when(userRepository.findByUserName(Mockito.<String>any())).thenReturn(emptyResult);

        CategoryDTO categoryDTO = new CategoryDTO();
        categoryDTO.setCode("Code");
        categoryDTO.setDescription("The characteristics of someone or something");
        categoryDTO.setImage("Image");

        // Act and Assert
        assertThrows(IllegalArgumentException.class, () -> categoryServiceImpl.addCategory("janedoe", categoryDTO));
        verify(userRepository).findByUserName(Mockito.<String>any());
    }

    /**
     * Method under test:
     * {@link CategoryServiceImpl#updateCategory(String, UpdateCategoryDTO)}
     */
    @Test
    void testUpdateCategory() throws Exception {
        // Arrange
        Category category = new Category();
        category.setCategoryId(1L);
        category.setCategoryUuid("01234567-89AB-CDEF-FEDC-BA9876543210");
        category.setCode("Code");
        category.setCreatedBy("Jan 1, 2020 8:00am GMT+0100");
        category.setCreationDate(Date.from(LocalDate.of(1970, 1, 1).atStartOfDay().atZone(ZoneOffset.UTC).toInstant()));
        category.setDescription("The characteristics of someone or something");
        category.setImage("Image");
        category.setLastModifiedBy("Jan 1, 2020 9:00am GMT+0100");
        category.setLastModifiedDate(Date.from(LocalDate.of(1970, 1, 1).atStartOfDay().atZone(ZoneOffset.UTC).toInstant()));
        category.setStatus(CategoryStatus.ACTIVE);
        Optional<Category> ofResult = Optional.of(category);

        Category category2 = new Category();
        category2.setCategoryId(1L);
        category2.setCategoryUuid("01234567-89AB-CDEF-FEDC-BA9876543210");
        category2.setCode("Code");
        category2.setCreatedBy("Jan 1, 2020 8:00am GMT+0100");
        category2.setCreationDate(Date.from(LocalDate.of(1970, 1, 1).atStartOfDay().atZone(ZoneOffset.UTC).toInstant()));
        category2.setDescription("The characteristics of someone or something");
        category2.setImage("Image");
        category2.setLastModifiedBy("Jan 1, 2020 9:00am GMT+0100");
        category2
                .setLastModifiedDate(Date.from(LocalDate.of(1970, 1, 1).atStartOfDay().atZone(ZoneOffset.UTC).toInstant()));
        category2.setStatus(CategoryStatus.ACTIVE);
        when(categoryRepository.save(Mockito.<Category>any())).thenReturn(category2);
        when(categoryRepository.findByCategoryUuid(Mockito.<String>any())).thenReturn(ofResult);

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
        Optional<Users> ofResult2 = Optional.of(users);
        when(userRepository.findByUserName(Mockito.<String>any())).thenReturn(ofResult2);

        UpdateCategoryDTO updateCategoryDTO = new UpdateCategoryDTO();
        updateCategoryDTO.setCategoryUuid("01234567-89AB-CDEF-FEDC-BA9876543210");
        updateCategoryDTO.setCode("Code");
        updateCategoryDTO.setDescription("The characteristics of someone or something");
        updateCategoryDTO.setImage("Image");
        updateCategoryDTO.setStatus(CategoryStatus.ACTIVE);

        // Act
        Category actualUpdateCategoryResult = categoryServiceImpl.updateCategory("janedoe", updateCategoryDTO);

        // Assert
        verify(categoryRepository).findByCategoryUuid(Mockito.<String>any());
        verify(userRepository).findByUserName(Mockito.<String>any());
        verify(categoryRepository).save(Mockito.<Category>any());
        assertSame(category2, actualUpdateCategoryResult);
    }

    /**
     * Method under test:
     * {@link CategoryServiceImpl#updateCategory(String, UpdateCategoryDTO)}
     */
    @Test
    void testUpdateCategory2() throws Exception {
        // Arrange
        Category category = new Category();
        category.setCategoryId(1L);
        category.setCategoryUuid("01234567-89AB-CDEF-FEDC-BA9876543210");
        category.setCode("Code");
        category.setCreatedBy("Jan 1, 2020 8:00am GMT+0100");
        category.setCreationDate(Date.from(LocalDate.of(1970, 1, 1).atStartOfDay().atZone(ZoneOffset.UTC).toInstant()));
        category.setDescription("The characteristics of someone or something");
        category.setImage("Image");
        category.setLastModifiedBy("Jan 1, 2020 9:00am GMT+0100");
        category.setLastModifiedDate(Date.from(LocalDate.of(1970, 1, 1).atStartOfDay().atZone(ZoneOffset.UTC).toInstant()));
        category.setStatus(CategoryStatus.ACTIVE);
        Optional<Category> ofResult = Optional.of(category);
        when(categoryRepository.save(Mockito.<Category>any()))
                .thenThrow(new IllegalArgumentException("UpdateCategoryStart"));
        when(categoryRepository.findByCategoryUuid(Mockito.<String>any())).thenReturn(ofResult);

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
        Optional<Users> ofResult2 = Optional.of(users);
        when(userRepository.findByUserName(Mockito.<String>any())).thenReturn(ofResult2);

        UpdateCategoryDTO updateCategoryDTO = new UpdateCategoryDTO();
        updateCategoryDTO.setCategoryUuid("01234567-89AB-CDEF-FEDC-BA9876543210");
        updateCategoryDTO.setCode("Code");
        updateCategoryDTO.setDescription("The characteristics of someone or something");
        updateCategoryDTO.setImage("Image");
        updateCategoryDTO.setStatus(CategoryStatus.ACTIVE);

        // Act and Assert
        assertThrows(IllegalArgumentException.class,
                () -> categoryServiceImpl.updateCategory("janedoe", updateCategoryDTO));
        verify(categoryRepository).findByCategoryUuid(Mockito.<String>any());
        verify(userRepository).findByUserName(Mockito.<String>any());
        verify(categoryRepository).save(Mockito.<Category>any());
    }

    /**
     * Method under test:
     * {@link CategoryServiceImpl#updateCategory(String, UpdateCategoryDTO)}
     */
    @Test
    void testUpdateCategory3() throws Exception {
        // Arrange
        Optional<Category> emptyResult = Optional.empty();
        when(categoryRepository.findByCategoryUuid(Mockito.<String>any())).thenReturn(emptyResult);

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
        when(userRepository.findByUserName(Mockito.<String>any())).thenReturn(ofResult);

        UpdateCategoryDTO updateCategoryDTO = new UpdateCategoryDTO();
        updateCategoryDTO.setCategoryUuid("01234567-89AB-CDEF-FEDC-BA9876543210");
        updateCategoryDTO.setCode("Code");
        updateCategoryDTO.setDescription("The characteristics of someone or something");
        updateCategoryDTO.setImage("Image");
        updateCategoryDTO.setStatus(CategoryStatus.ACTIVE);

        // Act and Assert
        assertThrows(IllegalArgumentException.class,
                () -> categoryServiceImpl.updateCategory("janedoe", updateCategoryDTO));
        verify(categoryRepository).findByCategoryUuid(Mockito.<String>any());
        verify(userRepository).findByUserName(Mockito.<String>any());
    }

    /**
     * Method under test:
     * {@link CategoryServiceImpl#getCategories(String, int, int, Sort.Direction)}
     */
    @Test
    void testGetCategories() throws Exception {
        // Arrange
        ArrayList<Category> content = new ArrayList<>();
        when(categoryRepository.findAll(Mockito.<Pageable>any())).thenReturn(new PageImpl<>(content));

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
        when(userRepository.findByUserName(Mockito.<String>any())).thenReturn(ofResult);

        // Act
        QueryResults<Category> actualCategories = categoryServiceImpl.getCategories("janedoe", 10, 3, Sort.Direction.ASC);

        // Assert
        verify(userRepository).findByUserName(Mockito.<String>any());
        verify(categoryRepository).findAll(Mockito.<Pageable>any());
        assertEquals(0L, actualCategories.countOfResults.longValue());
        assertEquals(content, actualCategories.results);
    }

    /**
     * Method under test:
     * {@link CategoryServiceImpl#getCategories(String, int, int, Sort.Direction)}
     */
    @Test
    void testGetCategories2() throws Exception {
        // Arrange
        when(categoryRepository.findAll(Mockito.<Pageable>any()))
                .thenThrow(new IllegalArgumentException("lastModifiedDate"));

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
        when(userRepository.findByUserName(Mockito.<String>any())).thenReturn(ofResult);

        // Act and Assert
        assertThrows(IllegalArgumentException.class,
                () -> categoryServiceImpl.getCategories("janedoe", 10, 3, Sort.Direction.ASC));
        verify(userRepository).findByUserName(Mockito.<String>any());
        verify(categoryRepository).findAll(Mockito.<Pageable>any());
    }

    /**
     * Method under test:
     * {@link CategoryServiceImpl#getCategories(String, int, int, Sort.Direction)}
     */
    @Test
    void testGetCategories3() throws Exception {
        // Arrange
        Optional<Users> emptyResult = Optional.empty();
        when(userRepository.findByUserName(Mockito.<String>any())).thenReturn(emptyResult);

        // Act and Assert
        assertThrows(IllegalArgumentException.class,
                () -> categoryServiceImpl.getCategories("janedoe", 10, 3, Sort.Direction.ASC));
        verify(userRepository).findByUserName(Mockito.<String>any());
    }

    /**
     * Method under test:
     * {@link CategoryServiceImpl#getCategories(String, int, int, Sort.Direction)}
     */
    @Test
    void testGetCategories4() throws Exception {
        // Arrange
        ArrayList<Category> content = new ArrayList<>();
        when(categoryRepository.findAll(Mockito.<Pageable>any())).thenReturn(new PageImpl<>(content));

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
        when(userRepository.findByUserName(Mockito.<String>any())).thenReturn(ofResult);

        // Act
        QueryResults<Category> actualCategories = categoryServiceImpl.getCategories("janedoe", 10, 3, Sort.Direction.DESC);

        // Assert
        verify(userRepository).findByUserName(Mockito.<String>any());
        verify(categoryRepository).findAll(Mockito.<Pageable>any());
        assertEquals(0L, actualCategories.countOfResults.longValue());
        assertEquals(content, actualCategories.results);
    }

    /**
     * Method under test:
     * {@link CategoryServiceImpl#deactivateCategory(String, String)}
     */
    @Test
    void testDeactivateCategory() throws Exception {
        // Arrange
        Category category = new Category();
        category.setCategoryId(1L);
        category.setCategoryUuid("01234567-89AB-CDEF-FEDC-BA9876543210");
        category.setCode("Code");
        category.setCreatedBy("Jan 1, 2020 8:00am GMT+0100");
        category.setCreationDate(Date.from(LocalDate.of(1970, 1, 1).atStartOfDay().atZone(ZoneOffset.UTC).toInstant()));
        category.setDescription("The characteristics of someone or something");
        category.setImage("Image");
        category.setLastModifiedBy("Jan 1, 2020 9:00am GMT+0100");
        category.setLastModifiedDate(Date.from(LocalDate.of(1970, 1, 1).atStartOfDay().atZone(ZoneOffset.UTC).toInstant()));
        category.setStatus(CategoryStatus.ACTIVE);
        Optional<Category> ofResult = Optional.of(category);

        Category category2 = new Category();
        category2.setCategoryId(1L);
        category2.setCategoryUuid("01234567-89AB-CDEF-FEDC-BA9876543210");
        category2.setCode("Code");
        category2.setCreatedBy("Jan 1, 2020 8:00am GMT+0100");
        category2.setCreationDate(Date.from(LocalDate.of(1970, 1, 1).atStartOfDay().atZone(ZoneOffset.UTC).toInstant()));
        category2.setDescription("The characteristics of someone or something");
        category2.setImage("Image");
        category2.setLastModifiedBy("Jan 1, 2020 9:00am GMT+0100");
        category2
                .setLastModifiedDate(Date.from(LocalDate.of(1970, 1, 1).atStartOfDay().atZone(ZoneOffset.UTC).toInstant()));
        category2.setStatus(CategoryStatus.ACTIVE);
        when(categoryRepository.save(Mockito.<Category>any())).thenReturn(category2);
        when(categoryRepository.findByCategoryUuid(Mockito.<String>any())).thenReturn(ofResult);

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
        Optional<Users> ofResult2 = Optional.of(users);
        when(userRepository.findByUserName(Mockito.<String>any())).thenReturn(ofResult2);

        // Act
        Category actualDeactivateCategoryResult = categoryServiceImpl.deactivateCategory("janedoe",
                "01234567-89AB-CDEF-FEDC-BA9876543210");

        // Assert
        verify(categoryRepository).findByCategoryUuid(Mockito.<String>any());
        verify(userRepository).findByUserName(Mockito.<String>any());
        verify(categoryRepository).save(Mockito.<Category>any());
        assertSame(category2, actualDeactivateCategoryResult);
    }

    /**
     * Method under test:
     * {@link CategoryServiceImpl#deactivateCategory(String, String)}
     */
    @Test
    void testDeactivateCategory2() throws Exception {
        // Arrange
        Category category = new Category();
        category.setCategoryId(1L);
        category.setCategoryUuid("01234567-89AB-CDEF-FEDC-BA9876543210");
        category.setCode("Code");
        category.setCreatedBy("Jan 1, 2020 8:00am GMT+0100");
        category.setCreationDate(Date.from(LocalDate.of(1970, 1, 1).atStartOfDay().atZone(ZoneOffset.UTC).toInstant()));
        category.setDescription("The characteristics of someone or something");
        category.setImage("Image");
        category.setLastModifiedBy("Jan 1, 2020 9:00am GMT+0100");
        category.setLastModifiedDate(Date.from(LocalDate.of(1970, 1, 1).atStartOfDay().atZone(ZoneOffset.UTC).toInstant()));
        category.setStatus(CategoryStatus.ACTIVE);
        Optional<Category> ofResult = Optional.of(category);
        when(categoryRepository.save(Mockito.<Category>any()))
                .thenThrow(new IllegalArgumentException("DeactivateCategoryStart"));
        when(categoryRepository.findByCategoryUuid(Mockito.<String>any())).thenReturn(ofResult);

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
        Optional<Users> ofResult2 = Optional.of(users);
        when(userRepository.findByUserName(Mockito.<String>any())).thenReturn(ofResult2);

        // Act and Assert
        assertThrows(IllegalArgumentException.class,
                () -> categoryServiceImpl.deactivateCategory("janedoe", "01234567-89AB-CDEF-FEDC-BA9876543210"));
        verify(categoryRepository).findByCategoryUuid(Mockito.<String>any());
        verify(userRepository).findByUserName(Mockito.<String>any());
        verify(categoryRepository).save(Mockito.<Category>any());
    }

    /**
     * Method under test:
     * {@link CategoryServiceImpl#deactivateCategory(String, String)}
     */
    @Test
    void testDeactivateCategory3() throws Exception {
        // Arrange
        Optional<Category> emptyResult = Optional.empty();
        when(categoryRepository.findByCategoryUuid(Mockito.<String>any())).thenReturn(emptyResult);

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
        when(userRepository.findByUserName(Mockito.<String>any())).thenReturn(ofResult);

        // Act and Assert
        assertThrows(IllegalArgumentException.class,
                () -> categoryServiceImpl.deactivateCategory("janedoe", "01234567-89AB-CDEF-FEDC-BA9876543210"));
        verify(categoryRepository).findByCategoryUuid(Mockito.<String>any());
        verify(userRepository).findByUserName(Mockito.<String>any());
    }
}
