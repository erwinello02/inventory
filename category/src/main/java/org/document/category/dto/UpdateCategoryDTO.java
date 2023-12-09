package org.document.category.dto;

import lombok.Getter;
import lombok.Setter;
import org.document.common.enums.CategoryStatus;

@Getter
@Setter
public class UpdateCategoryDTO {
    private String categoryUuid;
    private String code;
    private String description;
    private String image;
    private CategoryStatus status;
}
