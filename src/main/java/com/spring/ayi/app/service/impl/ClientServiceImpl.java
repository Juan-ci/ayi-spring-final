package com.spring.ayi.app.service.impl;

import com.spring.ayi.app.dto.request.ClientRequest;
import com.spring.ayi.app.dto.response.ClientResponse;
import com.spring.ayi.app.entity.Client;
import com.spring.ayi.app.entity.ClientDetail;
import com.spring.ayi.app.mapper.IClientMapper;
import com.spring.ayi.app.repository.IClientRepository;
import com.spring.ayi.app.service.IClientService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ClientServiceImpl implements IClientService {

    private IClientRepository clientRepository;

    private IClientMapper clientMapper;

    @Autowired
    public ClientServiceImpl(IClientRepository clientRepository, IClientMapper clientMapper) {
        this.clientRepository = clientRepository;
        this.clientMapper  = clientMapper;
    }

    @Override
    public ClientResponse createClient(ClientRequest request) {
        Client client = clientMapper.convertDtoToEntity(request);
        ClientDetail clientDetail = client.getClientDetail();

        clientDetail.setClient(client);
        client = clientRepository.save(client);

        return clientMapper.convertEntityToDto(client);
    }
}
