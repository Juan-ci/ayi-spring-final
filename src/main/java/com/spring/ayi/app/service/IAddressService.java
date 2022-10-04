package com.spring.ayi.app.service;

import com.spring.ayi.app.dto.request.AddressRequest;
import com.spring.ayi.app.dto.response.AddressResponse;
import com.spring.ayi.app.dto.response.GenericListPaginationResponse;
import org.springframework.stereotype.Service;
import org.springframework.web.util.UriComponentsBuilder;

import javax.transaction.Transactional;
import java.util.NoSuchElementException;

@Service
public interface IAddressService {
    AddressResponse createAddress(AddressRequest request) throws NoSuchElementException;

    @Transactional
    GenericListPaginationResponse<AddressResponse> getAllAddress(String uri,
                                                                 int pageReq,
                                                                 Integer size,
                                                                 UriComponentsBuilder uriBuilder);

    @Transactional
    AddressResponse getOneAddressById(Long idAddress) throws NoSuchElementException;

    @Transactional
    AddressResponse updateAddress(Long idAddress, AddressRequest request) throws NoSuchElementException;

    @Transactional
    void deleteAddressById(Long idAddress) throws NoSuchElementException;
}
