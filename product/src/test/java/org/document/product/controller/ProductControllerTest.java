package org.document.product.controller;

import static org.mockito.Mockito.when;

import com.fasterxml.jackson.databind.ObjectMapper;

import java.time.LocalDate;
import java.time.ZoneOffset;
import java.util.Date;

import org.document.common.enums.CategoryStatus;
import org.document.common.enums.ProductStatus;
import org.document.common.model.Category;
import org.document.common.model.Product;
import org.document.product.dto.ProductDTO;
import org.document.product.dto.UpdateProductDTO;
import org.document.product.service.ProductService;
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

@ContextConfiguration(classes = {ProductController.class})
@ExtendWith(SpringExtension.class)
class ProductControllerTest {
    @Autowired
    private ProductController productController;

    @MockBean
    private ProductService productService;

    /**
     * Method under test: {@link ProductController#addProduct(String, ProductDTO)}
     */
    @Test
    @Disabled("TODO: Complete this test")
    void testAddProduct() throws Exception {
        // TODO: Complete this test.
        //   Reason: R013 No inputs found that don't throw a trivial exception.
        //   Diffblue Cover tried to run the arrange/act section, but the method under
        //   test threw
        //   org.springframework.http.converter.HttpMessageConversionException: Type definition error: [simple type, class org.document.product.dto.ProductDTO]; nested exception is com.fasterxml.jackson.databind.exc.InvalidDefinitionException: Cannot construct instance of `org.document.product.dto.ProductDTO` (no Creators, like default constructor, exist): cannot deserialize from Object value (no delegate- or property-based Creator)
        //    at [Source: (org.springframework.util.StreamUtils$NonClosingInputStream); line: 1, column: 2]
        //       at javax.servlet.http.HttpServlet.service(HttpServlet.java:696)
        //       at javax.servlet.http.HttpServlet.service(HttpServlet.java:779)
        //   com.fasterxml.jackson.databind.exc.InvalidDefinitionException: Cannot construct instance of `org.document.product.dto.ProductDTO` (no Creators, like default constructor, exist): cannot deserialize from Object value (no delegate- or property-based Creator)
        //    at [Source: (org.springframework.util.StreamUtils$NonClosingInputStream); line: 1, column: 2]
        //       at com.fasterxml.jackson.databind.exc.InvalidDefinitionException.from(InvalidDefinitionException.java:67)
        //       at com.fasterxml.jackson.databind.DeserializationContext.reportBadDefinition(DeserializationContext.java:1904)
        //       at com.fasterxml.jackson.databind.DatabindContext.reportBadDefinition(DatabindContext.java:400)
        //       at com.fasterxml.jackson.databind.DeserializationContext.handleMissingInstantiator(DeserializationContext.java:1349)
        //       at com.fasterxml.jackson.databind.deser.BeanDeserializerBase.deserializeFromObjectUsingNonDefault(BeanDeserializerBase.java:1415)
        //       at com.fasterxml.jackson.databind.deser.BeanDeserializer.deserializeFromObject(BeanDeserializer.java:352)
        //       at com.fasterxml.jackson.databind.deser.BeanDeserializer.deserialize(BeanDeserializer.java:185)
        //       at com.fasterxml.jackson.databind.deser.DefaultDeserializationContext.readRootValue(DefaultDeserializationContext.java:323)
        //       at com.fasterxml.jackson.databind.ObjectMapper._readMapAndClose(ObjectMapper.java:4674)
        //       at com.fasterxml.jackson.databind.ObjectMapper.readValue(ObjectMapper.java:3682)
        //       at javax.servlet.http.HttpServlet.service(HttpServlet.java:696)
        //       at javax.servlet.http.HttpServlet.service(HttpServlet.java:779)
        //   See https://diff.blue/R013 to resolve this issue.

        // Arrange
        ProductController productController = new ProductController();

        // Act
        productController.addProduct("janedoe", new ProductDTO("Product Name", 1L, "Sub Category", 1L, "Stock",
                "Minimum Qty", "Quantity", "The characteristics of someone or something", 1L, 3L, 1L, "Image"));
    }

