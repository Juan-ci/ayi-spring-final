package com.spring.ayi.app.mapper;

import com.spring.ayi.app.dto.request.clientDetail.ClientDetailRequest;
import com.spring.ayi.app.dto.response.clientDetail.ClientDetailResponse;
import com.spring.ayi.app.entity.ClientDetail;

public interface IClientDetailMapper {
    ClientDetail convertDtoToEntity(ClientDetailRequest request);

    ClientDetailResponse convertEntityToDto(ClientDetail entity);
}
