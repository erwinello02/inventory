package org.document.category.controller;

import static org.mockito.Mockito.when;

import com.fasterxml.jackson.databind.ObjectMapper;

import java.time.LocalDate;
import java.time.ZoneOffset;
import java.util.Date;

import org.document.category.dto.CategoryDTO;
import org.document.category.dto.UpdateCategoryDTO;
import org.document.category.service.CategoryService;
import org.document.common.enums.CategoryStatus;
import org.document.common.model.Category;
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

@ContextConfiguration(classes = {CategoryController.class})
@ExtendWith(SpringExtension.class)
class CategoryControllerTest {
    @Autowired
    private CategoryController categoryController;

    @MockBean
    private CategoryService categoryService;

    /**
     * Method under test:
     * {@link CategoryController#addCategory(String, CategoryDTO)}
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
        when(categoryService.addCategory(Mockito.<String>any(), Mockito.<CategoryDTO>any())).thenReturn(category);

        CategoryDTO categoryDTO = new CategoryDTO();
        categoryDTO.setCode("Code");
        categoryDTO.setDescription("The characteristics of someone or something");
        categoryDTO.setImage("Image");
        String content = (new ObjectMapper()).writeValueAsString(categoryDTO);
        MockHttpServletRequestBuilder requestBuilder = MockMvcRequestBuilders.post("/category/add")
                .header("X-USER-NAME", "X-USER-NAME")
                .contentType(MediaType.APPLICATION_JSON)
                .content(content);

        // Act
        ResultActions actualPerformResult = MockMvcBuilders.standaloneSetup(categoryController)
                .build()
                .perform(requestBuilder);

        // Assert
        actualPerformResult.andExpect(MockMvcResultMatchers.status().isCreated())
                .andExpect(MockMvcResultMatchers.content().contentType("application/json"))
                .andExpect(MockMvcResultMatchers.content()
                        .string(
                                "{\"createdBy\":\"Jan 1, 2020 8:00am GMT+0100\",\"creationDate\":0,\"lastModifiedBy\":\"Jan 1, 2020 9:00am"
                                        + " GMT+0100\",\"lastModifiedDate\":0,\"categoryId\":1,\"categoryUuid\":\"01234567-89AB-CDEF-FEDC-BA9876543210"
                                        + "\",\"code\":\"Code\",\"description\":\"The characteristics of someone or something\",\"image\":\"Image\",\"status"
                                        + "\":\"ACTIVE\"}"));
    }

    /**
     * Method under test:
     * {@link CategoryController#updateCategory(String, UpdateCategoryDTO)}
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
        when(categoryService.updateCategory(Mockito.<String>any(), Mockito.<UpdateCategoryDTO>any())).thenReturn(category);

        UpdateCategoryDTO updateCategoryDTO = new UpdateCategoryDTO();
        updateCategoryDTO.setCategoryUuid("01234567-89AB-CDEF-FEDC-BA9876543210");
        updateCategoryDTO.setCode("Code");
        updateCategoryDTO.setDescription("The characteristics of someone or something");
        updateCategoryDTO.setImage("Image");
        updateCategoryDTO.setStatus(CategoryStatus.ACTIVE);
        String content = (new ObjectMapper()).writeValueAsString(updateCategoryDTO);
        MockHttpServletRequestBuilder requestBuilder = MockMvcRequestBuilders.patch("/category/update")
                .header("X-USER-NAME", "X-USER-NAME")
                .contentType(MediaType.APPLICATION_JSON)
                .content(content);

        // Act
        ResultActions actualPerformResult = MockMvcBuilders.standaloneSetup(categoryController)
                .build()
                .perform(requestBuilder);

        // Assert
        actualPerformResult.andExpect(MockMvcResultMatchers.status().isCreated())
                .andExpect(MockMvcResultMatchers.content().contentType("application/json"))
                .andExpect(MockMvcResultMatchers.content()
                        .string(
                                "{\"createdBy\":\"Jan 1, 2020 8:00am GMT+0100\",\"creationDate\":0,\"lastModifiedBy\":\"Jan 1, 2020 9:00am"
                                        + " GMT+0100\",\"lastModifiedDate\":0,\"categoryId\":1,\"categoryUuid\":\"01234567-89AB-CDEF-FEDC-BA9876543210"
                                        + "\",\"code\":\"Code\",\"description\":\"The characteristics of someone or something\",\"image\":\"Image\",\"status"
                                        + "\":\"ACTIVE\"}"));
    }

    /**
     * Method under test:
     * {@link CategoryController#deactivateCategory(String, String)}
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
        when(categoryService.deactivateCategory(Mockito.<String>any(), Mockito.<String>any())).thenReturn(category);
        MockHttpServletRequestBuilder requestBuilder = MockMvcRequestBuilders
                .delete("/category/de-activate/{categoryUuid}", "01234567-89AB-CDEF-FEDC-BA9876543210")
                .header("X-USER-NAME", "X-USER-NAME");

        // Act
        ResultActions actualPerformResult = MockMvcBuilders.standaloneSetup(categoryController)
                .build()
                .perform(requestBuilder);

        // Assert
        actualPerformResult.andExpect(MockMvcResultMatchers.status().isCreated())
                .andExpect(MockMvcResultMatchers.content().contentType("application/json"))
                .andExpect(MockMvcResultMatchers.content()
                        .string(
                                "{\"createdBy\":\"Jan 1, 2020 8:00am GMT+0100\",\"creationDate\":0,\"lastModifiedBy\":\"Jan 1, 2020 9:00am"
                                        + " GMT+0100\",\"lastModifiedDate\":0,\"categoryId\":1,\"categoryUuid\":\"01234567-89AB-CDEF-FEDC-BA9876543210"
                                        + "\",\"code\":\"Code\",\"description\":\"The characteristics of someone or something\",\"image\":\"Image\",\"status"
                                        + "\":\"ACTIVE\"}"));
    }

    /**
     * Method under test:
     * {@link CategoryController#getCategories(String, int, int, Sort.Direction)}
     */
    @Test
    @Disabled("TODO: Complete this test")
    void testGetCategories() throws Exception {
        // TODO: Complete this test.
        //   Diffblue AI was unable to find a test

        // Arrange
        // TODO: Populate arranged inputs
        MockHttpServletRequestBuilder getResult = MockMvcRequestBuilders.get("/category");
        MockHttpServletRequestBuilder paramResult = getResult.param("pageNumber", String.valueOf(1));
        MockHttpServletRequestBuilder paramResult2 = paramResult.param("pageSize", String.valueOf(1));
        MockHttpServletRequestBuilder requestBuilder = paramResult2.param("sort", String.valueOf(Sort.Direction.ASC))
                .header("X-USER-NAME", "X-USER-NAME");
        MockMvc buildResult = MockMvcBuilders.standaloneSetup(categoryController).build();

        // Act
        ResultActions actualPerformResult = buildResult.perform(requestBuilder);

        // Assert
        // TODO: Add assertions on result
    }
}
