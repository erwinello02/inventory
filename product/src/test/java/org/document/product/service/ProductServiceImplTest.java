package org.document.product.service;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertSame;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.Mockito.any;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.time.LocalDate;
import java.time.ZoneOffset;
import java.util.ArrayList;
import java.util.Date;
import java.util.Optional;

import org.document.category.repository.CategoryRepository;
import org.document.common.enums.CategoryStatus;
import org.document.common.enums.ProductStatus;
import org.document.common.model.Category;
import org.document.common.model.Product;
import org.document.common.utils.QueryResults;
import org.document.product.dto.ProductDTO;
import org.document.product.dto.UpdateProductDTO;
import org.document.product.repository.ProductRepository;
import org.document.product.service.impl.ProductServiceImpl;
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

@ContextConfiguration(classes = {ProductServiceImpl.class})
@ExtendWith(SpringExtension.class)
class ProductServiceImplTest {
    @MockBean
    private CategoryRepository categoryRepository;

    @MockBean
    private ProductRepository productRepository;

    @Autowired
    private ProductServiceImpl productServiceImpl;

    /**
     * Method under test: {@link ProductServiceImpl#addProduct(ProductDTO)}
     */
    @Test
    void testAddProduct() throws Exception {
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
        when(categoryRepository.findById((Long) any())).thenReturn(ofResult);

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

        Product product = new Product();
        product.setCategory(category1);
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
        when(productRepository.save((Product) any())).thenReturn(product);
        assertSame(product, productServiceImpl.addProduct(new ProductDTO("Product Name", 1L, "Sub Category", 1L, "Stock",
                "Minimum Qty", "Quantity", "The characteristics of someone or something", 1L, 3L, 1L, "Image")));
        verify(categoryRepository).findById((Long) any());
        verify(productRepository).save((Product) any());
    }

    /**
     * Method under test: {@link ProductServiceImpl#addProduct(ProductDTO)}
     */
    @Test
    void testAddProduct2() throws Exception {
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
        when(categoryRepository.findById((Long) any())).thenReturn(ofResult);
        when(productRepository.save((Product) any())).thenThrow(new IllegalArgumentException());
        assertThrows(IllegalArgumentException.class,
                () -> productServiceImpl.addProduct(new ProductDTO("Product Name", 1L, "Sub Category", 1L, "Stock",
                        "Minimum Qty", "Quantity", "The characteristics of someone or something", 1L, 3L, 1L, "Image")));
        verify(categoryRepository).findById((Long) any());
        verify(productRepository).save((Product) any());
    }

    /**
     * Method under test: {@link ProductServiceImpl#addProduct(ProductDTO)}
     */
    @Test
    void testAddProduct3() throws Exception {
        when(categoryRepository.findById((Long) any())).thenReturn(Optional.empty());

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

        Product product = new Product();
        product.setCategory(category);
        product.setCreatedBy("Jan 1, 2020 8:00am GMT+0100");
        product.setCreationDate(Date.from(LocalDate.of(1970, 1, 1).atStartOfDay().atZone(ZoneOffset.UTC).toInstant()));
        product.setDescription("The characteristics of someone or something");
        product.setDiscountType(3L);
        product.setImage("Image");
        product.setLastModifiedBy("Jan 1, 2020 9:00am GMT+0100");
        product
                .setLastModifiedDate(Date.from(LocalDate.of(1970, 1, 1).atStartOfDay().atZone(ZoneOffset.UTC).toInstant()));
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
        when(productRepository.save((Product) any())).thenReturn(product);
        assertThrows(IllegalArgumentException.class,
                () -> productServiceImpl.addProduct(new ProductDTO("Product Name", 1L, "Sub Category", 1L, "Stock",
                        "Minimum Qty", "Quantity", "The characteristics of someone or something", 1L, 3L, 1L, "Image")));
        verify(categoryRepository).findById((Long) any());
    }

