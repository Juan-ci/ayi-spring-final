package com.spring.ayi.app.service;

import com.spring.ayi.app.dto.request.AddressRequest;
import com.spring.ayi.app.dto.response.AddressResponse;
import com.spring.ayi.app.dto.response.GenericListPaginationResponse;
import com.spring.ayi.app.exception.AddressNotFoundException;
import com.spring.ayi.app.exception.ClientNotFoundException;
import com.spring.ayi.app.exception.DocumentNumberNotFoundException;
import com.spring.ayi.app.exception.EmptyListException;
import com.spring.ayi.app.exception.PageDoesNotExistException;
import org.springframework.stereotype.Service;
import org.springframework.web.util.UriComponentsBuilder;

import javax.transaction.Transactional;

@Service
public interface IAddressService {

    @Transactional
    AddressResponse createAddress(AddressRequest request) throws ClientNotFoundException, DocumentNumberNotFoundException;

    @Transactional
    GenericListPaginationResponse<AddressResponse> getAllAddress
            (
                    String uri,
                    int pageReq,
                    Integer size,
                    UriComponentsBuilder uriBuilder
            )
            throws PageDoesNotExistException, EmptyListException;

    @Transactional
    AddressResponse getOneAddressById(Long idAddress) throws AddressNotFoundException;

    @Transactional
    AddressResponse updateAddress(Long idAddress, AddressRequest request) throws AddressNotFoundException;

    @Transactional
    void deleteAddressById(Long idAddress) throws AddressNotFoundException;
}
