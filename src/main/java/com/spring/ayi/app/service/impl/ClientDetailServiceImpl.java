package com.spring.ayi.app.service.impl;

import com.spring.ayi.app.dto.request.ClientDetailRequest;
import com.spring.ayi.app.dto.response.ClientDetailResponse;
import com.spring.ayi.app.dto.response.GenericListPaginationResponse;
import com.spring.ayi.app.entity.Client;
import com.spring.ayi.app.entity.ClientDetail;
import com.spring.ayi.app.mapper.IClientDetailMapper;
import com.spring.ayi.app.repository.IClientDetailRepository;
import com.spring.ayi.app.service.IClientDetailService;
import lombok.AllArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.web.util.UriComponentsBuilder;

import javax.transaction.Transactional;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.stream.Collectors;

@Service
@AllArgsConstructor
public class ClientDetailServiceImpl implements IClientDetailService {

    private IClientDetailRepository clientDetailRepository;

    private IClientDetailMapper clientDetailMapper;

    @Override
    @Transactional
    public ClientDetailResponse createClientDetail(ClientDetailRequest request) {
        ClientDetail clientDetail = clientDetailMapper.convertDtoToEntity(request);
        Client client = clientDetail.getClient();

        clientDetail.setClient(client);
        clientDetail = clientDetailRepository.save(clientDetail);

        return clientDetailMapper.convertEntityToDto(clientDetail);
    }

    @Override
    @Transactional
    public GenericListPaginationResponse<ClientDetailResponse> getAllClientDetail(String uri,
                                                                                  int pageReq,
                                                                                  Integer size,
                                                                                  UriComponentsBuilder uriBuilder) {
        GenericListPaginationResponse<ClientDetailResponse> clientDetailPagesResponse = new GenericListPaginationResponse<>();
        Pageable pageable = PageRequest.of(pageReq, size);
        Page<ClientDetail> clientDetailPage = clientDetailRepository.findAll(pageable);

        if (clientDetailPage != null && !clientDetailPage.isEmpty() && !(pageReq > clientDetailPage.getTotalPages())) {
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
        } else if (pageReq > clientDetailPage.getTotalPages()) {
            //  Crear custom exception
            throw new RuntimeException("No existe la página " + pageReq);
        } else {
            // Crear custom exception
            throw new RuntimeException("No hay registros en client detail pára mostrar.");
        }
    }

    @Override
    @Transactional
    public ClientDetailResponse getOneClientDetailById(Long idClientDetail) throws NoSuchElementException {
        ClientDetail clientDetail = this.getClientDetailById(idClientDetail);
        return clientDetailMapper.convertEntityToDto(clientDetail);
    }

    @Override
    @Transactional
    public ClientDetailResponse updateClientDetail(Long idClientDetail, ClientDetailRequest request) throws NoSuchElementException {
        ClientDetail clientDetailToUpdate = this.getClientDetailById(idClientDetail);

        clientDetailToUpdate.setPrime(request.getPrime());
        clientDetailToUpdate.setAcumulatedPoints(request.getAcumulatedPoints());

        ClientDetail clientDetailUpdated = clientDetailRepository.save(clientDetailToUpdate);

        return clientDetailMapper.convertEntityToDto(clientDetailUpdated);
    }

    @Override
    @Transactional
    public void deleteClientDetailById(Long idClientDetail) throws NoSuchElementException {
        ClientDetail clientDetail = this.getClientDetailById(idClientDetail);

        clientDetail.setSoftDelete(Boolean.TRUE);
        clientDetailRepository.save(clientDetail);
    }

    private ClientDetail getClientDetailById(Long idClientDetail) throws NoSuchElementException {
        //  Create custom exception
        return clientDetailRepository.findById(idClientDetail)
                .orElseThrow(() -> new RuntimeException("Client detail id " + idClientDetail + " not found."));
    }

    private String constructPrevPageUri(UriComponentsBuilder uriBuilder, int pageReq) {
        return uriBuilder.replaceQueryParam("page", pageReq - 1).build().encode().toUriString();
    }

    private String constructNextPageUri(UriComponentsBuilder uriBuilder, int pageReq) {
        return uriBuilder.replaceQueryParam("page", pageReq + 1).build().encode().toUriString();
    }
}
