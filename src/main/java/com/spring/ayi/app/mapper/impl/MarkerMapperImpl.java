package com.spring.ayi.app.mapper.impl;

import com.spring.ayi.app.dto.request.marker.MarkerRequest;
import com.spring.ayi.app.dto.response.marker.MarkerResponse;
import com.spring.ayi.app.entity.Marker;
import com.spring.ayi.app.mapper.IMarkerMapper;
import lombok.AllArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Component;

@Component
@AllArgsConstructor
public class MarkerMapperImpl implements IMarkerMapper {

    private final ModelMapper modelMapper;

    @Override
    public Marker convertDtoToEntity(MarkerRequest request) {
        return modelMapper.map(request, Marker.class);
    }

    @Override
    public MarkerResponse convertEntityToDto(Marker entity) {
        return modelMapper.map(entity, MarkerResponse.class);
    }
}
