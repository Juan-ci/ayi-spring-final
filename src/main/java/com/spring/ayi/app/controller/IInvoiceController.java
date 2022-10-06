package com.spring.ayi.app.controller;

import com.spring.ayi.app.dto.request.InvoiceRequest;
import com.spring.ayi.app.dto.response.GenericListPaginationResponse;
import com.spring.ayi.app.dto.response.InvoiceResponse;
import com.spring.ayi.app.exception.ClientNotFoundException;
import com.spring.ayi.app.exception.DocumentNumberNotFoundException;
import com.spring.ayi.app.exception.EmptyListException;
import com.spring.ayi.app.exception.InvoiceNotFoundException;
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

public interface IInvoiceController {

    @PostMapping(value = "/createInvoice", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    @ApiOperation(
            value = "Retrieves an invoice created",
            httpMethod = "POST",
            response = InvoiceResponse.class
    )
    @ApiResponses(value = {
            @ApiResponse(code = 201,
                    message = "Body content with all information about invoice",
                    response = InvoiceResponse.class),
            @ApiResponse(code = 400,
                    message = "Describes errors on invalid payload received, e.g: missing fields, invalid data form")
    })
    ResponseEntity<InvoiceResponse> createInvoice(
            @ApiParam(value = "data of invoice", required = true)
            @RequestBody InvoiceRequest request
    ) throws DocumentNumberNotFoundException;

    @GetMapping(value = "/getAllInvoices", produces = MediaType.APPLICATION_JSON_VALUE)
    @ApiOperation(
            value = "Retrieves all Lists Invoices in a page",
            httpMethod = "GET",
            response = GenericListPaginationResponse.class
    )
    @ApiResponses(value = {
            @ApiResponse(code = 200,
                    message = "Body content with basic information about invoices",
                    response = GenericListPaginationResponse.class),
            @ApiResponse(
                    code = 400,
                    message = "Describes errors on invalid payload received, e.g: missing fields, invalid data formats, etc.")
    })
    ResponseEntity<GenericListPaginationResponse<InvoiceResponse>> getAllInvoicesForPage(
            @ApiParam(value = "page to display", example = "1")
            @RequestParam(name = "page", defaultValue = "0") Integer page,
            @ApiParam(value = "number of items per request", example = "1")
            @RequestParam(name = "size", defaultValue = "5") Integer size,
            UriComponentsBuilder uriBuilder) throws PageDoesNotExistException, EmptyListException;

    @GetMapping(value = "/getInvoiceById/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
    @ApiOperation(
            value = "Retrieves an invoice by the id",
            httpMethod = "GET",
            response = InvoiceResponse.class
    )
    @ApiResponses(value = {
            @ApiResponse(
                    code = 200,
                    message = "Body content with all information about an invoice"
            ),
            @ApiResponse(
                    code = 400,
                    message = "Describes errors on invalid payload received, e.g: missing fields, invalid data formats, etc.")
    })
    ResponseEntity<InvoiceResponse> getInvoiceById(
            @ApiParam(name = "id", required = true, value = "Invoice Id", example = "1")
            @PathVariable("id") Long idInvoice) throws InvoiceNotFoundException;

    @PutMapping(value = "/updateInvoice/{id}")
    @ApiOperation(
            value = "Retrieves an invoice updated",
            httpMethod = "PUT",
            response = InvoiceResponse.class
    )
    @ApiResponses(value = {
            @ApiResponse(code = 200,
                    message = "Body content with all information about an invoice updated",
                    response = InvoiceResponse.class),
            @ApiResponse(code = 400,
                    message = "Describes errors on invalid payload received, e.g: missing fields, invalid data form")
    })
    ResponseEntity<InvoiceResponse> updateInvoice(
            @ApiParam(value = "id of invoice to update", required = true, example = "1")
            @PathVariable(name = "id") Long idInvoice,
            @ApiParam(value = "data of invoice", required = true)
            @RequestBody InvoiceRequest request
    ) throws InvoiceNotFoundException;

    @DeleteMapping(value = "/deleteInvoiceById/{id}")
    @ApiOperation(
            value = "Delete an invoice by id",
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
    ResponseEntity<Void> deleteInvoiceById(
            @ApiParam(name = "id", required = true, value = "Invoice Id", example = "1")
            @PathVariable("id") Long idInvoice) throws InvoiceNotFoundException;
}
