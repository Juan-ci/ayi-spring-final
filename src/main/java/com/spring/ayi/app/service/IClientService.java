package com.spring.ayi.app.service;

import com.spring.ayi.app.dto.request.ClientRequest;
import com.spring.ayi.app.dto.response.ClientResponse;
import com.spring.ayi.app.dto.response.GenericListPaginationResponse;
import org.springframework.stereotype.Service;
import org.springframework.web.util.UriComponentsBuilder;

import javax.transaction.Transactional;

@Service
public interface IClientService {
    ClientResponse createClient(ClientRequest request);

    @Transactional
    GenericListPaginationResponse<ClientResponse> getClientPage(String uri, int pageReq, Integer size, UriComponentsBuilder uriBuilder);
}
