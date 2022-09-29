package com.spring.ayi.app.service;

import com.spring.ayi.app.dto.request.ClientRequest;
import com.spring.ayi.app.dto.response.ClientResponse;
import org.springframework.stereotype.Service;

@Service
public interface IClientService {
    ClientResponse createClient(ClientRequest request);
}
