package com.spring.ayi.app.service.impl;

import com.spring.ayi.app.dto.request.InvoiceRequest;
import com.spring.ayi.app.dto.response.InvoiceResponse;
import com.spring.ayi.app.entity.Client;
import com.spring.ayi.app.entity.Invoice;
import com.spring.ayi.app.mapper.IClientMapper;
import com.spring.ayi.app.mapper.IInvoiceMapper;
import com.spring.ayi.app.repository.IInvoiceRepository;
import com.spring.ayi.app.service.IInvoiceService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;

@Service
@AllArgsConstructor
public class InvoiceServiceImpl implements IInvoiceService {

    private IInvoiceRepository invoiceRepository;

    private IInvoiceMapper invoiceMapper;

    private IClientMapper clientMapper;

    @Override
    @Transactional
    public InvoiceResponse createInvoice(InvoiceRequest request) {
        Invoice invoice = invoiceMapper.convertDtoToEntity(request);
        Client client = clientMapper.convertDtoToEntity(request.getClient());

        client.getInvoices().add(invoice);
        invoice.setClient(client);

        invoice = invoiceRepository.save(invoice);

        return invoiceMapper.convertEntityToDto(invoice);
    }
}
