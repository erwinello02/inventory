package org.document.user.controller;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.ArraySchema;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import org.document.common.model.Users;
import org.document.common.utils.Headers;
import org.document.common.utils.QueryResults;
import org.document.user.constant.UserResponseConstants;
import org.document.user.dto.UpdateUserDTO;
import org.document.user.dto.UserDTO;
import org.document.user.service.UserService;
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
@RequestMapping(value = "/user", produces = APPLICATION_JSON_VALUE)
public class UserController {
    private final Logger logger = LoggerFactory.getLogger(this.getClass());

    @Autowired
    private UserService userService;

    @Operation(
            summary = "added new user account",
            description = "To add new user account",
            responses = {
                    @ApiResponse(
                            responseCode = RC201,
                            description = UserResponseConstants.USER_SUCCESS,
                            content = {@Content(
                                    mediaType = MediaType.APPLICATION_JSON_VALUE,
                                    schema = @Schema(implementation = UserDTO.class)
                            )}
                    )
                    ,
                    @ApiResponse(responseCode = RC400),
                    @ApiResponse(responseCode = RC409),
                    @ApiResponse(responseCode = RC500)
            }
    )
    @PostMapping("/add")
    public ResponseEntity<Users> addUser(
            @RequestHeader(Headers.USERNAME) String userName,
            @Valid @RequestBody UserDTO userDTO
    ) throws Exception {
        logger.info("Adding a user into Users table");
        Users result = userService.addUser(userName, userDTO);
        return ResponseEntity.status(201).body(result);
    }

    @Operation(
            summary = "update user account",
            description = "To update user account",
            responses = {
                    @ApiResponse(
                            responseCode = RC201,
                            description = UserResponseConstants.USER_UPDATE_SUCCESS,
                            content = {@Content(
                                    mediaType = MediaType.APPLICATION_JSON_VALUE,
                                    schema = @Schema(implementation = UpdateUserDTO.class)
                            )}
                    )
                    ,
                    @ApiResponse(responseCode = RC400),
                    @ApiResponse(responseCode = RC409),
                    @ApiResponse(responseCode = RC500)
            }
    )
    @PatchMapping("/update")
    public ResponseEntity<Users> updateUser(
            @RequestHeader(Headers.USERNAME) String userName,
            @Valid @RequestBody UpdateUserDTO updateUserDTO
    ) throws Exception {
        logger.info("Updating a user into Users table");
        Users result = userService.updateUser(userName, updateUserDTO);
        return ResponseEntity.status(201).body(result);
    }

    @Operation(
            summary = "Get all user",
            responses = {
                    @ApiResponse(
                            responseCode = RC200,
                            description = UserResponseConstants.GET_USER_SUCCESS,
                            content = {@Content(
                                    mediaType = MediaType.APPLICATION_JSON_VALUE,
                                    array = @ArraySchema(
                                            schema = @Schema(
                                                    implementation = Users.class
                                            )))
                            }),
                    @ApiResponse(responseCode = RC500)
            }
    )
    @GetMapping
    public ResponseEntity<QueryResults<Users>> getUsers(
            @RequestHeader(Headers.USERNAME) String userName,
            @RequestParam int pageNumber,
            @RequestParam int pageSize,
            @RequestParam(defaultValue = "DESC") Sort.Direction sort
    ) throws Exception {
        logger.info("Fetching users from Users table");
        QueryResults<Users> users = userService.getUsers(userName, pageNumber, pageSize, sort);
        logger.info("Users retrieved");
        return ResponseEntity.status(200).body(users);
    }

    @Operation(
            summary = "Get user by uuid",
            responses = {
                    @ApiResponse(
                            responseCode = RC200,
                            description = UserResponseConstants.GET_USER_SUCCESS,
                            content = {@Content(
                                    mediaType = MediaType.APPLICATION_JSON_VALUE,
                                    array = @ArraySchema(
                                            schema = @Schema(
                                                    implementation = Users.class
                                            )))
                            }),
                    @ApiResponse(responseCode = RC500)
            }
    )
    @GetMapping("/{userUuid}")
    public ResponseEntity<Users> getUserByUuid(
            @RequestHeader(Headers.USERNAME) String userName,
            @PathVariable String userUuid
    ) throws Exception {
        logger.info("Fetching users from Users table");
        Users users = userService.getUserByUuid(userName, userUuid);
        logger.info("Users retrieved by uuid");
        return ResponseEntity.status(200).body(users);
    }

    @Operation(
            summary = "de-activate user account",
            description = "To de-activate user account",
            responses = {
                    @ApiResponse(
                            responseCode = RC201,
                            description = UserResponseConstants.USER_DEACTIVATED_SUCCESS,
                            content = {@Content(
                                    mediaType = MediaType.APPLICATION_JSON_VALUE,
                                    schema = @Schema(implementation = Users.class)
                            )}
                    )
                    ,
                    @ApiResponse(responseCode = RC400),
                    @ApiResponse(responseCode = RC409),
                    @ApiResponse(responseCode = RC500)
            }
    )
    @DeleteMapping("/de-activate/{userUuid}")
    public ResponseEntity<Users> deactivateUser(
            @RequestHeader(Headers.USERNAME) String userName,
            @Valid @PathVariable String userUuid
    ) throws Exception {
        logger.info("De-activate a user into Users table");
        Users result = userService.deactivateUser(userName, userUuid);
        return ResponseEntity.status(201).body(result);
    }
}
