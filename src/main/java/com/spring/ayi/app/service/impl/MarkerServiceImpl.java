package com.spring.ayi.app.service.impl;

import com.spring.ayi.app.dto.request.marker.MarkerRequest;
import com.spring.ayi.app.dto.response.marker.MarkerResponse;
import com.spring.ayi.app.dto.response.pagination.GenericListPaginationResponse;
import com.spring.ayi.app.entity.Marker;
import com.spring.ayi.app.exception.custom.UserAccountNotFoundException;
import com.spring.ayi.app.exception.custom.EmptyListException;
import com.spring.ayi.app.mapper.IMarkerMapper;
import com.spring.ayi.app.repository.IMarkerRepository;
import com.spring.ayi.app.service.IMarkerService;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.web.util.UriComponentsBuilder;

import javax.transaction.Transactional;
import static com.spring.ayi.app.constants.exception.messages.ExceptionMessages.USER_ACCOUNT_ID_NOT_FOUND;
import static java.text.MessageFormat.format;

@Service
public class MarkerServiceImpl implements IMarkerService {

    private IMarkerRepository userAccountRepository;

    private IMarkerMapper userAccountMapper;

    private static final String LIST_TYPE_EXCEPTION = "MARKERS";

    public MarkerServiceImpl(IMarkerRepository userAccountRepository,
                             IMarkerMapper userAccountMapper) {
        this.userAccountRepository = userAccountRepository;
        this.userAccountMapper = userAccountMapper;
    }

    @Override
    @Transactional
    public MarkerResponse createMarker(MarkerRequest request) {
        Marker marker = userAccountMapper.convertDtoToEntity(request);

        return null;
    }

    @Override
    @Transactional
    public GenericListPaginationResponse<MarkerResponse> getAllMarkers
            (
                    String uri,
                    int pageReq,
                    Integer size,
                    UriComponentsBuilder uriBuilder
            ) throws EmptyListException {
        GenericListPaginationResponse<MarkerResponse> userAccountPagesResponse = new GenericListPaginationResponse<>();
        Pageable pageable = PageRequest.of(pageReq, size);
        Page<Marker> userAccountPage = userAccountRepository.findAll(pageable);

        return null;
    }

    @Override
    @Transactional
    public void deleteMarkerById(Long idMarker) throws UserAccountNotFoundException {
        Marker marker = this.getUserAccountById(idMarker);

        marker.setSoftDelete(Boolean.TRUE);

        userAccountRepository.save(marker);
    }

    private Marker getUserAccountById(Long idMarker) throws UserAccountNotFoundException {
        return userAccountRepository
                .findById(idMarker)
                .orElseThrow(
                        () -> new UserAccountNotFoundException(format(USER_ACCOUNT_ID_NOT_FOUND, idMarker))
                );
    }
}
