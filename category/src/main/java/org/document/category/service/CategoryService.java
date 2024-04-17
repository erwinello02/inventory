package org.document.category.service;

import org.document.category.dto.CategoryDTO;
import org.document.category.dto.UpdateCategoryDTO;
import org.document.common.model.Category;
import org.document.common.utils.QueryResults;
import org.springframework.data.domain.Sort;

public interface CategoryService {
    Category addCategory(String userName, CategoryDTO categoryDTO) throws Exception;
    Category updateCategory(String userName, UpdateCategoryDTO updateCategoryDTO) throws Exception;
    QueryResults<Category> getCategories(String userName, int pageNumber, int pageSize, Sort.Direction sort) throws Exception;
    Category deactivateCategory(String userName, String categoryUuid) throws Exception;

    Category getCategoryByUuid(String userName, String categoryUuid) throws Exception;

}
