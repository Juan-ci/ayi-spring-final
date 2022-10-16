package com.spring.ayi.app.service;

import com.spring.ayi.app.dto.request.marker.MarkerRequest;
import com.spring.ayi.app.dto.response.marker.MarkerResponse;
import com.spring.ayi.app.dto.response.pagination.GenericListPaginationResponse;
import com.spring.ayi.app.exception.custom.UserAccountNotFoundException;
import com.spring.ayi.app.exception.custom.EmptyListException;
import org.springframework.stereotype.Service;
import org.springframework.web.util.UriComponentsBuilder;

import javax.transaction.Transactional;

@Service
public interface IMarkerService {

    @Transactional
    MarkerResponse createMarker(MarkerRequest request);

    @Transactional
    GenericListPaginationResponse<MarkerResponse> getAllMarkers
            (
                    String uri,
                    int pageReq,
                    Integer size,
                    UriComponentsBuilder uriBuilder
            ) throws EmptyListException;

    @Transactional
    void deleteMarkerById(Long idMarker) throws UserAccountNotFoundException;
}
