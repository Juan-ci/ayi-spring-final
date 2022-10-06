package com.spring.ayi.app.service;

import com.spring.ayi.app.dto.request.InvoiceRequest;
import com.spring.ayi.app.dto.response.GenericListPaginationResponse;
import com.spring.ayi.app.dto.response.InvoiceResponse;
import com.spring.ayi.app.exception.DocumentNumberNotFoundException;
import com.spring.ayi.app.exception.EmptyListException;
import com.spring.ayi.app.exception.InvoiceNotFoundException;
import com.spring.ayi.app.exception.PageDoesNotExistException;
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
    InvoiceResponse updateInvoice(Long idInvoice, InvoiceRequest request) throws InvoiceNotFoundException;

    @Transactional
    void deleteInvoiceById(Long idInvoice) throws InvoiceNotFoundException;
}
