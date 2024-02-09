package org.document.product.service;

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

import org.document.category.repository.CategoryRepository;
import org.document.common.enums.CategoryStatus;
import org.document.common.enums.Gender;
import org.document.common.enums.ProductStatus;
import org.document.common.enums.UserStatus;
import org.document.common.model.Category;
import org.document.common.model.Product;
import org.document.common.model.Users;
import org.document.common.utils.QueryResults;
import org.document.product.dto.ProductDTO;
import org.document.product.dto.UpdateProductDTO;
import org.document.product.repository.ProductRepository;
import org.document.product.service.impl.ProductServiceImpl;
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

@ContextConfiguration(classes = {ProductServiceImpl.class})
@ExtendWith(SpringExtension.class)
class ProductServiceImplTest {
    @MockBean
    private CategoryRepository categoryRepository;

    @MockBean
    private ProductRepository productRepository;

    @Autowired
    private ProductServiceImpl productServiceImpl;

    @MockBean
    private UserRepository userRepository;

    /**
     * Method under test: {@link ProductServiceImpl#addProduct(String, ProductDTO)}
     */
    @Test
    void testAddProduct() throws Exception {
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
        when(categoryRepository.findById(Mockito.<Long>any())).thenReturn(ofResult);

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

        Product product = new Product();
        product.setCategory(category2);
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
        when(productRepository.save(Mockito.<Product>any())).thenReturn(product);

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
        Product actualAddProductResult = productServiceImpl.addProduct("janedoe",
                new ProductDTO("Product Name", 1L, "Sub Category", 1L, "Stock", "Minimum Qty", "Quantity",
                        "The characteristics of someone or something", 1L, 3L, 1L, "Image"));

        // Assert
        verify(userRepository).findByUserName(Mockito.<String>any());
        verify(categoryRepository).findById(Mockito.<Long>any());
        verify(productRepository).save(Mockito.<Product>any());
        assertSame(product, actualAddProductResult);
    }

