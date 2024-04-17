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
import org.document.common.utils.Headers;
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

@CrossOrigin("*")
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
                    @ApiResponse(responseCode = RC400),
                    @ApiResponse(responseCode = RC409),
                    @ApiResponse(responseCode = RC500)
            }
    )
    @PostMapping("/add")
    public ResponseEntity<Category> addCategory(
            @RequestHeader(Headers.USERNAME) String userName,
            @Valid @RequestBody CategoryDTO categoryDTO
    ) throws Exception {
        logger.info("Adding a category into category table");
        Category result = categoryService.addCategory(userName, categoryDTO);
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
                    @ApiResponse(responseCode = RC400),
                    @ApiResponse(responseCode = RC409),
                    @ApiResponse(responseCode = RC500)
            }
    )
    @PatchMapping("/update")
    public ResponseEntity<Category> updateCategory(
            @RequestHeader(Headers.USERNAME) String userName,
            @Valid @RequestBody UpdateCategoryDTO updateCategoryDTO
    ) throws Exception {
        logger.info("Updating a category into category table");
        Category result = categoryService.updateCategory(userName, updateCategoryDTO);
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
                    @ApiResponse(responseCode = RC500)
            }
    )
    @GetMapping
    public ResponseEntity<QueryResults<Category>> getCategories(
            @RequestHeader(Headers.USERNAME) String userName,
            @RequestParam int pageNumber,
            @RequestParam int pageSize,
            @RequestParam(defaultValue = "DESC") Sort.Direction sort
    ) throws Exception {
        logger.info("Fetching categories from Category table");
        QueryResults<Category> categories = categoryService.getCategories(userName, pageNumber, pageSize, sort);
        logger.info("Categories retrieved");
        return ResponseEntity.status(200).body(categories);
    }

    @Operation(
            summary = "Get category by uuid",
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
                    @ApiResponse(responseCode = RC500)
            }
    )
    @GetMapping("/{categoryUuid}")
    public ResponseEntity<Category> getCategoryByUuid(
            @RequestHeader(Headers.USERNAME) String userName,
            @PathVariable String categoryUuid
    ) throws Exception {
        logger.info("Fetching categories from Category table");
        Category category = categoryService.getCategoryByUuid(userName, categoryUuid);
        logger.info("Categories retrieved");
        return ResponseEntity.status(200).body(category);
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
                    @ApiResponse(responseCode = RC400),
                    @ApiResponse(responseCode = RC409),
                    @ApiResponse(responseCode = RC500)
            }
    )
    @DeleteMapping("/de-activate/{categoryUuid}")
    public ResponseEntity<Category> deactivateCategory(
            @RequestHeader(Headers.USERNAME) String userName,
            @Valid @PathVariable String categoryUuid
    ) throws Exception {
        logger.info("De-activate a category into category table");
        Category result = categoryService.deactivateCategory(userName, categoryUuid);
        return ResponseEntity.status(201).body(result);
    }
}
