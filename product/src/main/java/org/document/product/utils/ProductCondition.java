package org.document.product.utils;

import org.document.common.model.Category;
import org.document.common.model.Product;
import org.document.product.dto.UpdateProductDTO;

public class ProductCondition {
    public static Product updateProductCondition(
            UpdateProductDTO updateProductDTO,
            Category category,
            Product product
    ) throws Exception {
        if(updateProductDTO.getProductName() != null){
            product.setProductName(updateProductDTO.getProductName());
        }
        if(updateProductDTO.getCategoryId() != null){
            product.setCategory(category);
        }
        if(updateProductDTO.getSubCategory() != null){
            product.setSubCategory(updateProductDTO.getSubCategory());
        }
        if(updateProductDTO.getUnit() != null){
            product.setUnit(updateProductDTO.getUnit());
        }
        if(updateProductDTO.getStock() != null){
            product.setStock(updateProductDTO.getStock());
        }
        if(updateProductDTO.getMinimumQty() != null){
            product.setMinimumQty(updateProductDTO.getMinimumQty());
        }
        if(updateProductDTO.getQuantity() != null){
            product.setQuantity(updateProductDTO.getQuantity());
        }
        if(updateProductDTO.getDescription() != null){
            product.setDescription(updateProductDTO.getDescription());
        }
        if(updateProductDTO.getTax() != null){
            product.setTax(updateProductDTO.getTax());
        }
        if(updateProductDTO.getDiscountType() != null){
            product.setDiscountType(updateProductDTO.getDiscountType());
        }
        if(updateProductDTO.getPrice() != null){
            product.setPrice(updateProductDTO.getPrice());
        }
        if(updateProductDTO.getStatus() != null){
            product.setStatus(updateProductDTO.getStatus());
        }
        if(updateProductDTO.getImage() != null){
            product.setImage(updateProductDTO.getImage());
        }
        return product;
    }
}
