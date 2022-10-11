package com.spring.ayi.app.mapper;

import com.spring.ayi.app.dto.request.invoice.InvoiceRequest;
import com.spring.ayi.app.dto.response.invoice.InvoiceResponse;
import com.spring.ayi.app.entity.Invoice;

public interface IInvoiceMapper {

    Invoice convertDtoToEntity(InvoiceRequest request);

    InvoiceResponse convertEntityToDto(Invoice entity);
}
