package org.document.category.controller;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.ArraySchema;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import org.document.category.constant.CategoryResponseConstants;
import org.document.category.dto.CategoryDTO;
import org.document.category.dto.UpdateCategoryDTO;
import org.document.category.service.CategoryService;
import org.document.common.model.Category;
import org.document.common.utils.QueryResults;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

import static org.document.common.utils.ResponseCodes.*;
import static org.springframework.http.MediaType.APPLICATION_JSON_VALUE;

@RestController
@RequestMapping(value = "/category", produces = APPLICATION_JSON_VALUE)
public class CategoryController {
    private final Logger logger = LoggerFactory.getLogger(this.getClass());

    @Autowired
    private CategoryService categoryService;

    @Operation(
            summary = "added new category",
            description = "To add new category for the product",
            responses = {
                    @ApiResponse(
                            responseCode = RC201,
                            description = CategoryResponseConstants.CATEGORY_SUCCESS,
                            content = {@Content(
                                    mediaType = MediaType.APPLICATION_JSON_VALUE,
                                    schema = @Schema(implementation = CategoryDTO.class)
                            )}
                    )
                    ,
                    @ApiResponse(responseCode = RC400, ref = RC400R),
                    @ApiResponse(responseCode = RC409, ref = RC409R),
                    @ApiResponse(responseCode = RC500, ref = RC500R)
            }
    )
    @PostMapping("/add")
    public ResponseEntity<Category> addCategory(@Valid @RequestBody CategoryDTO categoryDTO) throws Exception {
        logger.debug("Adding a category into category table");
        Category result = categoryService.addCategory(categoryDTO);
        return ResponseEntity.status(201).body(result);
    }

    @Operation(
            summary = "update category",
            description = "To update category for the product",
            responses = {
                    @ApiResponse(
                            responseCode = RC201,
                            description = CategoryResponseConstants.CATEGORY_UPDATE_SUCCESS,
                            content = {@Content(
                                    mediaType = MediaType.APPLICATION_JSON_VALUE,
                                    schema = @Schema(implementation = UpdateCategoryDTO.class)
                            )}
                    )
                    ,
                    @ApiResponse(responseCode = RC400, ref = RC400R),
                    @ApiResponse(responseCode = RC409, ref = RC409R),
                    @ApiResponse(responseCode = RC500, ref = RC500R)
            }
    )
    @PatchMapping("/update")
    public ResponseEntity<Category> updateCategory(@Valid @RequestBody UpdateCategoryDTO updateCategoryDTO) throws Exception {
        logger.debug("Updating a category into category table");
        Category result = categoryService.updateCategory(updateCategoryDTO);
        return ResponseEntity.status(201).body(result);
    }

    @Operation(
            summary = "Get all category",
            responses = {
                    @ApiResponse(
                            responseCode = RC200,
                            description = CategoryResponseConstants.GET_CATEGORY_SUCCESS,
                            content = {@Content(
                                    mediaType = MediaType.APPLICATION_JSON_VALUE,
                                    array = @ArraySchema(
                                            schema = @Schema(
                                                    implementation = Category.class
                                            )))
                            }),
                    @ApiResponse(responseCode = RC500, ref = RC500R)
            }
    )
    @GetMapping
    public ResponseEntity<QueryResults<Category>> getCategories(
            @RequestParam int pageNumber,
            @RequestParam int pageSize,
            @RequestParam(defaultValue = "DESC") Sort.Direction sort
    ) throws Exception {
        logger.debug("Fetching categories from Category table");
        QueryResults<Category> categories = categoryService.getCategories(pageNumber, pageSize, sort);
        logger.debug("Categories retrieved");
        return ResponseEntity.status(200).body(categories);
    }

    @Operation(
            summary = "de-activate category",
            description = "To de-activate category for the product",
            responses = {
                    @ApiResponse(
                            responseCode = RC201,
                            description = CategoryResponseConstants.CATEGORY_DEACTIVATED_SUCCESS,
                            content = {@Content(
                                    mediaType = MediaType.APPLICATION_JSON_VALUE,
                                    schema = @Schema(implementation = Category.class)
                            )}
                    )
                    ,
                    @ApiResponse(responseCode = RC400, ref = RC400R),
                    @ApiResponse(responseCode = RC409, ref = RC409R),
                    @ApiResponse(responseCode = RC500, ref = RC500R)
            }
    )
    @DeleteMapping("/de-activate/{categoryUuid}")
    public ResponseEntity<Category> deactivateCategory(@Valid @PathVariable String categoryUuid) throws Exception {
        logger.debug("De-activate a category into category table");
        Category result = categoryService.deactivateCategory(categoryUuid);
        return ResponseEntity.status(201).body(result);
    }
}
