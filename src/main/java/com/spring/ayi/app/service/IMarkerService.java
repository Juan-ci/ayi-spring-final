package com.spring.ayi.app.service;

import com.spring.ayi.app.dto.request.marker.MarkerRequest;
import com.spring.ayi.app.dto.response.marker.MarkerResponse;
import com.spring.ayi.app.exception.custom.MarkerNotFoundException;
import com.spring.ayi.app.exception.custom.EmptyListException;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;

@Service
public interface IMarkerService {

    @Transactional
    MarkerResponse createMarker(MarkerRequest request);

    @Transactional
    List<MarkerResponse> getAllMarkers() throws EmptyListException;

    @Transactional
    void deleteMarkerById(Long idMarker) throws MarkerNotFoundException;
}
