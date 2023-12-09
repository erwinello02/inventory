package org.document.product.utils;

import org.document.product.dto.ProductDTO;

import static org.document.common.utils.ErrorCodes.*;

public class ProductValidation {
    public static void addProductValidation(ProductDTO productDTO) throws Exception {
        if(productDTO.getProductName() == null){
            throw new IllegalAccessException(PRD001);
        } else if(productDTO.getCategoryId() == null){
            throw new IllegalAccessException(PRD002);
        } else if(productDTO.getSubCategory() == null){
            throw new IllegalAccessException(PRD003);
        } else if(productDTO.getUnit() == null){
            throw new IllegalAccessException(PRD004);
        } else if(productDTO.getStock() == null){
            throw new IllegalAccessException(PRD005);
        } else if(productDTO.getMinimumQty() == null){
            throw new IllegalAccessException(PRD006);
        } else if(productDTO.getQuantity() == null){
            throw new IllegalAccessException(PRD007);
        } else if(productDTO.getDescription() == null){
            throw new IllegalAccessException(PRD008);
        } else if(productDTO.getTax() == null){
            throw new IllegalAccessException(PRD009);
        } else if(productDTO.getDiscountType() == null){
            throw new IllegalAccessException(PRD010);
        } else if(productDTO.getPrice() == null){
            throw new IllegalAccessException(PRD011);
        } else if(productDTO.getImage() == null){
            throw new IllegalAccessException(PRD012);
        }
    }
}