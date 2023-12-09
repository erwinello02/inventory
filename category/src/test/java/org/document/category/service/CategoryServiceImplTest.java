package org.document.category.service;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertSame;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.Mockito.any;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.mock;
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
import org.document.common.model.Category;
import org.document.common.utils.QueryResults;
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

@ContextConfiguration(classes = {CategoryServiceImpl.class})
@ExtendWith(SpringExtension.class)
class CategoryServiceImplTest {
    @MockBean
    private CategoryRepository categoryRepository;

    @Autowired
    private CategoryServiceImpl categoryServiceImpl;

    /**
     * Method under test: {@link CategoryServiceImpl#addCategory(CategoryDTO)}
     */
    @Test
    void testAddCategory() throws Exception {
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
        when(categoryRepository.save((Category) any())).thenReturn(category);

        CategoryDTO categoryDTO = new CategoryDTO();
        categoryDTO.setCode("Code");
        categoryDTO.setDescription("The characteristics of someone or something");
        categoryDTO.setImage("Image");
        assertSame(category, categoryServiceImpl.addCategory(categoryDTO));
        verify(categoryRepository).save((Category) any());
    }

    /**
     * Method under test: {@link CategoryServiceImpl#addCategory(CategoryDTO)}
     */
    @Test
    @Disabled("TODO: Complete this test")
    void testAddCategory2() throws Exception {
        // TODO: Complete this test.
        //   Reason: R013 No inputs found that don't throw a trivial exception.
        //   Diffblue Cover tried to run the arrange/act section, but the method under
        //   test threw
        //   java.lang.IllegalArgumentException
        //       at org.document.category.utils.CategoryValidation.addCategoryValidation(CategoryValidation.java:13)
        //       at org.document.category.service.impl.CategoryServiceImpl.addCategory(CategoryServiceImpl.java:39)
        //   See https://diff.blue/R013 to resolve this issue.

        Category category = new Category();
        category.setCategoryId(1L);
        category.setCategoryUuid("01234567-89AB-CDEF-FEDC-BA9876543210");
        category.setCode("Code");
        category.setCreatedBy("Jan 1, 2020 8:00am GMT+0100");
        category.setCreationDate(Date.from(LocalDate.of(1970, 1, 1).atStartOfDay().atZone(ZoneOffset.UTC).toInstant()));
        category.setDescription("The characteristics of someone or something");
        category.setImage("Image");
        category.setLastModifiedBy("Jan 1, 2020 9:00am GMT+0100");
        category
                .setLastModifiedDate(Date.from(LocalDate.of(1970, 1, 1).atStartOfDay().atZone(ZoneOffset.UTC).toInstant()));
        category.setStatus(CategoryStatus.ACTIVE);
        when(categoryRepository.save((Category) any())).thenReturn(category);
        CategoryDTO categoryDTO = mock(CategoryDTO.class);
        when(categoryDTO.getImage()).thenThrow(new IllegalArgumentException());
        when(categoryDTO.getDescription()).thenReturn("The characteristics of someone or something");
        when(categoryDTO.getCode()).thenReturn("Code");
        doNothing().when(categoryDTO).setCode((String) any());
        doNothing().when(categoryDTO).setDescription((String) any());
        doNothing().when(categoryDTO).setImage((String) any());
        categoryDTO.setCode("Code");
        categoryDTO.setDescription("The characteristics of someone or something");
        categoryDTO.setImage("Image");
        categoryServiceImpl.addCategory(categoryDTO);
    }

