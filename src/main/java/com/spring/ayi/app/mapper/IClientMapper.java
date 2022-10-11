package com.spring.ayi.app.mapper;

import com.spring.ayi.app.dto.request.client.ClientRequest;
import com.spring.ayi.app.dto.response.client.ClientResponse;
import com.spring.ayi.app.entity.Client;

public interface IClientMapper {
    Client convertDtoToEntity(ClientRequest request);

    ClientResponse convertEntityToDto(Client entity);
}
