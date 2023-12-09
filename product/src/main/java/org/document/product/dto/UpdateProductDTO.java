package org.document.product.dto;

import lombok.Getter;
import lombok.Setter;
import org.document.common.enums.ProductStatus;

@Getter
@Setter
public class UpdateProductDTO {
    private String productUuid;
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
    private ProductStatus status;
    private String image;
}
