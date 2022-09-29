package com.spring.ayi.app.mapper;

import com.spring.ayi.app.dto.request.ClientRequest;
import com.spring.ayi.app.dto.response.ClientResponse;
import com.spring.ayi.app.entity.Client;

public interface IClientMapper {
    Client convertDtoToEntity(ClientRequest request);

    ClientResponse convertEntityToDto(Client entity);
}
