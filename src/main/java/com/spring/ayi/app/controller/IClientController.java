package com.spring.ayi.app.controller;

import com.spring.ayi.app.dto.request.ClientRequest;
import com.spring.ayi.app.dto.response.ClientResponse;
import com.spring.ayi.app.dto.response.GenericListPaginationResponse;
import com.spring.ayi.app.exception.ClientNotFoundException;
import com.spring.ayi.app.exception.DocumentNumberAlreadyExistException;
import com.spring.ayi.app.exception.EmptyListException;
import com.spring.ayi.app.exception.PageDoesNotExistException;
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

public interface IClientController {

    @PostMapping(value = "/create-client", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    @ApiOperation(
            value = "Retrieves a client created",
            httpMethod = "POST",
            response = ClientResponse.class
    )
    @ApiResponses(value = {
            @ApiResponse(code = 201,
                    message = "Body content with all information about client",
                    response = ClientResponse.class),
            @ApiResponse(code = 400,
                    message = "Describes errors on invalid payload received, e.g: missing fields, invalid data form")
    })
    ResponseEntity<ClientResponse> createClient(
            @ApiParam(value = "data of client", required = true)
            @RequestBody ClientRequest request
    ) throws DocumentNumberAlreadyExistException;

    @GetMapping(value = "/getAllClients", produces = MediaType.APPLICATION_JSON_VALUE)
    @ApiOperation(
            value = "Retrieves all Lists Clients in a page",
            httpMethod = "GET",
            response = GenericListPaginationResponse.class
    )
    @ApiResponses(value = {
            @ApiResponse(code = 200,
                    message = "Body content with basic information about clients",
                    response = GenericListPaginationResponse.class),
            @ApiResponse(
                    code = 400,
                    message = "Describes errors on invalid payload received, e.g: missing fields, invalid data formats, etc.")
    })
    ResponseEntity<GenericListPaginationResponse<ClientResponse>> getAllClientsForPage(
            @ApiParam(value = "page to display", example = "1")
            @RequestParam(name = "page", defaultValue = "0") Integer page,
            @ApiParam(value = "number of items per request", example = "1")
            @RequestParam(name = "size", defaultValue = "5") Integer size,
            UriComponentsBuilder uriBuilder) throws PageDoesNotExistException, EmptyListException;

    @GetMapping(value = "/getClientById/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
    @ApiOperation(
            value = "Retrieves a client by the id",
            httpMethod = "GET",
            response = ClientResponse.class
    )
    @ApiResponses(value = {
            @ApiResponse(
                    code = 200,
                    message = "Body content with all information about a client"
            ),
            @ApiResponse(
                    code = 400,
                    message = "Describes errors on invalid payload received, e.g: missing fields, invalid data formats, etc.")
    })
    ResponseEntity<ClientResponse> getClientById(
            @ApiParam(name = "id", required = true, value = "Client Id", example = "1")
            @PathVariable("id") Long id) throws ClientNotFoundException;

    @PutMapping(value = "/updateClient/{id}")
    @ApiOperation(
            value = "Retrieves a client updated",
            httpMethod = "PUT",
            response = ClientResponse.class
    )
    @ApiResponses(value = {
            @ApiResponse(code = 200,
                    message = "Body content with all information about client updated",
                    response = ClientResponse.class),
            @ApiResponse(code = 400,
                    message = "Describes errors on invalid payload received, e.g: missing fields, invalid data form")
    })
    ResponseEntity<ClientResponse> updateClient(
            @ApiParam(value = "id of client to update", required = true, example = "1")
            @PathVariable(name = "id") Long idClient,
            @ApiParam(value = "data of client", required = true)
            @RequestBody ClientRequest request
    ) throws ClientNotFoundException;

    @DeleteMapping(value = "/deleteClientById/{id}")
    @ApiOperation(
            value = "Delete a client by id",
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
    ResponseEntity<Void> deleteClientById(
            @ApiParam(name = "id", required = true, value = "Client Id", example = "1")
            @PathVariable("id") Long idClient) throws ClientNotFoundException;
}
