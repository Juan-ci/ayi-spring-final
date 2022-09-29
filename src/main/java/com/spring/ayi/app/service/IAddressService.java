package com.spring.ayi.app.service;

import com.spring.ayi.app.dto.request.AddressRequest;
import com.spring.ayi.app.dto.response.AddressResponse;
import org.springframework.stereotype.Service;

@Service
public interface IAddressService {
    AddressResponse createAddress(AddressRequest request);
}
