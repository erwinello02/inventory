package org.document.category.service.impl;

import org.document.category.dto.CategoryDTO;
import org.document.category.dto.UpdateCategoryDTO;
import org.document.category.repository.CategoryRepository;
import org.document.category.service.CategoryService;
import org.document.category.utils.CategoryBuilder;
import org.document.category.utils.CategoryCondition;
import org.document.category.utils.CategoryValidation;
import org.document.common.enums.CategoryStatus;
import org.document.common.model.Category;
import org.document.common.utils.QueryResults;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.time.Instant;
import java.util.List;
import java.util.stream.Collectors;

import static org.document.common.utils.ErrorCodes.CAT005;

@Service
public class CategoryServiceImpl implements CategoryService {

    private final Logger logger = LoggerFactory.getLogger(this.getClass());

    @Autowired
    private CategoryRepository categoryRepository;

    @Override
    public Category addCategory(CategoryDTO categoryDTO) throws Exception {
        Instant createCategoryStart = Instant.now();
        CategoryValidation.addCategoryValidation(categoryDTO);
        Category addCategory = CategoryBuilder.addCategoryBuilder(categoryDTO);
        Category saveCategory = categoryRepository.save(addCategory);
        logger.info("CreateCategoryStart" + createCategoryStart);
        return saveCategory;
    }

    @Override
    public Category updateCategory(UpdateCategoryDTO updateCategoryDTO) throws Exception {
        Instant updateCategoryStart = Instant.now();
        Category category = categoryRepository.findByCategoryUuid(updateCategoryDTO.getCategoryUuid())
                .orElseThrow(() -> new IllegalArgumentException(CAT005(updateCategoryDTO.getCategoryUuid())));
        Category updateCategory = CategoryCondition.updateCategoryCondition(updateCategoryDTO, category);
        Category updateSaveCategory = categoryRepository.save(updateCategory);
        logger.info("UpdateCategoryStart" + updateCategoryStart);
        return updateSaveCategory;
    }

    @Override
    public QueryResults<Category> getCategories(int pageNumber, int pageSize, Sort.Direction sort) throws Exception {
        Pageable pageable = PageRequest.of(pageNumber - 1, pageSize, Sort.by(sort, "code"));
        Page<Category> cat = categoryRepository.findAll(pageable);
        Long totalCount = cat.getTotalElements();
        List<Category> result = cat.stream().collect(Collectors.toList());
        return new QueryResults(result, totalCount);
    }

    @Override
    public Category deactivateCategory(String categoryUuid) throws Exception{
        Instant deactivateCategoryStart = Instant.now();
        Category category = categoryRepository.findByCategoryUuid(categoryUuid)
                .orElseThrow(() -> new IllegalArgumentException(CAT005(categoryUuid)));
        category.setStatus(CategoryStatus.DEACTIVATE);
        Category deactivateCategory = categoryRepository.save(category);
        logger.info("DeactivateCategoryStart" + deactivateCategoryStart);
        return deactivateCategory;
    }
}
