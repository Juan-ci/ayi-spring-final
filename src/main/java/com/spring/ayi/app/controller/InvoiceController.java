package com.spring.ayi.app.controller;

import com.spring.ayi.app.dto.request.invoice.InvoiceRequest;
import com.spring.ayi.app.dto.request.invoice.InvoiceRequestWithoutDocumentNumber;
import com.spring.ayi.app.dto.response.pagination.GenericListPaginationResponse;
import com.spring.ayi.app.dto.response.invoice.InvoiceResponse;
import com.spring.ayi.app.exception.custom.DocumentNumberNotFoundException;
import com.spring.ayi.app.exception.custom.EmptyListException;
import com.spring.ayi.app.exception.custom.InvoiceNotFoundException;
import com.spring.ayi.app.exception.custom.PageDoesNotExistException;
import com.spring.ayi.app.service.IInvoiceService;
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

import static com.spring.ayi.app.constants.path.PathUrls.INVOICE_PATH;

@RestController
@AllArgsConstructor
@Api(value = "Invoice Api", tags = "{Invoice Service}")
@RequestMapping(path = "/invoice")
public class InvoiceController {

    private IInvoiceService invoiceService;

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
    public ResponseEntity<InvoiceResponse> createInvoice(
            @ApiParam(value = "data of invoice", required = true)
            @Valid @RequestBody InvoiceRequest request
    ) throws DocumentNumberNotFoundException {
        InvoiceResponse response = invoiceService.createInvoice(request);
        return new ResponseEntity<>(response, HttpStatus.CREATED);
    }

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
    public ResponseEntity<GenericListPaginationResponse<InvoiceResponse>> getAllInvoicesForPage
            (
                    @ApiParam(value = "page to display", example = "1")
                    @RequestParam(name = "page", defaultValue = "0") Integer page,
                    @ApiParam(value = "number of items per request", example = "1")
                    @RequestParam(name = "size", defaultValue = "5") Integer size,
                    UriComponentsBuilder uriBuilder
            ) throws PageDoesNotExistException, EmptyListException {
        GenericListPaginationResponse<InvoiceResponse> response = invoiceService.getAllInvoices(INVOICE_PATH, page, size, uriBuilder);
        return new ResponseEntity<>(response, HttpStatus.OK);
    }

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
    public ResponseEntity<InvoiceResponse> getInvoiceById(
            @ApiParam(name = "id", required = true, value = "Invoice Id", example = "1")
            @PathVariable("id") Long idInvoice) throws InvoiceNotFoundException {
        InvoiceResponse response = invoiceService.getOneInvoiceById(idInvoice);
        return new ResponseEntity<>(response, HttpStatus.OK);
    }

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
    public ResponseEntity<InvoiceResponse> updateInvoice(
            @ApiParam(value = "id of invoice to update", required = true, example = "1")
            @PathVariable(name = "id") Long idInvoice,
            @ApiParam(value = "data of invoice", required = true)
            @Valid @RequestBody InvoiceRequestWithoutDocumentNumber request
    ) throws InvoiceNotFoundException {
        InvoiceResponse response = invoiceService.updateInvoice(idInvoice, request);
        return new ResponseEntity<>(response, HttpStatus.OK);
    }

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
    public ResponseEntity<Void> deleteInvoiceById
            (
                    @ApiParam(name = "id", required = true, value = "Invoice Id", example = "1")
                    @PathVariable("id") Long idInvoice
            ) throws InvoiceNotFoundException {
        invoiceService.deleteInvoiceById(idInvoice);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }
}
