package com.spring.ayi.app.mapper.impl;

import com.spring.ayi.app.dto.request.client.ClientRequest;
import com.spring.ayi.app.dto.response.client.ClientResponse;
import com.spring.ayi.app.entity.Client;
import com.spring.ayi.app.mapper.IClientMapper;
import lombok.AllArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Component;

@Component
@AllArgsConstructor
public class ClientMapperImpl implements IClientMapper {

    private final ModelMapper modelMapper;

    @Override
    public Client convertDtoToEntity(ClientRequest request) {
        return modelMapper.map(request, Client.class);
    }

    @Override
    public ClientResponse convertEntityToDto(Client entity) {
        return modelMapper.map(entity, ClientResponse.class);
    }
}
