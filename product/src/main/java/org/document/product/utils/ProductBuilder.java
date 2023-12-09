package org.document.product.utils;

import org.document.common.enums.ProductStatus;
import org.document.common.model.Category;
import org.document.common.model.Product;
import org.document.common.utils.Utils;
import org.document.product.dto.ProductDTO;

public class ProductBuilder {
    public static Product addProductBuilder(
            ProductDTO productDTO,
            Category category
    ) throws Exception{
        Product product = new Product();;
        product.setProductUuid(Utils.getGeneratedUuid());
        product.setProductName(productDTO.getProductName());
        product.setCategory(category);
        product.setSubCategory(productDTO.getSubCategory());
        product.setUnit(productDTO.getTax());
        product.setStock(productDTO.getStock());
        product.setMinimumQty(productDTO.getMinimumQty());
        product.setQuantity(productDTO.getQuantity());
        product.setDescription(productDTO.getDescription());
        product.setTax(productDTO.getTax());
        product.setDiscountType(productDTO.getDiscountType());
        product.setPrice(productDTO.getPrice());
        product.setStatus(ProductStatus.INACTIVE);
        product.setImage(productDTO.getImage());
        return product;
    }
}
