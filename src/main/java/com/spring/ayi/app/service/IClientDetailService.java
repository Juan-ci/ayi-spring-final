package com.spring.ayi.app.service;

import com.spring.ayi.app.dto.request.clientDetail.ClientDetailRequest;
import com.spring.ayi.app.dto.response.clientDetail.ClientDetailResponse;
import com.spring.ayi.app.dto.response.pagination.GenericListPaginationResponse;
import com.spring.ayi.app.exception.custom.ClientDetailNotFoundException;
import com.spring.ayi.app.exception.custom.DocumentNumberNotFoundException;
import com.spring.ayi.app.exception.custom.EmptyListException;
import com.spring.ayi.app.exception.custom.PageDoesNotExistException;
import org.springframework.stereotype.Service;
import org.springframework.web.util.UriComponentsBuilder;

import javax.transaction.Transactional;

@Service
public interface IClientDetailService {

    @Transactional
    ClientDetailResponse createClientDetail(ClientDetailRequest request) throws DocumentNumberNotFoundException;

    @Transactional
    GenericListPaginationResponse<ClientDetailResponse> getAllClientDetail
            (
                    String uri,
                    int pageReq,
                    Integer size,
                    UriComponentsBuilder uriBuilder
            ) throws PageDoesNotExistException, EmptyListException;

    @Transactional
    ClientDetailResponse getOneClientDetailById(Long idClientDetail) throws ClientDetailNotFoundException;

    @Transactional
    ClientDetailResponse updateClientDetail(Long idClientDetail, ClientDetailRequest request)
            throws ClientDetailNotFoundException;

    @Transactional
    void deleteClientDetailById(Long idClientDetail) throws ClientDetailNotFoundException;
}
