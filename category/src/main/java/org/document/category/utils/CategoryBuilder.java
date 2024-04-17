package org.document.category.utils;

import org.document.category.dto.CategoryDTO;
import org.document.common.enums.CategoryStatus;
import org.document.common.model.Category;
import org.document.common.utils.Utils;

import java.time.Instant;
import java.util.Date;

public class CategoryBuilder {
    public static Category addCategoryBuilder(String userName, CategoryDTO categoryDTO) throws Exception{
        Category category = new Category();
        // Auditable details
        category.setCreatedBy(userName);
        category.setCreationDate(Date.from(Instant.now()));
        category.setLastModifiedBy(userName);
        category.setLastModifiedDate(Date.from(Instant.now()));
        // Category details
        category.setCategoryUuid(Utils.getGeneratedUuid());
        category.setCode(categoryDTO.getCode());
        category.setImage(categoryDTO.getImage());
        category.setDescription(categoryDTO.getDescription());
        category.setStatus(CategoryStatus.INACTIVE);
        category.setCategoryName(categoryDTO.getCategoryName());
        return category;
    }
}