    /**
     * Method under test:
     * {@link ProductController#updateProduct(String, UpdateProductDTO)}
     */
    @Test
    void testUpdateProduct() throws Exception {
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

        Product product = new Product();
        product.setCategory(category);
        product.setCreatedBy("Jan 1, 2020 8:00am GMT+0100");
        product.setCreationDate(Date.from(LocalDate.of(1970, 1, 1).atStartOfDay().atZone(ZoneOffset.UTC).toInstant()));
        product.setDescription("The characteristics of someone or something");
        product.setDiscountType(3L);
        product.setImage("Image");
        product.setLastModifiedBy("Jan 1, 2020 9:00am GMT+0100");
        product.setLastModifiedDate(Date.from(LocalDate.of(1970, 1, 1).atStartOfDay().atZone(ZoneOffset.UTC).toInstant()));
        product.setMinimumQty("Minimum Qty");
        product.setPrice(1L);
        product.setProductId(1L);
        product.setProductName("Product Name");
        product.setProductUuid("01234567-89AB-CDEF-FEDC-BA9876543210");
        product.setQuantity("Quantity");
        product.setStatus(ProductStatus.ACTIVE);
        product.setStock("Stock");
        product.setSubCategory("Sub Category");
        product.setTax(1L);
        product.setUnit(1L);
        when(productService.updateProduct(Mockito.<String>any(), Mockito.<UpdateProductDTO>any())).thenReturn(product);

        UpdateProductDTO updateProductDTO = new UpdateProductDTO();
        updateProductDTO.setCategoryId(1L);
        updateProductDTO.setDescription("The characteristics of someone or something");
        updateProductDTO.setDiscountType(3L);
        updateProductDTO.setImage("Image");
        updateProductDTO.setMinimumQty("Minimum Qty");
        updateProductDTO.setPrice(1L);
        updateProductDTO.setProductName("Product Name");
        updateProductDTO.setProductUuid("01234567-89AB-CDEF-FEDC-BA9876543210");
        updateProductDTO.setQuantity("Quantity");
        updateProductDTO.setStatus(ProductStatus.ACTIVE);
        updateProductDTO.setStock("Stock");
        updateProductDTO.setSubCategory("Sub Category");
        updateProductDTO.setTax(1L);
        updateProductDTO.setUnit(1L);
        String content = (new ObjectMapper()).writeValueAsString(updateProductDTO);
        MockHttpServletRequestBuilder requestBuilder = MockMvcRequestBuilders.patch("/product/update")
                .header("X-USER-NAME", "X-USER-NAME")
                .contentType(MediaType.APPLICATION_JSON)
                .content(content);

        // Act
        ResultActions actualPerformResult = MockMvcBuilders.standaloneSetup(productController)
                .build()
                .perform(requestBuilder);

        // Assert
        actualPerformResult.andExpect(MockMvcResultMatchers.status().isCreated())
                .andExpect(MockMvcResultMatchers.content().contentType("application/json"))
                .andExpect(MockMvcResultMatchers.content()
                        .string(
                                "{\"createdBy\":\"Jan 1, 2020 8:00am GMT+0100\",\"creationDate\":0,\"lastModifiedBy\":\"Jan 1, 2020 9:00am"
                                        + " GMT+0100\",\"lastModifiedDate\":0,\"productId\":1,\"productUuid\":\"01234567-89AB-CDEF-FEDC-BA9876543210\","
                                        + "\"productName\":\"Product Name\",\"category\":{\"createdBy\":\"Jan 1, 2020 8:00am GMT+0100\",\"creationDate\":0,"
                                        + "\"lastModifiedBy\":\"Jan 1, 2020 9:00am GMT+0100\",\"lastModifiedDate\":0,\"categoryId\":1,\"categoryUuid\":"
                                        + "\"01234567-89AB-CDEF-FEDC-BA9876543210\",\"code\":\"Code\",\"description\":\"The characteristics of someone or"
                                        + " something\",\"image\":\"Image\",\"status\":\"ACTIVE\"},\"subCategory\":\"Sub Category\",\"unit\":1,\"stock\":\"Stock\""
                                        + ",\"minimumQty\":\"Minimum Qty\",\"quantity\":\"Quantity\",\"description\":\"The characteristics of someone or"
                                        + " something\",\"tax\":1,\"discountType\":3,\"price\":1,\"status\":\"ACTIVE\",\"image\":\"Image\"}"));
    }