    /**
     * Method under test: {@link CategoryServiceImpl#updateCategory(UpdateCategoryDTO)}
     */
    @Test
    void testUpdateCategory() throws Exception {
        Category category = new Category();
        category.setCategoryId(1L);
        category.setCategoryUuid("01234567-89AB-CDEF-FEDC-BA9876543210");
        category.setCode("Code");
        category.setCreatedBy("Jan 1, 2020 8:00am GMT+0100");
        category.setCreationDate(Date.from(LocalDate.of(1970, 1, 1).atStartOfDay().atZone(ZoneOffset.UTC).toInstant()));
        category.setDescription("The characteristics of someone or something");
        category.setImage("Image");
        category.setLastModifiedBy("Jan 1, 2020 9:00am GMT+0100");
        category
                .setLastModifiedDate(Date.from(LocalDate.of(1970, 1, 1).atStartOfDay().atZone(ZoneOffset.UTC).toInstant()));
        category.setStatus(CategoryStatus.ACTIVE);
        Optional<Category> ofResult = Optional.of(category);

        Category category1 = new Category();
        category1.setCategoryId(1L);
        category1.setCategoryUuid("01234567-89AB-CDEF-FEDC-BA9876543210");
        category1.setCode("Code");
        category1.setCreatedBy("Jan 1, 2020 8:00am GMT+0100");
        category1.setCreationDate(Date.from(LocalDate.of(1970, 1, 1).atStartOfDay().atZone(ZoneOffset.UTC).toInstant()));
        category1.setDescription("The characteristics of someone or something");
        category1.setImage("Image");
        category1.setLastModifiedBy("Jan 1, 2020 9:00am GMT+0100");
        category1
                .setLastModifiedDate(Date.from(LocalDate.of(1970, 1, 1).atStartOfDay().atZone(ZoneOffset.UTC).toInstant()));
        category1.setStatus(CategoryStatus.ACTIVE);
        when(categoryRepository.save((Category) any())).thenReturn(category1);
        when(categoryRepository.findByCategoryUuid((String) any())).thenReturn(ofResult);

        UpdateCategoryDTO updateCategoryDTO = new UpdateCategoryDTO();
        updateCategoryDTO.setCategoryUuid("01234567-89AB-CDEF-FEDC-BA9876543210");
        updateCategoryDTO.setCode("Code");
        updateCategoryDTO.setDescription("The characteristics of someone or something");
        updateCategoryDTO.setImage("Image");
        updateCategoryDTO.setStatus(CategoryStatus.ACTIVE);
        assertSame(category1, categoryServiceImpl.updateCategory(updateCategoryDTO));
        verify(categoryRepository).save((Category) any());
        verify(categoryRepository).findByCategoryUuid((String) any());
    }

    /**
     * Method under test: {@link CategoryServiceImpl#updateCategory(UpdateCategoryDTO)}
     */
    @Test
    void testUpdateCategory2() throws Exception {
        Category category = new Category();
        category.setCategoryId(1L);
        category.setCategoryUuid("01234567-89AB-CDEF-FEDC-BA9876543210");
        category.setCode("Code");
        category.setCreatedBy("Jan 1, 2020 8:00am GMT+0100");
        category.setCreationDate(Date.from(LocalDate.of(1970, 1, 1).atStartOfDay().atZone(ZoneOffset.UTC).toInstant()));
        category.setDescription("The characteristics of someone or something");
        category.setImage("Image");
        category.setLastModifiedBy("Jan 1, 2020 9:00am GMT+0100");
        category
                .setLastModifiedDate(Date.from(LocalDate.of(1970, 1, 1).atStartOfDay().atZone(ZoneOffset.UTC).toInstant()));
        category.setStatus(CategoryStatus.ACTIVE);
        Optional<Category> ofResult = Optional.of(category);
        when(categoryRepository.save((Category) any())).thenThrow(new IllegalArgumentException());
        when(categoryRepository.findByCategoryUuid((String) any())).thenReturn(ofResult);

        UpdateCategoryDTO updateCategoryDTO = new UpdateCategoryDTO();
        updateCategoryDTO.setCategoryUuid("01234567-89AB-CDEF-FEDC-BA9876543210");
        updateCategoryDTO.setCode("Code");
        updateCategoryDTO.setDescription("The characteristics of someone or something");
        updateCategoryDTO.setImage("Image");
        updateCategoryDTO.setStatus(CategoryStatus.ACTIVE);
        assertThrows(IllegalArgumentException.class, () -> categoryServiceImpl.updateCategory(updateCategoryDTO));
        verify(categoryRepository).save((Category) any());
        verify(categoryRepository).findByCategoryUuid((String) any());
    }

    /**
     * Method under test: {@link CategoryServiceImpl#updateCategory(UpdateCategoryDTO)}
     */
    @Test
    void testUpdateCategory3() throws Exception {
        Category category = new Category();
        category.setCategoryId(1L);
        category.setCategoryUuid("01234567-89AB-CDEF-FEDC-BA9876543210");
        category.setCode("Code");
        category.setCreatedBy("Jan 1, 2020 8:00am GMT+0100");
        category.setCreationDate(Date.from(LocalDate.of(1970, 1, 1).atStartOfDay().atZone(ZoneOffset.UTC).toInstant()));
        category.setDescription("The characteristics of someone or something");
        category.setImage("Image");
        category.setLastModifiedBy("Jan 1, 2020 9:00am GMT+0100");
        category
                .setLastModifiedDate(Date.from(LocalDate.of(1970, 1, 1).atStartOfDay().atZone(ZoneOffset.UTC).toInstant()));
        category.setStatus(CategoryStatus.ACTIVE);
        when(categoryRepository.save((Category) any())).thenReturn(category);
        when(categoryRepository.findByCategoryUuid((String) any())).thenReturn(Optional.empty());

        UpdateCategoryDTO updateCategoryDTO = new UpdateCategoryDTO();
        updateCategoryDTO.setCategoryUuid("01234567-89AB-CDEF-FEDC-BA9876543210");
        updateCategoryDTO.setCode("Code");
        updateCategoryDTO.setDescription("The characteristics of someone or something");
        updateCategoryDTO.setImage("Image");
        updateCategoryDTO.setStatus(CategoryStatus.ACTIVE);
        assertThrows(IllegalArgumentException.class, () -> categoryServiceImpl.updateCategory(updateCategoryDTO));
        verify(categoryRepository).findByCategoryUuid((String) any());
    }

