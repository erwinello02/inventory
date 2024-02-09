package org.document.product.controller;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.ArraySchema;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import org.document.common.model.Product;
import org.document.common.utils.Headers;
import org.document.common.utils.QueryResults;
import org.document.product.constant.ProductResponseConstants;
import org.document.product.dto.ProductDTO;
import org.document.product.dto.UpdateProductDTO;
import org.document.product.service.ProductService;
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
@RequestMapping(value = "/product", produces = APPLICATION_JSON_VALUE)
public class ProductController {
    private final Logger logger = LoggerFactory.getLogger(this.getClass());

    @Autowired
    private ProductService productService;

    @Operation(
            summary = "added new product",
            description = "To add new product",
            responses = {
                    @ApiResponse(
                            responseCode = RC201,
                            description = ProductResponseConstants.PRODUCT_SUCCESS,
                            content = {@Content(
                                    mediaType = MediaType.APPLICATION_JSON_VALUE,
                                    schema = @Schema(implementation = ProductDTO.class)
                            )}
                    )
                    ,
                    @ApiResponse(responseCode = RC400),
                    @ApiResponse(responseCode = RC409),
                    @ApiResponse(responseCode = RC500)
            }
    )
    @PostMapping("/add")
    public ResponseEntity<Product> addProduct(
            @RequestHeader(Headers.USERNAME) String userName,
            @Valid @RequestBody ProductDTO productDTO
    ) throws Exception {
        logger.info("Adding a product into product table");
        Product result = productService.addProduct(userName, productDTO);
        return ResponseEntity.status(201).body(result);
    }

    @Operation(
            summary = "update product",
            description = "To update product",
            responses = {
                    @ApiResponse(
                            responseCode = RC201,
                            description = ProductResponseConstants.PRODUCT_UPDATE_SUCCESS,
                            content = {@Content(
                                    mediaType = MediaType.APPLICATION_JSON_VALUE,
                                    schema = @Schema(implementation = UpdateProductDTO.class)
                            )}
                    )
                    ,
                    @ApiResponse(responseCode = RC400),
                    @ApiResponse(responseCode = RC409),
                    @ApiResponse(responseCode = RC500)
            }
    )
    @PatchMapping("/update")
    public ResponseEntity<Product> updateProduct(
            @RequestHeader(Headers.USERNAME) String userName,
            @Valid @RequestBody UpdateProductDTO updateProductDTO
    ) throws Exception {
        logger.info("Updating a product into product table");
        Product result = productService.updateProduct(userName, updateProductDTO);
        return ResponseEntity.status(201).body(result);
    }

    @Operation(
            summary = "Get all product",
            responses = {
                    @ApiResponse(
                            responseCode = RC200,
                            description = ProductResponseConstants.GET_PRODUCT_SUCCESS,
                            content = {@Content(
                                    mediaType = MediaType.APPLICATION_JSON_VALUE,
                                    array = @ArraySchema(
                                            schema = @Schema(
                                                    implementation = Product.class
                                            )))
                            }),
                    @ApiResponse(responseCode = RC500)
            }
    )
    @GetMapping
    public ResponseEntity<QueryResults<Product>> getProducts(
            @RequestHeader(Headers.USERNAME) String userName,
            @RequestParam int pageNumber,
            @RequestParam int pageSize,
            @RequestParam(defaultValue = "DESC") Sort.Direction sort
    ) throws Exception {
        logger.info("Fetching products from Product table");
        QueryResults<Product> products = productService.getProducts(userName, pageNumber, pageSize, sort);
        logger.info("Products retrieved");
        return ResponseEntity.status(200).body(products);
    }

    @Operation(
            summary = "De-activate product",
            description = "To de-activate product",
            responses = {
                    @ApiResponse(
                            responseCode = RC201,
                            description = ProductResponseConstants.PRODUCT_DEACTIVATED_SUCCESS,
                            content = {@Content(
                                    mediaType = MediaType.APPLICATION_JSON_VALUE,
                                    schema = @Schema(implementation = Product.class)
                            )}
                    )
                    ,
                    @ApiResponse(responseCode = RC400),
                    @ApiResponse(responseCode = RC409),
                    @ApiResponse(responseCode = RC500)
            }
    )
    @DeleteMapping("/de-activate/{productUuid}")
    public ResponseEntity<Product> deactivateProduct(
            @RequestHeader(Headers.USERNAME) String userName,
            @Valid @PathVariable String productUuid
    ) throws Exception {
        logger.info("De-activate a product into product table");
        Product result = productService.deactivateProduct(userName, productUuid);
        return ResponseEntity.status(201).body(result);
    }
}
