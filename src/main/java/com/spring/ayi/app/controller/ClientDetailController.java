package com.spring.ayi.app.controller;

import com.spring.ayi.app.dto.request.clientDetail.ClientDetailRequest;
import com.spring.ayi.app.dto.response.clientDetail.ClientDetailResponse;
import com.spring.ayi.app.dto.response.pagination.GenericListPaginationResponse;
import com.spring.ayi.app.exception.custom.ClientDetailNotFoundException;
import com.spring.ayi.app.exception.custom.DocumentNumberNotFoundException;
import com.spring.ayi.app.exception.custom.EmptyListException;
import com.spring.ayi.app.exception.custom.PageDoesNotExistException;
import com.spring.ayi.app.service.IClientDetailService;
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

@AllArgsConstructor
@RestController
@Api(value = "Client Detail Api", tags = "{Client Detail Service}")
@RequestMapping(value = "/client-detail")
public class ClientDetailController {

    private IClientDetailService clientDetailService;

    @PostMapping(path = "/createClientDetail", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
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
    public ResponseEntity<ClientDetailResponse> createClientDetail
            (
                    @ApiParam(value = "data of client detail", required = true)
                    @Valid @RequestBody ClientDetailRequest request
            ) throws DocumentNumberNotFoundException {
        ClientDetailResponse clientDetailResponse = clientDetailService.createClientDetail(request);
        return new ResponseEntity<>(clientDetailResponse, HttpStatus.CREATED);
    }

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
    public ResponseEntity<GenericListPaginationResponse<ClientDetailResponse>> getAllClientDetailsForPage
            (
                    @ApiParam(value = "page to display", example = "1")
                    @RequestParam(name = "page", defaultValue = "0") Integer page,
                    @ApiParam(value = "number of items per request", example = "1")
                    @RequestParam(name = "size", defaultValue = "5") Integer size,
                    UriComponentsBuilder uriBuilder
            ) throws PageDoesNotExistException, EmptyListException {
        GenericListPaginationResponse<ClientDetailResponse> response = clientDetailService.getAllClientDetail("/client-detail/", page, size, uriBuilder);
        return new ResponseEntity<>(response, HttpStatus.OK);
    }

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
    public ResponseEntity<ClientDetailResponse> getClientDetailById
            (
                    @ApiParam(name = "id", required = true, value = "Client detail Id", example = "1")
                    @PathVariable("id") Long idClientDetail
            ) throws ClientDetailNotFoundException {
        ClientDetailResponse response = clientDetailService.getOneClientDetailById(idClientDetail);
        return new ResponseEntity<>(response, HttpStatus.OK);
    }

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
    public ResponseEntity<ClientDetailResponse> updateClientDetail
            (
                    @ApiParam(value = "id of client detail to update", required = true, example = "1")
                    @PathVariable(name = "id") Long idClientDetail,
                    @ApiParam(value = "data of client", required = true)
                    @Valid @RequestBody ClientDetailRequest request
            ) throws ClientDetailNotFoundException {
        ClientDetailResponse response = clientDetailService.updateClientDetail(idClientDetail, request);
        return new ResponseEntity<>(response, HttpStatus.OK);
    }

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
    public ResponseEntity<Void> deleteClientDetailById
            (
                    @ApiParam(name = "id", required = true, value = "Client detail Id", example = "1")
                    @PathVariable("id") Long idClientDetail
            ) throws ClientDetailNotFoundException {
        clientDetailService.deleteClientDetailById(idClientDetail);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }
}
