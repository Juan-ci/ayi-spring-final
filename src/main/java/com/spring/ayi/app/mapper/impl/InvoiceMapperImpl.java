package com.spring.ayi.app.mapper.impl;

import com.spring.ayi.app.dto.request.InvoiceRequest;
import com.spring.ayi.app.dto.response.InvoiceResponse;
import com.spring.ayi.app.entity.Invoice;
import com.spring.ayi.app.mapper.IInvoiceMapper;
import lombok.AllArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Component;

@Component
@AllArgsConstructor
public class InvoiceMapperImpl implements IInvoiceMapper {

    private final ModelMapper modelMapper;

    @Override
    public Invoice convertDtoToEntity(InvoiceRequest request) {
        return modelMapper.map(request, Invoice.class);
    }

    @Override
    public InvoiceResponse convertEntityToDto(Invoice entity) {
        return modelMapper.map(entity, InvoiceResponse.class);
    }
}
