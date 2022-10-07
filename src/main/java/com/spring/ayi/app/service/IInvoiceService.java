package com.spring.ayi.app.service;

import com.spring.ayi.app.dto.request.invoice.InvoiceRequest;
import com.spring.ayi.app.dto.request.invoice.InvoiceRequestWithoutDocumentNumber;
import com.spring.ayi.app.dto.response.pagination.GenericListPaginationResponse;
import com.spring.ayi.app.dto.response.invoice.InvoiceResponse;
import com.spring.ayi.app.exception.custom.DocumentNumberNotFoundException;
import com.spring.ayi.app.exception.custom.EmptyListException;
import com.spring.ayi.app.exception.custom.InvoiceNotFoundException;
import com.spring.ayi.app.exception.custom.PageDoesNotExistException;
import org.springframework.stereotype.Service;
import org.springframework.web.util.UriComponentsBuilder;

import javax.transaction.Transactional;

@Service
public interface IInvoiceService {

    @Transactional
    InvoiceResponse createInvoice(InvoiceRequest request) throws DocumentNumberNotFoundException;

    @Transactional
    GenericListPaginationResponse<InvoiceResponse> getAllInvoices
            (
                    String uri,
                    int pageReq,
                    Integer size,
                    UriComponentsBuilder uriBuilder
            ) throws PageDoesNotExistException, EmptyListException;

    @Transactional
    InvoiceResponse getOneInvoiceById(Long idInvoice) throws InvoiceNotFoundException;

    @Transactional
    InvoiceResponse updateInvoice(Long idInvoice, InvoiceRequestWithoutDocumentNumber request) throws InvoiceNotFoundException;

    @Transactional
    void deleteInvoiceById(Long idInvoice) throws InvoiceNotFoundException;
}
