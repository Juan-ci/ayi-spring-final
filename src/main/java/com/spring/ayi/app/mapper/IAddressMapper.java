package com.spring.ayi.app.mapper;

import com.spring.ayi.app.dto.request.AddressRequest;
import com.spring.ayi.app.dto.response.AddressResponse;
import com.spring.ayi.app.entity.Address;

public interface IAddressMapper {
    Address convertDtoTontity(AddressRequest request);

    AddressResponse convertEntityToDto(Address entity);
}
