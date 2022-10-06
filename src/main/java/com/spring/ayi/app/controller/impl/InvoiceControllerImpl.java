package com.spring.ayi.app.controller.impl;

import com.spring.ayi.app.controller.IInvoiceController;
import com.spring.ayi.app.dto.request.InvoiceRequest;
import com.spring.ayi.app.dto.response.GenericListPaginationResponse;
import com.spring.ayi.app.dto.response.InvoiceResponse;
import com.spring.ayi.app.exception.EmptyListException;
import com.spring.ayi.app.exception.InvoiceNotFoundException;
import com.spring.ayi.app.exception.PageDoesNotExistException;
import com.spring.ayi.app.service.IInvoiceService;
import io.swagger.annotations.Api;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.util.UriComponentsBuilder;

@RestController
@AllArgsConstructor
@Api(value = "Invoice Api", tags = "{Invoice Service}")
@RequestMapping(path = "/invoice")
public class InvoiceControllerImpl implements IInvoiceController {

    private IInvoiceService invoiceService;

    @Override
    public ResponseEntity<InvoiceResponse> createInvoice(InvoiceRequest request) {
        InvoiceResponse response = invoiceService.createInvoice(request);
        return new ResponseEntity<>(response, HttpStatus.CREATED);
    }

    @Override
    public ResponseEntity<GenericListPaginationResponse<InvoiceResponse>> getAllInvoicesForPage
            (
                    Integer page,
                    Integer size,
                    UriComponentsBuilder uriBuilder
            ) throws PageDoesNotExistException, EmptyListException {
        GenericListPaginationResponse<InvoiceResponse> response = invoiceService.getAllInvoices("/invoice/", page, size, uriBuilder);
        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    @Override
    public ResponseEntity<InvoiceResponse> getInvoiceById(Long idInvoice) throws InvoiceNotFoundException {
        InvoiceResponse response = invoiceService.getOneInvoiceById(idInvoice);
        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    @Override
    public ResponseEntity<InvoiceResponse> updateInvoice(Long idInvoice, InvoiceRequest request) throws InvoiceNotFoundException {
        InvoiceResponse response = invoiceService.updateInvoice(idInvoice, request);
        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    @Override
    public ResponseEntity<Void> deleteInvoiceById(Long idInvoice) throws InvoiceNotFoundException {
        invoiceService.deleteInvoiceById(idInvoice);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }
}
