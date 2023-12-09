package org.document.product.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
public class ProductDTO {
    private String productName;
    private Long categoryId;
    private String subCategory;
    private Long unit;
    private String stock;
    private String minimumQty;
    private String quantity;
    private String description;
    private Long tax;
    private Long discountType;
    private Long price;
    private String image;
}
