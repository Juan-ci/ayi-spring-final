package com.spring.ayi.app.mapper;

import com.spring.ayi.app.dto.request.ClientDetailRequest;
import com.spring.ayi.app.dto.response.ClientDetailResponse;
import com.spring.ayi.app.entity.ClientDetail;

public interface IClientDetailMapper {
    ClientDetail convertDtoToEntity(ClientDetailRequest request);

    ClientDetailResponse convertEntityToDto(ClientDetail entity);
}
