package org.document.category.utils;

import org.document.category.dto.UpdateCategoryDTO;
import org.document.common.model.Category;

public class CategoryCondition {
    public static Category updateCategoryCondition(
        UpdateCategoryDTO updateCategoryDTO,
        Category category
    ) throws Exception{
        if(updateCategoryDTO.getCode() != null){
            category.setCode(updateCategoryDTO.getCode());
        }
        if(updateCategoryDTO.getDescription() != null){
            category.setDescription(updateCategoryDTO.getDescription());
        }
        if(updateCategoryDTO.getImage() != null){
            category.setImage(updateCategoryDTO.getImage());
        }
        if(updateCategoryDTO.getStatus() != null){
            category.setStatus(updateCategoryDTO.getStatus());
        }
        return category;
    }
}
