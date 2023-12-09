package org.document.category.utils;

import org.document.category.dto.CategoryDTO;

import static org.document.common.utils.ErrorCodes.*;

public class CategoryValidation {
    public static void addCategoryValidation(CategoryDTO categoryDTO) throws Exception {
        if(categoryDTO.getCode() == null){
            throw new IllegalAccessException(CAT001);
        } else if(categoryDTO.getDescription() == null){
            throw new IllegalAccessException(CAT002);
        } else if(categoryDTO.getImage() == null){
            throw new IllegalAccessException(CAT003);
        }
    }
}