    /**
     * Method under test:
     * {@link ProductController#deactivateProduct(String, String)}
     */
    @Test
    void testDeactivateProduct() throws Exception {
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

        Product product = new Product();
        product.setCategory(category);
        product.setCreatedBy("Jan 1, 2020 8:00am GMT+0100");
        product.setCreationDate(Date.from(LocalDate.of(1970, 1, 1).atStartOfDay().atZone(ZoneOffset.UTC).toInstant()));
        product.setDescription("The characteristics of someone or something");
        product.setDiscountType(3L);
        product.setImage("Image");
        product.setLastModifiedBy("Jan 1, 2020 9:00am GMT+0100");
        product.setLastModifiedDate(Date.from(LocalDate.of(1970, 1, 1).atStartOfDay().atZone(ZoneOffset.UTC).toInstant()));
        product.setMinimumQty("Minimum Qty");
        product.setPrice(1L);
        product.setProductId(1L);
        product.setProductName("Product Name");
        product.setProductUuid("01234567-89AB-CDEF-FEDC-BA9876543210");
        product.setQuantity("Quantity");
        product.setStatus(ProductStatus.ACTIVE);
        product.setStock("Stock");
        product.setSubCategory("Sub Category");
        product.setTax(1L);
        product.setUnit(1L);
        when(productService.deactivateProduct(Mockito.<String>any(), Mockito.<String>any())).thenReturn(product);
        MockHttpServletRequestBuilder requestBuilder = MockMvcRequestBuilders
                .delete("/product/de-activate/{productUuid}", "01234567-89AB-CDEF-FEDC-BA9876543210")
                .header("X-USER-NAME", "X-USER-NAME");

        // Act
        ResultActions actualPerformResult = MockMvcBuilders.standaloneSetup(productController)
                .build()
                .perform(requestBuilder);

        // Assert
        actualPerformResult.andExpect(MockMvcResultMatchers.status().isCreated())
                .andExpect(MockMvcResultMatchers.content().contentType("application/json"))
                .andExpect(MockMvcResultMatchers.content()
                        .string(
                                "{\"createdBy\":\"Jan 1, 2020 8:00am GMT+0100\",\"creationDate\":0,\"lastModifiedBy\":\"Jan 1, 2020 9:00am"
                                        + " GMT+0100\",\"lastModifiedDate\":0,\"productId\":1,\"productUuid\":\"01234567-89AB-CDEF-FEDC-BA9876543210\","
                                        + "\"productName\":\"Product Name\",\"category\":{\"createdBy\":\"Jan 1, 2020 8:00am GMT+0100\",\"creationDate\":0,"
                                        + "\"lastModifiedBy\":\"Jan 1, 2020 9:00am GMT+0100\",\"lastModifiedDate\":0,\"categoryId\":1,\"categoryUuid\":"
                                        + "\"01234567-89AB-CDEF-FEDC-BA9876543210\",\"code\":\"Code\",\"description\":\"The characteristics of someone or"
                                        + " something\",\"image\":\"Image\",\"status\":\"ACTIVE\"},\"subCategory\":\"Sub Category\",\"unit\":1,\"stock\":\"Stock\""
                                        + ",\"minimumQty\":\"Minimum Qty\",\"quantity\":\"Quantity\",\"description\":\"The characteristics of someone or"
                                        + " something\",\"tax\":1,\"discountType\":3,\"price\":1,\"status\":\"ACTIVE\",\"image\":\"Image\"}"));
    }

    /**
     * Method under test:
     * {@link ProductController#getProducts(String, int, int, Sort.Direction)}
     */
    @Test
    @Disabled("TODO: Complete this test")
    void testGetProducts() throws Exception {
        // TODO: Complete this test.
        //   Diffblue AI was unable to find a test

        // Arrange
        // TODO: Populate arranged inputs
        MockHttpServletRequestBuilder getResult = MockMvcRequestBuilders.get("/product");
        MockHttpServletRequestBuilder paramResult = getResult.param("pageNumber", String.valueOf(1));
        MockHttpServletRequestBuilder paramResult2 = paramResult.param("pageSize", String.valueOf(1));
        MockHttpServletRequestBuilder requestBuilder = paramResult2.param("sort", String.valueOf(Sort.Direction.ASC))
                .header("X-USER-NAME", "X-USER-NAME");
        MockMvc buildResult = MockMvcBuilders.standaloneSetup(productController).build();

        // Act
        ResultActions actualPerformResult = buildResult.perform(requestBuilder);

        // Assert
        // TODO: Add assertions on result
    }
}
