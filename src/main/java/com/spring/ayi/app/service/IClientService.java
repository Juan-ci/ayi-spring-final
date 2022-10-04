package com.spring.ayi.app.service;

import com.spring.ayi.app.dto.request.ClientRequest;
import com.spring.ayi.app.dto.response.ClientResponse;
import com.spring.ayi.app.dto.response.GenericListPaginationResponse;
import com.spring.ayi.app.entity.Client;
import com.spring.ayi.app.exception.DocumentNumberAlreadyExistException;
import org.springframework.stereotype.Service;
import org.springframework.web.util.UriComponentsBuilder;

import javax.transaction.Transactional;
import java.util.NoSuchElementException;

@Service
public interface IClientService {
    ClientResponse createClient(ClientRequest request) throws DocumentNumberAlreadyExistException;

    @Transactional
    GenericListPaginationResponse<ClientResponse> getClientPage(String uri, int pageReq, Integer size, UriComponentsBuilder uriBuilder);

    @Transactional
    ClientResponse getOneClientById(Long idClient) throws NoSuchElementException;

    @Transactional
    ClientResponse updateClient(Long id, ClientRequest request) throws NoSuchElementException;

    @Transactional
    void deleteClientById(Long idClient) throws NoSuchElementException;

    @Transactional
    Client getClientByDocumentNumber(String documentNumber) throws NoSuchElementException;
}