    /**
     * Method under test: {@link CategoryServiceImpl#getCategories(int, int, Sort.Direction)}
     */
    @Test
    void testGetCategories() throws Exception {
        when(categoryRepository.findAll((Pageable) any())).thenReturn(new PageImpl<>(new ArrayList<>()));
        QueryResults<Category> actualCategories = categoryServiceImpl.getCategories(10, 3, Sort.Direction.ASC);
        assertEquals(0L, actualCategories.countOfResults.longValue());
        assertTrue(actualCategories.results.isEmpty());
        verify(categoryRepository).findAll((Pageable) any());
    }

    /**
     * Method under test: {@link CategoryServiceImpl#getCategories(int, int, Sort.Direction)}
     */
    @Test
    @Disabled("TODO: Complete this test")
    void testGetCategories2() throws Exception {
        // TODO: Complete this test.
        //   Reason: R013 No inputs found that don't throw a trivial exception.
        //   Diffblue Cover tried to run the arrange/act section, but the method under
        //   test threw
        //   java.lang.NullPointerException
        //       at org.document.category.service.impl.CategoryServiceImpl.getCategories(CategoryServiceImpl.java:61)
        //   See https://diff.blue/R013 to resolve this issue.

        when(categoryRepository.findAll((Pageable) any())).thenReturn(null);
        categoryServiceImpl.getCategories(10, 3, Sort.Direction.ASC);
    }

    /**
     * Method under test: {@link CategoryServiceImpl#getCategories(int, int, Sort.Direction)}
     */
    @Test
    @Disabled("TODO: Complete this test")
    void testGetCategories3() throws Exception {
        // TODO: Complete this test.
        //   Reason: R013 No inputs found that don't throw a trivial exception.
        //   Diffblue Cover tried to run the arrange/act section, but the method under
        //   test threw
        //   java.lang.IllegalArgumentException: Page index must not be less than zero
        //       at org.document.category.service.impl.CategoryServiceImpl.getCategories(CategoryServiceImpl.java:59)
        //   See https://diff.blue/R013 to resolve this issue.

        when(categoryRepository.findAll((Pageable) any())).thenReturn(new PageImpl<>(new ArrayList<>()));
        categoryServiceImpl.getCategories(0, 3, Sort.Direction.ASC);
    }

    /**
     * Method under test: {@link CategoryServiceImpl#getCategories(int, int, Sort.Direction)}
     */
    @Test
    void testGetCategories4() throws Exception {
        when(categoryRepository.findAll((Pageable) any())).thenReturn(new PageImpl<>(new ArrayList<>()));
        QueryResults<Category> actualCategories = categoryServiceImpl.getCategories(10, 3, Sort.Direction.DESC);
        assertEquals(0L, actualCategories.countOfResults.longValue());
        assertTrue(actualCategories.results.isEmpty());
        verify(categoryRepository).findAll((Pageable) any());
    }

    /**
     * Method under test: {@link CategoryServiceImpl#getCategories(int, int, Sort.Direction)}
     */
    @Test
    void testGetCategories5() throws Exception {
        when(categoryRepository.findAll((Pageable) any())).thenThrow(new IllegalArgumentException());
        assertThrows(IllegalArgumentException.class, () -> categoryServiceImpl.getCategories(10, 3, Sort.Direction.ASC));
        verify(categoryRepository).findAll((Pageable) any());
    }

