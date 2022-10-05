package com.spring.ayi.app.service;

import com.spring.ayi.app.dto.request.ClientDetailRequest;
import com.spring.ayi.app.dto.response.ClientDetailResponse;
import com.spring.ayi.app.dto.response.GenericListPaginationResponse;
import org.springframework.stereotype.Service;
import org.springframework.web.util.UriComponentsBuilder;

import javax.transaction.Transactional;
import java.util.NoSuchElementException;

@Service
public interface IClientDetailService {
    ClientDetailResponse createClientDetail(ClientDetailRequest request);

    @Transactional
    GenericListPaginationResponse<ClientDetailResponse> getAllClientDetail(String uri,
                                                                           int pageReq,
                                                                           Integer size,
                                                                           UriComponentsBuilder uriBuilder);

    @Transactional
    ClientDetailResponse getOneClientDetailById(Long idClientDetail) throws NoSuchElementException;

    @Transactional
    ClientDetailResponse updateClientDetail(Long idClientDetail, ClientDetailRequest request) throws NoSuchElementException;

    @Transactional
    void deleteClientDetailById(Long idClientDetail) throws NoSuchElementException;
}
