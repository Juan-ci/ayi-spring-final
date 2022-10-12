package com.spring.ayi.app.controller;

import com.spring.ayi.app.dto.request.userAccount.UserAccountRequest;
import com.spring.ayi.app.dto.response.userAccount.UserAccountResponse;
import com.spring.ayi.app.dto.response.pagination.GenericListPaginationResponse;
import com.spring.ayi.app.exception.custom.DocumentNumberAlreadyExistException;
import com.spring.ayi.app.exception.custom.MailAlreadyExistException;
import com.spring.ayi.app.exception.custom.UserAccountNotFoundException;
import com.spring.ayi.app.exception.custom.DocumentNumberNotFoundException;
import com.spring.ayi.app.exception.custom.EmptyListException;
import com.spring.ayi.app.exception.custom.PageDoesNotExistException;
import com.spring.ayi.app.service.IUserAccountService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.util.UriComponentsBuilder;

import javax.validation.Valid;

import static com.spring.ayi.app.constants.path.PathUrls.USER_ACCOUNT_PATH;

@AllArgsConstructor
@RestController
@Api(value = "User Account Api", tags = "{User Account Service}")
@RequestMapping(value = "/user-account")
public class UserAccountController {

    private IUserAccountService userAccountService;

    @PostMapping(path = "/createUserAccount", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    @ApiOperation(
            value = "Retrieves a user account created",
            httpMethod = "POST",
            response = UserAccountResponse.class
    )
    @ApiResponses(value = {
            @ApiResponse(code = 201,
                    message = "Body content with all information about user account",
                    response = UserAccountResponse.class),
            @ApiResponse(code = 400,
                    message = "Describes errors on invalid payload received, e.g: missing fields, invalid data form")
    })
    public ResponseEntity<UserAccountResponse> createUserAccount
            (
                    @ApiParam(value = "data of user account", required = true)
                    @Valid @RequestBody UserAccountRequest request
            ) throws DocumentNumberAlreadyExistException, MailAlreadyExistException {
        UserAccountResponse userAccount = userAccountService.createUserAccount(request);
        return new ResponseEntity<>(userAccount, HttpStatus.CREATED);
    }

    @GetMapping(value = "/getAllUserAccounts", produces = MediaType.APPLICATION_JSON_VALUE)
    @ApiOperation(
            value = "Retrieves all Lists Users accounts in a page",
            httpMethod = "GET",
            response = GenericListPaginationResponse.class
    )
    @ApiResponses(value = {
            @ApiResponse(code = 200,
                    message = "Body content with basic information about users accounts",
                    response = GenericListPaginationResponse.class),
            @ApiResponse(
                    code = 400,
                    message = "Describes errors on invalid payload received, e.g: missing fields, invalid data formats, etc.")
    })
    public ResponseEntity<GenericListPaginationResponse<UserAccountResponse>> getAllUserAccountsForPage
            (
                    @ApiParam(value = "page to display", example = "1")
                    @RequestParam(name = "page", defaultValue = "0") Integer page,
                    @ApiParam(value = "number of items per request", example = "1")
                    @RequestParam(name = "size", defaultValue = "5") Integer size,
                    UriComponentsBuilder uriBuilder
            ) throws PageDoesNotExistException, EmptyListException {
        GenericListPaginationResponse<UserAccountResponse> response = userAccountService.getAllUserAccount(USER_ACCOUNT_PATH, page, size, uriBuilder);
        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    @GetMapping(value = "/getUserAccountById/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
    @ApiOperation(
            value = "Retrieves a user account by the id",
            httpMethod = "GET",
            response = UserAccountResponse.class
    )
    @ApiResponses(value = {
            @ApiResponse(
                    code = 200,
                    message = "Body content with all information about a user account"
            ),
            @ApiResponse(
                    code = 400,
                    message = "Describes errors on invalid payload received, e.g: missing fields, invalid data formats, etc.")
    })
    public ResponseEntity<UserAccountResponse> getUserAccountById
            (
                    @ApiParam(name = "id", required = true, value = "User account Id", example = "1")
                    @PathVariable("id") Long idUserAccount
            ) throws UserAccountNotFoundException {
        UserAccountResponse response = userAccountService.getOneUserAccountById(idUserAccount);
        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    @PutMapping(value = "/updateUserAccount/{id}")
    @ApiOperation(
            value = "Retrieves a user account updated",
            httpMethod = "PUT",
            response = UserAccountResponse.class
    )
    @ApiResponses(value = {
            @ApiResponse(code = 200,
                    message = "Body content with all information about user account updated",
                    response = UserAccountResponse.class),
            @ApiResponse(code = 400,
                    message = "Describes errors on invalid payload received, e.g: missing fields, invalid data form")
    })
    public ResponseEntity<UserAccountResponse> updateUserAccount
            (
                    @ApiParam(value = "id of user account to update", required = true, example = "1")
                    @PathVariable(name = "id") Long idUserAccount,
                    @ApiParam(value = "data of user account", required = true)
                    @Valid @RequestBody UserAccountRequest request
            ) throws UserAccountNotFoundException, MailAlreadyExistException {
        UserAccountResponse response = userAccountService.updateUserAccount(idUserAccount, request);
        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    @DeleteMapping(value = "/deleteUserAccountById/{id}")
    @ApiOperation(
            value = "Delete a user account by id",
            httpMethod = "DELETE"
    )
    @ApiResponses(value = {
            @ApiResponse(
                    code = 204,
                    message = "No Body Content"
            ),
            @ApiResponse(
                    code = 404,
                    message = "Describes errors on invalid id which is not found.")
    })
    public ResponseEntity<Void> deleteUserAccountById
            (
                    @ApiParam(name = "id", required = true, value = "User account Id", example = "1")
                    @PathVariable("id") Long idUserAccount
            ) throws UserAccountNotFoundException {
        userAccountService.deleteUserAccountById(idUserAccount);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }
}
