package org.document.product.service.impl;

import org.document.category.repository.CategoryRepository;
import org.document.common.enums.ProductStatus;
import org.document.common.model.Category;
import org.document.common.model.Product;
import org.document.common.utils.QueryResults;
import org.document.product.dto.ProductDTO;
import org.document.product.dto.UpdateProductDTO;
import org.document.product.repository.ProductRepository;
import org.document.product.service.ProductService;
import org.document.product.utils.ProductBuilder;
import org.document.product.utils.ProductCondition;
import org.document.product.utils.ProductValidation;
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

import static org.document.common.utils.ErrorCodes.*;

@Service
public class ProductServiceImpl implements ProductService {

    private final Logger logger = LoggerFactory.getLogger(this.getClass());

    @Autowired
    private ProductRepository productRepository;

    @Autowired
    private CategoryRepository categoryRepository;

    @Override
    public Product addProduct(ProductDTO productDTO) throws Exception {
        Instant createProductStart = Instant.now();
        ProductValidation.addProductValidation(productDTO);
        Category category = categoryRepository.findById(productDTO.getCategoryId())
                .orElseThrow(() -> new IllegalArgumentException(CAT004(productDTO.getCategoryId())));
        Product addProduct = ProductBuilder.addProductBuilder(productDTO, category);
        Product saveProduct = productRepository.save(addProduct);
        logger.info("CreateProductStart" + createProductStart);
        return saveProduct;
    }

    @Override
    public Product updateProduct(UpdateProductDTO updateProductDTO) throws Exception {
        Instant updateProductStart = Instant.now();
        Product product = productRepository.findByProductUuid(updateProductDTO.getProductUuid())
                .orElseThrow(() -> new IllegalArgumentException(PRD013(updateProductDTO.getProductUuid())));
        Category category = categoryRepository.findById(updateProductDTO.getCategoryId())
                .orElseThrow(() -> new IllegalArgumentException(CAT004(updateProductDTO.getCategoryId())));
        Product updateProduct = ProductCondition.updateProductCondition(updateProductDTO, category, product);
        Product saveUpdateProduct = productRepository.save(updateProduct);
        logger.info("UpdateProductStart" + updateProductStart);
        return saveUpdateProduct;
    }

    @Override
    public QueryResults<Product> getProducts(int pageNumber, int pageSize, Sort.Direction sort) throws Exception {
        Pageable pageable = PageRequest.of(pageNumber - 1, pageSize, Sort.by(sort, "productName"));
        Page<Product> prod = productRepository.findAll(pageable);
        Long totalCount = prod.getTotalElements();
        List<Product> result = prod.stream().collect(Collectors.toList());
        return new QueryResults<>(result, totalCount);
    }

    public Product deactivateProduct(String productUuid) throws Exception{
        Instant deactivateProductStart = Instant.now();
        Product product = productRepository.findByProductUuid(productUuid)
                .orElseThrow(() -> new IllegalArgumentException(PRD013(productUuid)));
        product.setStatus(ProductStatus.DEACTIVATE);
        Product deactivateProduct = productRepository.save(product);
        logger.info("DeactivateProductStart" + deactivateProductStart);
        return deactivateProduct;
    }
}