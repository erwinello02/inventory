package org.document.product.service;

import org.document.common.model.Product;
import org.document.common.utils.QueryResults;
import org.document.product.dto.ProductDTO;
import org.document.product.dto.UpdateProductDTO;
import org.springframework.data.domain.Sort;

public interface ProductService {
    Product addProduct(String userName, ProductDTO productDTO) throws Exception;
    Product updateProduct(String userName, UpdateProductDTO updateProductDTO) throws Exception;
    QueryResults<Product> getProducts(String userName, int pageNumber, int pageSize, Sort.Direction sort) throws Exception;
    Product deactivateProduct(String userName, String productUuid) throws Exception;

    Product getProductByUuid(String userName, String productUuid) throws Exception;

}