package org.document.product.service;

import org.document.common.model.Product;
import org.document.common.utils.QueryResults;
import org.document.product.dto.ProductDTO;
import org.document.product.dto.UpdateProductDTO;
import org.springframework.data.domain.Sort;

public interface ProductService {
    Product addProduct(ProductDTO productDTO) throws Exception;
    Product updateProduct(UpdateProductDTO updateProductDTO) throws Exception;
    QueryResults<Product> getProducts(int pageNumber, int pageSize, Sort.Direction sort) throws Exception;
    Product deactivateProduct(String productUuid) throws Exception;
}