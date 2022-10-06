package com.spring.ayi.app.service.impl;

import com.spring.ayi.app.dto.request.InvoiceRequest;
import com.spring.ayi.app.dto.response.GenericListPaginationResponse;
import com.spring.ayi.app.dto.response.InvoiceResponse;
import com.spring.ayi.app.entity.Client;
import com.spring.ayi.app.entity.Invoice;
import com.spring.ayi.app.exception.DocumentNumberNotFoundException;
import com.spring.ayi.app.exception.InvoiceNotFoundException;
import com.spring.ayi.app.exception.EmptyListException;
import com.spring.ayi.app.exception.PageDoesNotExistException;
import com.spring.ayi.app.mapper.IClientMapper;
import com.spring.ayi.app.mapper.IInvoiceMapper;
import com.spring.ayi.app.repository.IInvoiceRepository;
import com.spring.ayi.app.service.IClientService;
import com.spring.ayi.app.service.IInvoiceService;
import lombok.AllArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.web.util.UriComponentsBuilder;

import javax.transaction.Transactional;
import java.util.List;
import java.util.stream.Collectors;

import static com.spring.ayi.app.constants.ExceptionMessages.ExceptionMessages.EMPTY_LIST_EXCEPTION;
import static com.spring.ayi.app.constants.ExceptionMessages.ExceptionMessages.INVOICE_ID_NOT_FOUND;
import static com.spring.ayi.app.constants.ExceptionMessages.ExceptionMessages.PAGE_DOES_NOT_EXIST;
import static java.text.MessageFormat.format;

@Service
@AllArgsConstructor
public class InvoiceServiceImpl implements IInvoiceService {

    private static final String LIST_EMPTY_TYPE = "INVOICES";
    private IInvoiceRepository invoiceRepository;

    private IInvoiceMapper invoiceMapper;

    private IClientService clientService;

    @Override
    @Transactional
    public InvoiceResponse createInvoice(InvoiceRequest request) throws DocumentNumberNotFoundException {
        Invoice invoice = invoiceMapper.convertDtoToEntity(request);
        Client client = clientService.getClientByDocumentNumber(request.getClientDocumentNumber());

        if (client != null) {
            client.getInvoices().add(invoice);
            invoice.setClient(client);

            invoice = invoiceRepository.save(invoice);
        }

        return invoiceMapper.convertEntityToDto(invoice);
    }

    @Override
    @Transactional
    public GenericListPaginationResponse<InvoiceResponse> getAllInvoices(String uri,
                                                                         int pageReq,
                                                                         Integer size,
                                                                         UriComponentsBuilder uriBuilder)
            throws PageDoesNotExistException, EmptyListException {
        GenericListPaginationResponse<InvoiceResponse> invoicesPagesResponse = new GenericListPaginationResponse<>();
        Pageable pageable = PageRequest.of(pageReq, size);
        Page<Invoice> invoicePage = invoiceRepository.findAll(pageable);

        if (invoicePage != null && !invoicePage.isEmpty() && !(pageReq > invoicePage.getTotalPages() - 1 )) {
            List<InvoiceResponse> invoiceContent = invoicePage
                    .map(
                            invoice -> invoiceMapper.convertEntityToDto(invoice)
                    ).stream()
                    .collect(Collectors.toList());

            invoicesPagesResponse.setPages(invoiceContent);
            invoicesPagesResponse.setSize(invoicePage.getSize());
            invoicesPagesResponse.setCurrentPage(invoicePage.getNumber() + 1);
            invoicesPagesResponse.setTotalPages(invoicePage.getTotalPages());
            invoicesPagesResponse.setTotalElements((int) invoicePage.getTotalElements());

            uriBuilder.path(uri);
            String nextPage = constructNextPageUri(uriBuilder, pageReq);
            String prevPage = constructPrevPageUri(uriBuilder, pageReq);

            /**
             * If page is equal to 0, then
             * there is no previous page
             */
            if (pageable.getPageNumber() == 0) {
                prevPage = null;
            }

            /**
             * If page is equal to the last page, then
             *  there is no next page
             */
            if (pageable.getPageNumber() == invoicePage.getTotalPages() - 1) {
                nextPage = null;
            }

            invoicesPagesResponse.setPrevPage(prevPage);
            invoicesPagesResponse.setNextPage(nextPage);

            return invoicesPagesResponse;
        } else if (invoicePage.getTotalElements() == 0) {
            throw new EmptyListException(format(EMPTY_LIST_EXCEPTION, LIST_EMPTY_TYPE));
        } else {
            throw new PageDoesNotExistException(format(PAGE_DOES_NOT_EXIST, pageReq, size));
        }
    }

    @Override
    @Transactional
    public InvoiceResponse getOneInvoiceById(Long idInvoice) throws InvoiceNotFoundException {
        Invoice invoice = this.getInvoiceById(idInvoice);
        return invoiceMapper.convertEntityToDto(invoice);
    }

    @Override
    @Transactional
    public InvoiceResponse updateInvoice(Long idInvoice, InvoiceRequest request) throws InvoiceNotFoundException {
        Invoice invoiceToUpdate = this.getInvoiceById(idInvoice);

        invoiceToUpdate.setDescription(request.getDescription());
        invoiceToUpdate.setTotal(request.getTotal());

        Invoice invoiceUpdated = invoiceRepository.save(invoiceToUpdate);

        return invoiceMapper.convertEntityToDto(invoiceUpdated);
    }

    @Override
    @Transactional
    public void deleteInvoiceById(Long idInvoice) throws InvoiceNotFoundException {
        Invoice invoice = this.getInvoiceById(idInvoice);

        invoice.setSoftDelete(Boolean.TRUE);
        invoiceRepository.save(invoice);
    }

    private Invoice getInvoiceById(Long idInvoice) throws InvoiceNotFoundException {
        return invoiceRepository
                .findById(idInvoice)
                .orElseThrow(
                        () -> new InvoiceNotFoundException(format(INVOICE_ID_NOT_FOUND, idInvoice))
                );
    }

    private String constructPrevPageUri(UriComponentsBuilder uriBuilder, int pageReq) {
        return uriBuilder.replaceQueryParam("page", pageReq - 1).build().encode().toUriString();
    }

    private String constructNextPageUri(UriComponentsBuilder uriBuilder, int pageReq) {
        return uriBuilder.replaceQueryParam("page", pageReq + 1).build().encode().toUriString();
    }
}