    /**
     * Method under test: {@link ProductServiceImpl#updateProduct(UpdateProductDTO)}
     */
    @Test
    void testUpdateProduct() throws Exception {
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
        when(categoryRepository.findById((Long) any())).thenReturn(ofResult);

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

        Product product = new Product();
        product.setCategory(category1);
        product.setCreatedBy("Jan 1, 2020 8:00am GMT+0100");
        product.setCreationDate(Date.from(LocalDate.of(1970, 1, 1).atStartOfDay().atZone(ZoneOffset.UTC).toInstant()));
        product.setDescription("The characteristics of someone or something");
        product.setDiscountType(3L);
        product.setImage("Image");
        product.setLastModifiedBy("Jan 1, 2020 9:00am GMT+0100");
        product
                .setLastModifiedDate(Date.from(LocalDate.of(1970, 1, 1).atStartOfDay().atZone(ZoneOffset.UTC).toInstant()));
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
        Optional<Product> ofResult1 = Optional.of(product);

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

        Product product1 = new Product();
        product1.setCategory(category2);
        product1.setCreatedBy("Jan 1, 2020 8:00am GMT+0100");
        product1.setCreationDate(Date.from(LocalDate.of(1970, 1, 1).atStartOfDay().atZone(ZoneOffset.UTC).toInstant()));
        product1.setDescription("The characteristics of someone or something");
        product1.setDiscountType(3L);
        product1.setImage("Image");
        product1.setLastModifiedBy("Jan 1, 2020 9:00am GMT+0100");
        product1
                .setLastModifiedDate(Date.from(LocalDate.of(1970, 1, 1).atStartOfDay().atZone(ZoneOffset.UTC).toInstant()));
        product1.setMinimumQty("Minimum Qty");
        product1.setPrice(1L);
        product1.setProductId(1L);
        product1.setProductName("Product Name");
        product1.setProductUuid("01234567-89AB-CDEF-FEDC-BA9876543210");
        product1.setQuantity("Quantity");
        product1.setStatus(ProductStatus.ACTIVE);
        product1.setStock("Stock");
        product1.setSubCategory("Sub Category");
        product1.setTax(1L);
        product1.setUnit(1L);
        when(productRepository.save((Product) any())).thenReturn(product1);
        when(productRepository.findByProductUuid((String) any())).thenReturn(ofResult1);

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
        assertSame(product1, productServiceImpl.updateProduct(updateProductDTO));
        verify(categoryRepository).findById((Long) any());
        verify(productRepository).save((Product) any());
        verify(productRepository).findByProductUuid((String) any());
    }

    /**
     * Method under test: {@link ProductServiceImpl#updateProduct(UpdateProductDTO)}
     */
    @Test
    void testUpdateProduct2() throws Exception {
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
        when(categoryRepository.findById((Long) any())).thenReturn(ofResult);

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

        Product product = new Product();
        product.setCategory(category1);
        product.setCreatedBy("Jan 1, 2020 8:00am GMT+0100");
        product.setCreationDate(Date.from(LocalDate.of(1970, 1, 1).atStartOfDay().atZone(ZoneOffset.UTC).toInstant()));
        product.setDescription("The characteristics of someone or something");
        product.setDiscountType(3L);
        product.setImage("Image");
        product.setLastModifiedBy("Jan 1, 2020 9:00am GMT+0100");
        product
                .setLastModifiedDate(Date.from(LocalDate.of(1970, 1, 1).atStartOfDay().atZone(ZoneOffset.UTC).toInstant()));
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
        Optional<Product> ofResult1 = Optional.of(product);
        when(productRepository.save((Product) any())).thenThrow(new IllegalArgumentException());
        when(productRepository.findByProductUuid((String) any())).thenReturn(ofResult1);

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
        assertThrows(IllegalArgumentException.class, () -> productServiceImpl.updateProduct(updateProductDTO));
        verify(categoryRepository).findById((Long) any());
        verify(productRepository).save((Product) any());
        verify(productRepository).findByProductUuid((String) any());
    }

