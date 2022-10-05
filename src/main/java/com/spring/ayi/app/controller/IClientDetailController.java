package com.spring.ayi.app.controller;

import com.spring.ayi.app.dto.request.ClientDetailRequest;
import com.spring.ayi.app.dto.response.ClientDetailResponse;
import com.spring.ayi.app.dto.response.GenericListPaginationResponse;
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

public interface IClientDetailController {

    @PostMapping(path = "/createClientDetail",consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    @ApiOperation(
            value = "Retrieves a client detail created",
            httpMethod = "POST",
            response = ClientDetailResponse.class
    )
    @ApiResponses(value = {
            @ApiResponse(code = 201,
                    message = "Body content with all information about client detail",
                    response = ClientDetailResponse.class),
            @ApiResponse(code = 400,
                    message = "Describes errors on invalid payload received, e.g: missing fields, invalid data form")
    })
    ResponseEntity<?> createClientDetail(
            @ApiParam(value = "data of client detail", required = true)
            @RequestBody ClientDetailRequest request
    );

    @GetMapping(value = "/getAllClientDetails", produces = MediaType.APPLICATION_JSON_VALUE)
    @ApiOperation(
            value = "Retrieves all Lists Clients details in a page",
            httpMethod = "GET",
            response = GenericListPaginationResponse.class
    )
    @ApiResponses(value = {
            @ApiResponse(code = 200,
                    message = "Body content with basic information about clients details",
                    response = GenericListPaginationResponse.class),
            @ApiResponse(
                    code = 400,
                    message = "Describes errors on invalid payload received, e.g: missing fields, invalid data formats, etc.")
    })
    ResponseEntity<?> getAllClientDetailsForPage(
            @ApiParam(value = "page to display", example = "1")
            @RequestParam(name = "page", defaultValue = "0") Integer page,
            @ApiParam(value = "number of items per request", example = "1")
            @RequestParam(name = "size", defaultValue = "5") Integer size,
            UriComponentsBuilder uriBuilder);

    @GetMapping(value = "/getClientDetailById/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
    @ApiOperation(
            value = "Retrieves a client detail by the id",
            httpMethod = "GET",
            response = ClientDetailResponse.class
    )
    @ApiResponses(value = {
            @ApiResponse(
                    code = 200,
                    message = "Body content with all information about a client detail"
            ),
            @ApiResponse(
                    code = 400,
                    message = "Describes errors on invalid payload received, e.g: missing fields, invalid data formats, etc.")
    })
    ResponseEntity<?> getClientDetailById(
            @ApiParam(name = "id", required = true, value = "Client detail Id", example = "1")
            @PathVariable("id") Long id);

    @PutMapping(value = "/updateClientDetail/{id}")
    @ApiOperation(
            value = "Retrieves a client detail updated",
            httpMethod = "PUT",
            response = ClientDetailResponse.class
    )
    @ApiResponses(value = {
            @ApiResponse(code = 200,
                    message = "Body content with all information about client detail updated",
                    response = ClientDetailResponse.class),
            @ApiResponse(code = 400,
                    message = "Describes errors on invalid payload received, e.g: missing fields, invalid data form")
    })
    ResponseEntity<?> updateClientDetail(
            @ApiParam(value = "id of client detail to update", required = true, example = "1")
            @PathVariable(name = "id") Long idClientDetail,
            @ApiParam(value = "data of client", required = true)
            @RequestBody ClientDetailRequest request
    );

    @DeleteMapping(value = "/deleteClientDetailById/{id}")
    @ApiOperation(
            value = "Delete a client detail by id",
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
    ResponseEntity deleteClientDetailById(
            @ApiParam(name = "id", required = true, value = "Client detail Id", example = "1")
            @PathVariable("id") Long idClientDetail);
}