    /**
     * Method under test: {@link CategoryServiceImpl#deactivateCategory(String)}
     */
    @Test
    void testDeactivateCategory() throws Exception {
        Category category = new Category();
        category.setCategoryId(1L);
        category.setCategoryUuid("01234567-89AB-CDEF-FEDC-BA9876543210");
        category.setCode("Code");
        category.setCreatedBy("Jan 1, 2020 8:00am GMT+0100");
        category.setCreationDate(Date.from(LocalDate.of(1970, 1, 1).atStartOfDay().atZone(ZoneOffset.UTC).toInstant()));
        category.setDescription("The characteristics of someone or something");
        category.setImage("Image");
        category.setLastModifiedBy("Jan 1, 2020 9:00am GMT+0100");
        category
                .setLastModifiedDate(Date.from(LocalDate.of(1970, 1, 1).atStartOfDay().atZone(ZoneOffset.UTC).toInstant()));
        category.setStatus(CategoryStatus.ACTIVE);
        Optional<Category> ofResult = Optional.of(category);

        Category category1 = new Category();
        category1.setCategoryId(1L);
        category1.setCategoryUuid("01234567-89AB-CDEF-FEDC-BA9876543210");
        category1.setCode("Code");
        category1.setCreatedBy("Jan 1, 2020 8:00am GMT+0100");
        category1.setCreationDate(Date.from(LocalDate.of(1970, 1, 1).atStartOfDay().atZone(ZoneOffset.UTC).toInstant()));
        category1.setDescription("The characteristics of someone or something");
        category1.setImage("Image");
        category1.setLastModifiedBy("Jan 1, 2020 9:00am GMT+0100");
        category1
                .setLastModifiedDate(Date.from(LocalDate.of(1970, 1, 1).atStartOfDay().atZone(ZoneOffset.UTC).toInstant()));
        category1.setStatus(CategoryStatus.ACTIVE);
        when(categoryRepository.save((Category) any())).thenReturn(category1);
        when(categoryRepository.findByCategoryUuid((String) any())).thenReturn(ofResult);
        assertSame(category1, categoryServiceImpl.deactivateCategory("01234567-89AB-CDEF-FEDC-BA9876543210"));
        verify(categoryRepository).save((Category) any());
        verify(categoryRepository).findByCategoryUuid((String) any());
    }

    /**
     * Method under test: {@link CategoryServiceImpl#deactivateCategory(String)}
     */
    @Test
    void testDeactivateCategory2() throws Exception {
        Category category = new Category();
        category.setCategoryId(1L);
        category.setCategoryUuid("01234567-89AB-CDEF-FEDC-BA9876543210");
        category.setCode("Code");
        category.setCreatedBy("Jan 1, 2020 8:00am GMT+0100");
        category.setCreationDate(Date.from(LocalDate.of(1970, 1, 1).atStartOfDay().atZone(ZoneOffset.UTC).toInstant()));
        category.setDescription("The characteristics of someone or something");
        category.setImage("Image");
        category.setLastModifiedBy("Jan 1, 2020 9:00am GMT+0100");
        category
                .setLastModifiedDate(Date.from(LocalDate.of(1970, 1, 1).atStartOfDay().atZone(ZoneOffset.UTC).toInstant()));
        category.setStatus(CategoryStatus.ACTIVE);
        Optional<Category> ofResult = Optional.of(category);
        when(categoryRepository.save((Category) any())).thenThrow(new IllegalArgumentException());
        when(categoryRepository.findByCategoryUuid((String) any())).thenReturn(ofResult);
        assertThrows(IllegalArgumentException.class,
                () -> categoryServiceImpl.deactivateCategory("01234567-89AB-CDEF-FEDC-BA9876543210"));
        verify(categoryRepository).save((Category) any());
        verify(categoryRepository).findByCategoryUuid((String) any());
    }

    /**
     * Method under test: {@link CategoryServiceImpl#deactivateCategory(String)}
     */
    @Test
    void testDeactivateCategory3() throws Exception {
        Category category = new Category();
        category.setCategoryId(1L);
        category.setCategoryUuid("01234567-89AB-CDEF-FEDC-BA9876543210");
        category.setCode("Code");
        category.setCreatedBy("Jan 1, 2020 8:00am GMT+0100");
        category.setCreationDate(Date.from(LocalDate.of(1970, 1, 1).atStartOfDay().atZone(ZoneOffset.UTC).toInstant()));
        category.setDescription("The characteristics of someone or something");
        category.setImage("Image");
        category.setLastModifiedBy("Jan 1, 2020 9:00am GMT+0100");
        category
                .setLastModifiedDate(Date.from(LocalDate.of(1970, 1, 1).atStartOfDay().atZone(ZoneOffset.UTC).toInstant()));
        category.setStatus(CategoryStatus.ACTIVE);
        when(categoryRepository.save((Category) any())).thenReturn(category);
        when(categoryRepository.findByCategoryUuid((String) any())).thenReturn(Optional.empty());
        assertThrows(IllegalArgumentException.class,
                () -> categoryServiceImpl.deactivateCategory("01234567-89AB-CDEF-FEDC-BA9876543210"));
        verify(categoryRepository).findByCategoryUuid((String) any());
    }
}

