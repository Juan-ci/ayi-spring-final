package com.spring.ayi.app.service;

import com.spring.ayi.app.dto.request.address.AddressRequest;
import com.spring.ayi.app.dto.request.client.AddressRequestWithoutDocumentNumber;
import com.spring.ayi.app.dto.response.address.AddressResponse;
import com.spring.ayi.app.dto.response.pagination.GenericListPaginationResponse;
import com.spring.ayi.app.exception.custom.AddressNotFoundException;
import com.spring.ayi.app.exception.custom.ClientNotFoundException;
import com.spring.ayi.app.exception.custom.DocumentNumberNotFoundException;
import com.spring.ayi.app.exception.custom.EmptyListException;
import com.spring.ayi.app.exception.custom.PageDoesNotExistException;
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
    AddressResponse updateAddress(Long idAddress, AddressRequestWithoutDocumentNumber request) throws AddressNotFoundException;

    @Transactional
    void deleteAddressById(Long idAddress) throws AddressNotFoundException;
}
