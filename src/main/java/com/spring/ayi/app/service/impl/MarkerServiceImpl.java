package com.spring.ayi.app.service.impl;

import com.spring.ayi.app.dto.request.marker.MarkerRequest;
import com.spring.ayi.app.dto.response.marker.MarkerResponse;
import com.spring.ayi.app.entity.Marker;
import com.spring.ayi.app.exception.custom.MarkerNotFoundException;
import com.spring.ayi.app.exception.custom.EmptyListException;
import com.spring.ayi.app.mapper.IMarkerMapper;
import com.spring.ayi.app.repository.IMarkerRepository;
import com.spring.ayi.app.service.IMarkerService;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.ArrayList;
import java.util.List;

import static com.spring.ayi.app.constants.exception.messages.ExceptionMessages.MARKER_ID_NOT_FOUND;
import static java.text.MessageFormat.format;

@Service
public class MarkerServiceImpl implements IMarkerService {

    private IMarkerRepository markerRepository;

    private IMarkerMapper markerMapper;

    private static final String LIST_TYPE_EXCEPTION = "MARKERS";

    public MarkerServiceImpl(IMarkerRepository markerRepository,
                             IMarkerMapper markerMapper) {
        this.markerRepository = markerRepository;
        this.markerMapper = markerMapper;
    }

    @Override
    @Transactional
    public MarkerResponse createMarker(MarkerRequest request) {
        Marker marker = markerMapper.convertDtoToEntity(request);

        marker = markerRepository.save(marker);

        return markerMapper.convertEntityToDto(marker);
    }

    @Override
    @Transactional
    public List<MarkerResponse> getAllMarkers() throws EmptyListException {
        List<Marker> markersList = markerRepository.findAllBySoftDeleteIsFalse();
        List<MarkerResponse> markerResponses = new ArrayList<>();

        if (!markersList.isEmpty()) {

            markersList.forEach(marker -> markerResponses.add(markerMapper.convertEntityToDto(marker)));

            return markerResponses;
        }
        return markerResponses;
    }

    @Override
    @Transactional
    public void deleteMarkerById(Long idMarker) throws MarkerNotFoundException {
        Marker marker = this.getUserAccountById(idMarker);

        marker.setSoftDelete(Boolean.TRUE);

        markerRepository.save(marker);
    }

    private Marker getUserAccountById(Long idMarker) throws MarkerNotFoundException {
        return markerRepository
                .findById(idMarker)
                .orElseThrow(
                        () -> new MarkerNotFoundException(format(MARKER_ID_NOT_FOUND, idMarker))
                );
    }
}