    /**
     * Method under test: {@link ProductServiceImpl#addProduct(String, ProductDTO)}
     */
    @Test
    void testAddProduct2() throws Exception {
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
        when(categoryRepository.findById(Mockito.<Long>any())).thenReturn(ofResult);
        when(productRepository.save(Mockito.<Product>any())).thenThrow(new IllegalArgumentException("CreateProductStart"));

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
                () -> productServiceImpl.addProduct("janedoe", new ProductDTO("Product Name", 1L, "Sub Category", 1L, "Stock",
                        "Minimum Qty", "Quantity", "The characteristics of someone or something", 1L, 3L, 1L, "Image")));
        verify(userRepository).findByUserName(Mockito.<String>any());
        verify(categoryRepository).findById(Mockito.<Long>any());
        verify(productRepository).save(Mockito.<Product>any());
    }

    /**
     * Method under test: {@link ProductServiceImpl#addProduct(String, ProductDTO)}
     */
    @Test
    void testAddProduct3() throws Exception {
        // Arrange
        Optional<Category> emptyResult = Optional.empty();
        when(categoryRepository.findById(Mockito.<Long>any())).thenReturn(emptyResult);

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
                () -> productServiceImpl.addProduct("janedoe", new ProductDTO("Product Name", 1L, "Sub Category", 1L, "Stock",
                        "Minimum Qty", "Quantity", "The characteristics of someone or something", 1L, 3L, 1L, "Image")));
        verify(userRepository).findByUserName(Mockito.<String>any());
        verify(categoryRepository).findById(Mockito.<Long>any());
    }

    /**
     * Method under test:
     * {@link ProductServiceImpl#updateProduct(String, UpdateProductDTO)}
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
        Optional<Category> ofResult = Optional.of(category);
        when(categoryRepository.findById(Mockito.<Long>any())).thenReturn(ofResult);

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

        Product product = new Product();
        product.setCategory(category2);
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
        Optional<Product> ofResult2 = Optional.of(product);

        Category category3 = new Category();
        category3.setCategoryId(1L);
        category3.setCategoryUuid("01234567-89AB-CDEF-FEDC-BA9876543210");
        category3.setCode("Code");
        category3.setCreatedBy("Jan 1, 2020 8:00am GMT+0100");
        category3.setCreationDate(Date.from(LocalDate.of(1970, 1, 1).atStartOfDay().atZone(ZoneOffset.UTC).toInstant()));
        category3.setDescription("The characteristics of someone or something");
        category3.setImage("Image");
        category3.setLastModifiedBy("Jan 1, 2020 9:00am GMT+0100");
        category3
                .setLastModifiedDate(Date.from(LocalDate.of(1970, 1, 1).atStartOfDay().atZone(ZoneOffset.UTC).toInstant()));
        category3.setStatus(CategoryStatus.ACTIVE);

        Product product2 = new Product();
        product2.setCategory(category3);
        product2.setCreatedBy("Jan 1, 2020 8:00am GMT+0100");
        product2.setCreationDate(Date.from(LocalDate.of(1970, 1, 1).atStartOfDay().atZone(ZoneOffset.UTC).toInstant()));
        product2.setDescription("The characteristics of someone or something");
        product2.setDiscountType(3L);
        product2.setImage("Image");
        product2.setLastModifiedBy("Jan 1, 2020 9:00am GMT+0100");
        product2.setLastModifiedDate(Date.from(LocalDate.of(1970, 1, 1).atStartOfDay().atZone(ZoneOffset.UTC).toInstant()));
        product2.setMinimumQty("Minimum Qty");
        product2.setPrice(1L);
        product2.setProductId(1L);
        product2.setProductName("Product Name");
        product2.setProductUuid("01234567-89AB-CDEF-FEDC-BA9876543210");
        product2.setQuantity("Quantity");
        product2.setStatus(ProductStatus.ACTIVE);
        product2.setStock("Stock");
        product2.setSubCategory("Sub Category");
        product2.setTax(1L);
        product2.setUnit(1L);
        when(productRepository.save(Mockito.<Product>any())).thenReturn(product2);
        when(productRepository.findByProductUuid(Mockito.<String>any())).thenReturn(ofResult2);

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
        Optional<Users> ofResult3 = Optional.of(users);
        when(userRepository.findByUserName(Mockito.<String>any())).thenReturn(ofResult3);

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

        // Act
        Product actualUpdateProductResult = productServiceImpl.updateProduct("janedoe", updateProductDTO);

        // Assert
        verify(productRepository).findByProductUuid(Mockito.<String>any());
        verify(userRepository).findByUserName(Mockito.<String>any());
        verify(categoryRepository).findById(Mockito.<Long>any());
        verify(productRepository).save(Mockito.<Product>any());
        assertSame(product2, actualUpdateProductResult);
    }

    /**
     * Method under test:
     * {@link ProductServiceImpl#updateProduct(String, UpdateProductDTO)}
     */
    @Test
    void testUpdateProduct2() throws Exception {
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
        when(categoryRepository.findById(Mockito.<Long>any())).thenReturn(ofResult);

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

        Product product = new Product();
        product.setCategory(category2);
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
        Optional<Product> ofResult2 = Optional.of(product);
        when(productRepository.save(Mockito.<Product>any())).thenThrow(new IllegalArgumentException("UpdateProductStart"));
        when(productRepository.findByProductUuid(Mockito.<String>any())).thenReturn(ofResult2);

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
        Optional<Users> ofResult3 = Optional.of(users);
        when(userRepository.findByUserName(Mockito.<String>any())).thenReturn(ofResult3);

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

        // Act and Assert
        assertThrows(IllegalArgumentException.class, () -> productServiceImpl.updateProduct("janedoe", updateProductDTO));
        verify(productRepository).findByProductUuid(Mockito.<String>any());
        verify(userRepository).findByUserName(Mockito.<String>any());
        verify(categoryRepository).findById(Mockito.<Long>any());
        verify(productRepository).save(Mockito.<Product>any());
    }

    /**
     * Method under test:
     * {@link ProductServiceImpl#updateProduct(String, UpdateProductDTO)}
     */
    @Test
    void testUpdateProduct3() throws Exception {
        // Arrange
        Optional<Category> emptyResult = Optional.empty();
        when(categoryRepository.findById(Mockito.<Long>any())).thenReturn(emptyResult);

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
        Optional<Product> ofResult = Optional.of(product);
        when(productRepository.findByProductUuid(Mockito.<String>any())).thenReturn(ofResult);

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

        // Act and Assert
        assertThrows(IllegalArgumentException.class, () -> productServiceImpl.updateProduct("janedoe", updateProductDTO));
        verify(productRepository).findByProductUuid(Mockito.<String>any());
        verify(userRepository).findByUserName(Mockito.<String>any());
        verify(categoryRepository).findById(Mockito.<Long>any());
    }

    /**
     * Method under test:
     * {@link ProductServiceImpl#getProducts(String, int, int, Sort.Direction)}
     */
    @Test
    void testGetProducts() throws Exception {
        // Arrange
        ArrayList<Product> content = new ArrayList<>();
        when(productRepository.findAll(Mockito.<Pageable>any())).thenReturn(new PageImpl<>(content));

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
        QueryResults<Product> actualProducts = productServiceImpl.getProducts("janedoe", 10, 3, Sort.Direction.ASC);

        // Assert
        verify(userRepository).findByUserName(Mockito.<String>any());
        verify(productRepository).findAll(Mockito.<Pageable>any());
        assertEquals(0L, actualProducts.countOfResults.longValue());
        assertEquals(content, actualProducts.results);
    }

    /**
     * Method under test:
     * {@link ProductServiceImpl#getProducts(String, int, int, Sort.Direction)}
     */
    @Test
    void testGetProducts2() throws Exception {
        // Arrange
        when(productRepository.findAll(Mockito.<Pageable>any()))
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
                () -> productServiceImpl.getProducts("janedoe", 10, 3, Sort.Direction.ASC));
        verify(userRepository).findByUserName(Mockito.<String>any());
        verify(productRepository).findAll(Mockito.<Pageable>any());
    }

    /**
     * Method under test:
     * {@link ProductServiceImpl#getProducts(String, int, int, Sort.Direction)}
     */
    @Test
    void testGetProducts3() throws Exception {
        // Arrange
        Optional<Users> emptyResult = Optional.empty();
        when(userRepository.findByUserName(Mockito.<String>any())).thenReturn(emptyResult);

        // Act and Assert
        assertThrows(IllegalArgumentException.class,
                () -> productServiceImpl.getProducts("janedoe", 10, 3, Sort.Direction.ASC));
        verify(userRepository).findByUserName(Mockito.<String>any());
    }

    /**
     * Method under test:
     * {@link ProductServiceImpl#getProducts(String, int, int, Sort.Direction)}
     */
    @Test
    void testGetProducts4() throws Exception {
        // Arrange
        ArrayList<Product> content = new ArrayList<>();
        when(productRepository.findAll(Mockito.<Pageable>any())).thenReturn(new PageImpl<>(content));

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
        QueryResults<Product> actualProducts = productServiceImpl.getProducts("janedoe", 10, 3, Sort.Direction.DESC);

        // Assert
        verify(userRepository).findByUserName(Mockito.<String>any());
        verify(productRepository).findAll(Mockito.<Pageable>any());
        assertEquals(0L, actualProducts.countOfResults.longValue());
        assertEquals(content, actualProducts.results);
    }

    /**
     * Method under test:
     * {@link ProductServiceImpl#deactivateProduct(String, String)}
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
        Optional<Product> ofResult = Optional.of(product);

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

        Product product2 = new Product();
        product2.setCategory(category2);
        product2.setCreatedBy("Jan 1, 2020 8:00am GMT+0100");
        product2.setCreationDate(Date.from(LocalDate.of(1970, 1, 1).atStartOfDay().atZone(ZoneOffset.UTC).toInstant()));
        product2.setDescription("The characteristics of someone or something");
        product2.setDiscountType(3L);
        product2.setImage("Image");
        product2.setLastModifiedBy("Jan 1, 2020 9:00am GMT+0100");
        product2.setLastModifiedDate(Date.from(LocalDate.of(1970, 1, 1).atStartOfDay().atZone(ZoneOffset.UTC).toInstant()));
        product2.setMinimumQty("Minimum Qty");
        product2.setPrice(1L);
        product2.setProductId(1L);
        product2.setProductName("Product Name");
        product2.setProductUuid("01234567-89AB-CDEF-FEDC-BA9876543210");
        product2.setQuantity("Quantity");
        product2.setStatus(ProductStatus.ACTIVE);
        product2.setStock("Stock");
        product2.setSubCategory("Sub Category");
        product2.setTax(1L);
        product2.setUnit(1L);
        when(productRepository.save(Mockito.<Product>any())).thenReturn(product2);
        when(productRepository.findByProductUuid(Mockito.<String>any())).thenReturn(ofResult);

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
        Product actualDeactivateProductResult = productServiceImpl.deactivateProduct("janedoe",
                "01234567-89AB-CDEF-FEDC-BA9876543210");

        // Assert
        verify(productRepository).findByProductUuid(Mockito.<String>any());
        verify(userRepository).findByUserName(Mockito.<String>any());
        verify(productRepository).save(Mockito.<Product>any());
        assertSame(product2, actualDeactivateProductResult);
    }

    /**
     * Method under test:
     * {@link ProductServiceImpl#deactivateProduct(String, String)}
     */
    @Test
    void testDeactivateProduct2() throws Exception {
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
        Optional<Product> ofResult = Optional.of(product);
        when(productRepository.save(Mockito.<Product>any()))
                .thenThrow(new IllegalArgumentException("DeactivateProductStart"));
        when(productRepository.findByProductUuid(Mockito.<String>any())).thenReturn(ofResult);

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
                () -> productServiceImpl.deactivateProduct("janedoe", "01234567-89AB-CDEF-FEDC-BA9876543210"));
        verify(productRepository).findByProductUuid(Mockito.<String>any());
        verify(userRepository).findByUserName(Mockito.<String>any());
        verify(productRepository).save(Mockito.<Product>any());
    }
}
