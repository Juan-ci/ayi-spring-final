package com.spring.ayi.app.service.impl;

import com.spring.ayi.app.dto.request.clientDetail.ClientDetailRequest;
import com.spring.ayi.app.dto.response.clientDetail.ClientDetailResponse;
import com.spring.ayi.app.dto.response.pagination.GenericListPaginationResponse;
import com.spring.ayi.app.entity.Client;
import com.spring.ayi.app.entity.ClientDetail;
import com.spring.ayi.app.exception.custom.ClientDetailNotFoundException;
import com.spring.ayi.app.exception.custom.DocumentNumberNotFoundException;
import com.spring.ayi.app.exception.custom.EmptyListException;
import com.spring.ayi.app.exception.custom.PageDoesNotExistException;
import com.spring.ayi.app.mapper.IClientDetailMapper;
import com.spring.ayi.app.repository.IClientDetailRepository;
import com.spring.ayi.app.service.IClientDetailService;
import com.spring.ayi.app.service.IClientService;
import lombok.AllArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.web.util.UriComponentsBuilder;

import javax.transaction.Transactional;
import java.util.List;
import java.util.stream.Collectors;

import static com.spring.ayi.app.constants.ExceptionMessages.ExceptionMessages.CLIENT_DETAIL_ID_NOT_FOUND;
import static com.spring.ayi.app.constants.ExceptionMessages.ExceptionMessages.EMPTY_LIST_EXCEPTION;
import static com.spring.ayi.app.constants.ExceptionMessages.ExceptionMessages.PAGE_DOES_NOT_EXIST;
import static java.text.MessageFormat.format;

@Service
@AllArgsConstructor
public class ClientDetailServiceImpl implements IClientDetailService {

    private IClientDetailRepository clientDetailRepository;

    private IClientDetailMapper clientDetailMapper;

    private IClientService clientService;

    private static final String LIST_TYPE_EXCEPTION = "CLIENT DETAILS";

    /**
     * It is not allowed to create a client detail
     * without an existing client previously
     * 
     * @param request
     * @return
     * @throws DocumentNumberNotFoundException
     */
    @Override
    @Transactional
    public ClientDetailResponse createClientDetail(ClientDetailRequest request)
            throws DocumentNumberNotFoundException {
        ClientDetail clientDetail = clientDetailMapper.convertDtoToEntity(request);

        Client client = clientService.getClientByDocumentNumber(request.getClientDocumentNumber());

        if (client != null) {
            client.setClientDetail(clientDetail);
            clientDetail.setClient(client);
            clientDetail = clientDetailRepository.save(clientDetail);

        }
        return clientDetailMapper.convertEntityToDto(clientDetail);
    }

    @Override
    @Transactional
    public GenericListPaginationResponse<ClientDetailResponse> getAllClientDetail
            (
                    String uri,
                    int pageReq,
                    Integer size,
                    UriComponentsBuilder uriBuilder
            ) throws PageDoesNotExistException, EmptyListException {
        GenericListPaginationResponse<ClientDetailResponse> clientDetailPagesResponse = new GenericListPaginationResponse<>();
        Pageable pageable = PageRequest.of(pageReq, size);
        Page<ClientDetail> clientDetailPage = clientDetailRepository.findAll(pageable);

        if (clientDetailPage != null && !clientDetailPage.isEmpty() && !(pageReq > clientDetailPage.getTotalPages() - 1)) {
            List<ClientDetailResponse> clientDetailContent = clientDetailPage.map(
                            clientDetail -> clientDetailMapper.convertEntityToDto(clientDetail)
                    ).stream()
                    .collect(Collectors.toList());

            clientDetailPagesResponse.setPages(clientDetailContent);
            clientDetailPagesResponse.setSize(clientDetailPage.getSize());
            clientDetailPagesResponse.setCurrentPage(clientDetailPage.getNumber() + 1);
            clientDetailPagesResponse.setTotalPages(clientDetailPage.getTotalPages());
            clientDetailPagesResponse.setTotalElements((int) clientDetailPage.getTotalElements());

            uriBuilder.path(uri);
            String nextPage = constructNextPageUri(uriBuilder, pageReq);
            String prevPage = constructPrevPageUri(uriBuilder, pageReq);

            /**
             * If page is equal to 0, then
             * there is no previous page
             */
            if (pageable.getPageNumber() == 0) {
                prevPage = null;
            }

            /**
             * If page is equal to the last page, then
             *  there is no next page
             */
            if (pageable.getPageNumber() == clientDetailPage.getTotalPages() - 1) {
                nextPage = null;
            }

            clientDetailPagesResponse.setPrevPage(prevPage);
            clientDetailPagesResponse.setNextPage(nextPage);

            return clientDetailPagesResponse;
        } else if (clientDetailPage.getTotalElements() == 0) {
            throw new EmptyListException(format(EMPTY_LIST_EXCEPTION, LIST_TYPE_EXCEPTION));
        } else {
            throw new PageDoesNotExistException(format(PAGE_DOES_NOT_EXIST, pageReq, size));
        }
    }

    @Override
    @Transactional
    public ClientDetailResponse getOneClientDetailById(Long idClientDetail) throws ClientDetailNotFoundException {
        ClientDetail clientDetail = this.getClientDetailById(idClientDetail);
        return clientDetailMapper.convertEntityToDto(clientDetail);
    }

    @Override
    @Transactional
    public ClientDetailResponse updateClientDetail(Long idClientDetail, ClientDetailRequest request)
            throws ClientDetailNotFoundException {
        ClientDetail clientDetailToUpdate = this.getClientDetailById(idClientDetail);

        clientDetailToUpdate.setPrime(request.getPrime());
        clientDetailToUpdate.setAcumulatedPoints(request.getAcumulatedPoints());

        ClientDetail clientDetailUpdated = clientDetailRepository.save(clientDetailToUpdate);

        return clientDetailMapper.convertEntityToDto(clientDetailUpdated);
    }

    @Override
    @Transactional
    public void deleteClientDetailById(Long idClientDetail) throws ClientDetailNotFoundException {
        ClientDetail clientDetail = this.getClientDetailById(idClientDetail);

        clientDetail.setSoftDelete(Boolean.TRUE);
        clientDetailRepository.save(clientDetail);
    }

    private ClientDetail getClientDetailById(Long idClientDetail) throws ClientDetailNotFoundException {
        return clientDetailRepository
                .findById(idClientDetail)
                .orElseThrow(
                        () -> new ClientDetailNotFoundException(format(CLIENT_DETAIL_ID_NOT_FOUND, idClientDetail))
                );
    }

    private String constructPrevPageUri(UriComponentsBuilder uriBuilder, int pageReq) {
        return uriBuilder.replaceQueryParam("page", pageReq - 1).build().encode().toUriString();
    }

    private String constructNextPageUri(UriComponentsBuilder uriBuilder, int pageReq) {
        return uriBuilder.replaceQueryParam("page", pageReq + 1).build().encode().toUriString();
    }
}