    /**
     * Method under test: {@link ProductServiceImpl#updateProduct(UpdateProductDTO)}
     */
    @Test
    void testUpdateProduct3() throws Exception {
        when(categoryRepository.findById((Long) any())).thenReturn(Optional.empty());

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

        Product product = new Product();
        product.setCategory(category);
        product.setCreatedBy("Jan 1, 2020 8:00am GMT+0100");
        product.setCreationDate(Date.from(LocalDate.of(1970, 1, 1).atStartOfDay().atZone(ZoneOffset.UTC).toInstant()));
        product.setDescription("The characteristics of someone or something");
        product.setDiscountType(3L);
        product.setImage("Image");
        product.setLastModifiedBy("Jan 1, 2020 9:00am GMT+0100");
        product
                .setLastModifiedDate(Date.from(LocalDate.of(1970, 1, 1).atStartOfDay().atZone(ZoneOffset.UTC).toInstant()));
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
        Optional<Product> ofResult = Optional.of(product);

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

        Product product1 = new Product();
        product1.setCategory(category1);
        product1.setCreatedBy("Jan 1, 2020 8:00am GMT+0100");
        product1.setCreationDate(Date.from(LocalDate.of(1970, 1, 1).atStartOfDay().atZone(ZoneOffset.UTC).toInstant()));
        product1.setDescription("The characteristics of someone or something");
        product1.setDiscountType(3L);
        product1.setImage("Image");
        product1.setLastModifiedBy("Jan 1, 2020 9:00am GMT+0100");
        product1
                .setLastModifiedDate(Date.from(LocalDate.of(1970, 1, 1).atStartOfDay().atZone(ZoneOffset.UTC).toInstant()));
        product1.setMinimumQty("Minimum Qty");
        product1.setPrice(1L);
        product1.setProductId(1L);
        product1.setProductName("Product Name");
        product1.setProductUuid("01234567-89AB-CDEF-FEDC-BA9876543210");
        product1.setQuantity("Quantity");
        product1.setStatus(ProductStatus.ACTIVE);
        product1.setStock("Stock");
        product1.setSubCategory("Sub Category");
        product1.setTax(1L);
        product1.setUnit(1L);
        when(productRepository.save((Product) any())).thenReturn(product1);
        when(productRepository.findByProductUuid((String) any())).thenReturn(ofResult);

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
        assertThrows(IllegalArgumentException.class, () -> productServiceImpl.updateProduct(updateProductDTO));
        verify(categoryRepository).findById((Long) any());
        verify(productRepository).findByProductUuid((String) any());
    }

    /**
     * Method under test: {@link ProductServiceImpl#getProducts(int, int, Sort.Direction)}
     */
    @Test
    void testGetProducts() throws Exception {
        when(productRepository.findAll((Pageable) any())).thenReturn(new PageImpl<>(new ArrayList<>()));
        QueryResults<Product> actualProducts = productServiceImpl.getProducts(10, 3, Sort.Direction.ASC);
        assertEquals(0L, actualProducts.countOfResults.longValue());
        assertTrue(actualProducts.results.isEmpty());
        verify(productRepository).findAll((Pageable) any());
    }

    /**
     * Method under test: {@link ProductServiceImpl#getProducts(int, int, Sort.Direction)}
     */
    @Test
    @Disabled("TODO: Complete this test")
    void testGetProducts2() throws Exception {
        // TODO: Complete this test.
        //   Reason: R013 No inputs found that don't throw a trivial exception.
        //   Diffblue Cover tried to run the arrange/act section, but the method under
        //   test threw
        //   java.lang.NullPointerException
        //       at org.document.product.service.impl.ProductServiceImpl.getProducts(ProductServiceImpl.java:70)
        //   See https://diff.blue/R013 to resolve this issue.

        when(productRepository.findAll((Pageable) any())).thenReturn(null);
        productServiceImpl.getProducts(10, 3, Sort.Direction.ASC);
    }

    /**
     * Method under test: {@link ProductServiceImpl#getProducts(int, int, Sort.Direction)}
     */
    @Test
    @Disabled("TODO: Complete this test")
    void testGetProducts3() throws Exception {
        // TODO: Complete this test.
        //   Reason: R013 No inputs found that don't throw a trivial exception.
        //   Diffblue Cover tried to run the arrange/act section, but the method under
        //   test threw
        //   java.lang.IllegalArgumentException: Page index must not be less than zero
        //       at org.document.product.service.impl.ProductServiceImpl.getProducts(ProductServiceImpl.java:68)
        //   See https://diff.blue/R013 to resolve this issue.

        when(productRepository.findAll((Pageable) any())).thenReturn(new PageImpl<>(new ArrayList<>()));
        productServiceImpl.getProducts(0, 3, Sort.Direction.ASC);
    }

    /**
     * Method under test: {@link ProductServiceImpl#getProducts(int, int, Sort.Direction)}
     */
    @Test
    void testGetProducts4() throws Exception {
        when(productRepository.findAll((Pageable) any())).thenReturn(new PageImpl<>(new ArrayList<>()));
        QueryResults<Product> actualProducts = productServiceImpl.getProducts(10, 3, Sort.Direction.DESC);
        assertEquals(0L, actualProducts.countOfResults.longValue());
        assertTrue(actualProducts.results.isEmpty());
        verify(productRepository).findAll((Pageable) any());
    }

