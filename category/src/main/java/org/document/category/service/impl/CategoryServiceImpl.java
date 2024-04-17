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
import org.document.user.repository.UserRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.sql.Date;
import java.time.Instant;
import java.util.List;
import java.util.stream.Collectors;

import static org.document.common.utils.ErrorCodes.CAT005;
import static org.document.common.utils.ErrorCodes.USR009;

@Service
public class CategoryServiceImpl implements CategoryService {

    private final Logger logger = LoggerFactory.getLogger(this.getClass());

    @Autowired
    private CategoryRepository categoryRepository;

    @Autowired
    private UserRepository userRepository;

    @Override
    public Category addCategory(String userName, CategoryDTO categoryDTO) throws Exception {
        Instant createCategoryStart = Instant.now();
        userRepository.findByUserName(userName).orElseThrow(() -> new IllegalArgumentException(USR009(userName)));
        CategoryValidation.addCategoryValidation(categoryDTO);
        Category addCategory = CategoryBuilder.addCategoryBuilder(userName, categoryDTO);
        Category saveCategory = categoryRepository.save(addCategory);
        logger.info("CreateCategoryStart" + createCategoryStart);
        return saveCategory;
    }

    @Override
    public Category updateCategory(String userName, UpdateCategoryDTO updateCategoryDTO) throws Exception {
        Instant updateCategoryStart = Instant.now();
        userRepository.findByUserName(userName).orElseThrow(() -> new IllegalArgumentException(USR009(userName)));
        Category category = categoryRepository.findByCategoryUuid(updateCategoryDTO.getCategoryUuid())
                .orElseThrow(() -> new IllegalArgumentException(CAT005(updateCategoryDTO.getCategoryUuid())));
        Category updateCategory = CategoryCondition.updateCategoryCondition(userName, updateCategoryDTO, category);
        Category updateSaveCategory = categoryRepository.save(updateCategory);
        logger.info("UpdateCategoryStart" + updateCategoryStart);
        return updateSaveCategory;
    }

    @Override
    public QueryResults<Category> getCategories(String userName, int pageNumber, int pageSize, Sort.Direction sort) throws Exception {
        userRepository.findByUserName(userName).orElseThrow(() -> new IllegalArgumentException(USR009(userName)));
        Pageable pageable = PageRequest.of(pageNumber - 1, pageSize, Sort.by(sort, "lastModifiedDate"));
        Page<Category> cat = categoryRepository.findAll(pageable);
        Long totalCount = cat.getTotalElements();
        List<Category> result = cat.stream().collect(Collectors.toList());
        return new QueryResults<>(result, totalCount);
    }

    @Override
    public Category getCategoryByUuid(String userName, String categoryUuid) throws Exception {
        userRepository.findByUserName(userName).orElseThrow(() -> new IllegalArgumentException(USR009(userName)));
        return categoryRepository.findByCategoryUuid(categoryUuid)
                .orElseThrow(() -> new IllegalArgumentException(CAT005(categoryUuid)));
    }

    @Override
    public Category deactivateCategory(String userName, String categoryUuid) throws Exception{
        Instant deactivateCategoryStart = Instant.now();
        userRepository.findByUserName(userName).orElseThrow(() -> new IllegalArgumentException(USR009(userName)));
        Category category = categoryRepository.findByCategoryUuid(categoryUuid)
                .orElseThrow(() -> new IllegalArgumentException(CAT005(categoryUuid)));
        category.setStatus(CategoryStatus.DEACTIVATE);
        category.setLastModifiedDate(Date.from(Instant.now()));
        category.setLastModifiedBy(userName);
        Category deactivateCategory = categoryRepository.save(category);
        logger.info("DeactivateCategoryStart" + deactivateCategoryStart);
        return deactivateCategory;
    }
}
