package com.spring.ayi.app.controller;

import com.spring.ayi.app.dto.request.AddressRequest;
import com.spring.ayi.app.dto.response.AddressResponse;
import com.spring.ayi.app.dto.response.GenericListPaginationResponse;
import com.spring.ayi.app.exception.AddressNotFoundException;
import com.spring.ayi.app.exception.DocumentNumberNotFoundException;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.util.UriComponentsBuilder;

public interface IAddressController {

    @PostMapping(value = "/create-address", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    @ApiOperation(
            value = "Retrieves a address created",
            httpMethod = "POST",
            response = AddressResponse.class
    )
    @ApiResponses(value = {
            @ApiResponse(code = 201,
                    message = "Body content with all information about address",
                    response = AddressResponse.class),
            @ApiResponse(code = 400,
                    message = "Describes errors on invalid payload received, e.g: missing fields, invalid data form")
    })
    ResponseEntity<AddressResponse> createAddress(
            @ApiParam(value = "data of address", required = true)
            @RequestBody AddressRequest request
    ) throws DocumentNumberNotFoundException;

    @GetMapping(value = "/getAllAddress", produces = MediaType.APPLICATION_JSON_VALUE)
    @ApiOperation(
            value = "Retrieves all Lists Address in a page",
            httpMethod = "GET",
            response = GenericListPaginationResponse.class
    )
    @ApiResponses(value = {
            @ApiResponse(code = 200,
                    message = "Body content with basic information about address",
                    response = GenericListPaginationResponse.class),
            @ApiResponse(
                    code = 400,
                    message = "Describes errors on invalid payload received, e.g: missing fields, invalid data formats, etc.")
    })
    ResponseEntity<GenericListPaginationResponse<AddressResponse>> getAllAddressForPage(
            @ApiParam(value = "page to display", example = "1")
            @RequestParam(name = "page", defaultValue = "0") Integer page,
            @ApiParam(value = "number of items per request", example = "1")
            @RequestParam(name = "size", defaultValue = "5") Integer size,
            UriComponentsBuilder uriBuilder);

    @GetMapping(value = "/getAddressById/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
    @ApiOperation(
            value = "Retrieves an address by the id",
            httpMethod = "GET",
            response = AddressResponse.class
    )
    @ApiResponses(value = {
            @ApiResponse(
                    code = 200,
                    message = "Body content with all information about an address"
            ),
            @ApiResponse(
                    code = 400,
                    message = "Describes errors on invalid payload received, e.g: missing fields, invalid data formats, etc.")
    })
    ResponseEntity<AddressResponse> getAddressById(
            @ApiParam(name = "id", required = true, value = "Address Id", example = "1")
            @PathVariable("id") Long idAddress) throws AddressNotFoundException;

    @PutMapping(value = "/updateAddress/{id}")
    @ApiOperation(
            value = "Retrieves an address updated",
            httpMethod = "PUT",
            response = AddressResponse.class
    )
    @ApiResponses(value = {
            @ApiResponse(code = 200,
                    message = "Body content with all information about address updated",
                    response = AddressResponse.class),
            @ApiResponse(code = 400,
                    message = "Describes errors on invalid payload received, e.g: missing fields, invalid data form")
    })
    ResponseEntity<AddressResponse> updateAddress(
            @ApiParam(value = "id of address to update", required = true, example = "1")
            @PathVariable(name = "id") Long idAddress,
            @ApiParam(value = "data of address", required = true)
            @RequestBody AddressRequest request
    ) throws AddressNotFoundException;

    @DeleteMapping(value = "/deleteAddressById/{id}")
    @ApiOperation(
            value = "Delete an address by id",
            httpMethod = "DELETE"
    )
    @ApiResponses(value = {
            @ApiResponse(
                    code = 204,
                    message = "No Body Content"
            ),
            @ApiResponse(
                    code = 404,
                    message = "Describes errors on invalid id wich is not found.")
    })
    ResponseEntity<Void> deleteAddressById(
            @ApiParam(name = "id", required = true, value = "Address Id", example = "1")
            @PathVariable("id") Long idAddress) throws AddressNotFoundException;
}
