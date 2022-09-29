package com.spring.ayi.app.mapper.impl;

import com.spring.ayi.app.dto.request.AddressRequest;
import com.spring.ayi.app.dto.response.AddressResponse;
import com.spring.ayi.app.entity.Address;
import com.spring.ayi.app.mapper.IInvoiceMapper;
import lombok.AllArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Component;

@Component
@AllArgsConstructor
public class InvoiceMapperImpl implements IInvoiceMapper {

    private final ModelMapper modelMapper;

    @Override
    public Address convertDtoToEntity(AddressRequest request) {
        return modelMapper.map(request, Address.class);
    }

    @Override
    public AddressResponse convertEntityToDto(Address entity) {
        return modelMapper.map(entity, AddressResponse.class);
    }
}
