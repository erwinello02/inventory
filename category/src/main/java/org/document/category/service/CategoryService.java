package org.document.category.service;

import org.document.category.dto.CategoryDTO;
import org.document.category.dto.UpdateCategoryDTO;
import org.document.common.model.Category;
import org.document.common.utils.QueryResults;
import org.springframework.data.domain.Sort;

public interface CategoryService {
    Category addCategory(CategoryDTO categoryDTO) throws Exception;
    Category updateCategory(UpdateCategoryDTO updateCategoryDTO) throws Exception;
    QueryResults<Category> getCategories(int pageNumber, int pageSize, Sort.Direction sort) throws Exception;
    Category deactivateCategory(String categoryUuid) throws Exception;
}
