package com.spring.ayi.app.mapper.impl;

import com.spring.ayi.app.dto.request.ClientDetailRequest;
import com.spring.ayi.app.dto.response.ClientDetailResponse;
import com.spring.ayi.app.entity.ClientDetail;
import com.spring.ayi.app.mapper.IClientDetailMapper;
import lombok.AllArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Component;

@Component
@AllArgsConstructor
public class ClientDetailMapperImpl implements IClientDetailMapper {

    private final ModelMapper modelMapper;

    @Override
    public ClientDetail convertDtoToEntity(ClientDetailRequest request) {
        return modelMapper.map(request, ClientDetail.class);
    }

    @Override
    public ClientDetailResponse convertEntityToDto(ClientDetail entity) {
        return modelMapper.map(entity, ClientDetailResponse.class);
    }
}
