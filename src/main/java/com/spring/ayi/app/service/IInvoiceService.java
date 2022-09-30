package com.spring.ayi.app.service;

import com.spring.ayi.app.dto.request.InvoiceRequest;
import com.spring.ayi.app.dto.response.InvoiceResponse;
import org.springframework.stereotype.Service;

@Service
public interface IInvoiceService {
    InvoiceResponse createInvoice(InvoiceRequest request);
}