    /**
     * Method under test: {@link ProductServiceImpl#getProducts(int, int, Sort.Direction)}
     */
    @Test
    void testGetProducts5() throws Exception {
        when(productRepository.findAll((Pageable) any())).thenThrow(new IllegalArgumentException());
        assertThrows(IllegalArgumentException.class, () -> productServiceImpl.getProducts(10, 3, Sort.Direction.ASC));
        verify(productRepository).findAll((Pageable) any());
    }

    /**
     * Method under test: {@link ProductServiceImpl#deactivateProduct(String)}
     */
    @Test
    void testDeactivateProduct() throws Exception {
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

        Product product = new Product();
        product.setCategory(category);
        product.setCreatedBy("Jan 1, 2020 8:00am GMT+0100");
        product.setCreationDate(Date.from(LocalDate.of(1970, 1, 1).atStartOfDay().atZone(ZoneOffset.UTC).toInstant()));
        product.setDescription("The characteristics of someone or something");
        product.setDiscountType(3L);
        product.setImage("Image");
        product.setLastModifiedBy("Jan 1, 2020 9:00am GMT+0100");
        product
                .setLastModifiedDate(Date.from(LocalDate.of(1970, 1, 1).atStartOfDay().atZone(ZoneOffset.UTC).toInstant()));
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
        Optional<Product> ofResult = Optional.of(product);

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

        Product product1 = new Product();
        product1.setCategory(category1);
        product1.setCreatedBy("Jan 1, 2020 8:00am GMT+0100");
        product1.setCreationDate(Date.from(LocalDate.of(1970, 1, 1).atStartOfDay().atZone(ZoneOffset.UTC).toInstant()));
        product1.setDescription("The characteristics of someone or something");
        product1.setDiscountType(3L);
        product1.setImage("Image");
        product1.setLastModifiedBy("Jan 1, 2020 9:00am GMT+0100");
        product1
                .setLastModifiedDate(Date.from(LocalDate.of(1970, 1, 1).atStartOfDay().atZone(ZoneOffset.UTC).toInstant()));
        product1.setMinimumQty("Minimum Qty");
        product1.setPrice(1L);
        product1.setProductId(1L);
        product1.setProductName("Product Name");
        product1.setProductUuid("01234567-89AB-CDEF-FEDC-BA9876543210");
        product1.setQuantity("Quantity");
        product1.setStatus(ProductStatus.ACTIVE);
        product1.setStock("Stock");
        product1.setSubCategory("Sub Category");
        product1.setTax(1L);
        product1.setUnit(1L);
        when(productRepository.save((Product) any())).thenReturn(product1);
        when(productRepository.findByProductUuid((String) any())).thenReturn(ofResult);
        assertSame(product1, productServiceImpl.deactivateProduct("01234567-89AB-CDEF-FEDC-BA9876543210"));
        verify(productRepository).save((Product) any());
        verify(productRepository).findByProductUuid((String) any());
    }

    /**
     * Method under test: {@link ProductServiceImpl#deactivateProduct(String)}
     */
    @Test
    void testDeactivateProduct2() throws Exception {
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

        Product product = new Product();
        product.setCategory(category);
        product.setCreatedBy("Jan 1, 2020 8:00am GMT+0100");
        product.setCreationDate(Date.from(LocalDate.of(1970, 1, 1).atStartOfDay().atZone(ZoneOffset.UTC).toInstant()));
        product.setDescription("The characteristics of someone or something");
        product.setDiscountType(3L);
        product.setImage("Image");
        product.setLastModifiedBy("Jan 1, 2020 9:00am GMT+0100");
        product
                .setLastModifiedDate(Date.from(LocalDate.of(1970, 1, 1).atStartOfDay().atZone(ZoneOffset.UTC).toInstant()));
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
        Optional<Product> ofResult = Optional.of(product);
        when(productRepository.save((Product) any())).thenThrow(new IllegalArgumentException());
        when(productRepository.findByProductUuid((String) any())).thenReturn(ofResult);
        assertThrows(IllegalArgumentException.class,
                () -> productServiceImpl.deactivateProduct("01234567-89AB-CDEF-FEDC-BA9876543210"));
        verify(productRepository).save((Product) any());
        verify(productRepository).findByProductUuid((String) any());
    }
}

