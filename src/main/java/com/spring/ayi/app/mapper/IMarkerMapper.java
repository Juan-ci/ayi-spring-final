package com.spring.ayi.app.mapper;

import com.spring.ayi.app.dto.request.marker.MarkerRequest;
import com.spring.ayi.app.dto.response.marker.MarkerResponse;
import com.spring.ayi.app.entity.Marker;

public interface IMarkerMapper {

    Marker convertDtoToEntity(MarkerRequest request);

    MarkerResponse convertEntityToDto(Marker entity);
}
