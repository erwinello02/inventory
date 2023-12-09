package org.document.category.utils;

import org.document.category.dto.CategoryDTO;
import org.document.common.enums.CategoryStatus;
import org.document.common.model.Category;
import org.document.common.utils.Utils;

public class CategoryBuilder {
    public static Category addCategoryBuilder(CategoryDTO categoryDTO) throws Exception{
        Category category = new Category();
        category.setCategoryUuid(Utils.getGeneratedUuid());
        category.setCode(categoryDTO.getCode());
        category.setImage(categoryDTO.getImage());
        category.setDescription(categoryDTO.getDescription());
        category.setStatus(CategoryStatus.INACTIVE);
        return category;
    }
}
