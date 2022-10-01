package com.spring.ayi.app.service.impl;

import com.spring.ayi.app.dto.request.ClientRequest;
import com.spring.ayi.app.dto.response.ClientResponse;
import com.spring.ayi.app.dto.response.GenericListPaginationResponse;
import com.spring.ayi.app.entity.Address;
import com.spring.ayi.app.entity.Client;
import com.spring.ayi.app.entity.ClientDetail;
import com.spring.ayi.app.mapper.IClientMapper;
import com.spring.ayi.app.repository.IClientRepository;
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

@Service
@AllArgsConstructor
public class ClientServiceImpl implements IClientService {

    private IClientRepository clientRepository;

    private IClientMapper clientMapper;

    @Override
    @Transactional
    public ClientResponse createClient(ClientRequest request) {
        Client client = clientMapper.convertDtoToEntity(request);
        ClientDetail clientDetail = client.getClientDetail();
        List<Address> address = client.getAddresses();

        if (address != null && address.size() > 0) {
            Client finalClient = client;
            address.forEach(addr -> {
                addr.setClient(finalClient);
            });
        }

        clientDetail.setClient(client);
        client.setAddresses(address);
        client = clientRepository.save(client);

        return clientMapper.convertEntityToDto(client);
    }

    @Override
    @Transactional
    public GenericListPaginationResponse<ClientResponse> getClientPage(String uri,
                                                                       int pageReq,
                                                                       Integer size,
                                                                       UriComponentsBuilder uriBuilder) {
        GenericListPaginationResponse<ClientResponse> clientPagesResponse = new GenericListPaginationResponse<>();
        Pageable pageable = PageRequest.of(pageReq, size);
        Page<Client> clientPages = clientRepository.findAll(pageable);

        if(clientPages != null && !clientPages.isEmpty() && !(pageReq > clientPages.getTotalPages())) {
            List<ClientResponse> clientContent = clientPages
                    .map(client -> clientMapper.convertEntityToDto(client))
                    .stream()
                    .collect(Collectors.toList());

            clientPagesResponse.setPages(clientContent);
            clientPagesResponse.setSize(clientPages.getSize());
            clientPagesResponse.setCurrentPage(clientPages.getNumber() + 1);
            clientPagesResponse.setTotalPages(clientPages.getTotalPages());
            clientPagesResponse.setTotalElements((int) clientPages.getTotalElements());

            uriBuilder.path(uri);
            String nextPage = constructNextPageUri(uriBuilder, pageReq);
            String prevPage = constructPrevPageUri(uriBuilder, pageReq);

            /**
             * If page is equal to 0, then
             * there is no previous page
             */
            if (pageable.getPageNumber() == 0 ) {
                prevPage = null;
            }

            /**
             * If page is equal to the last page, then
             *  there is no next page
             */
            if (pageable.getPageNumber() == clientPages.getTotalPages() -1 ) {
                nextPage = null;
            }

            clientPagesResponse.setPrevPage(prevPage);
            clientPagesResponse.setNextPage(nextPage);

            return clientPagesResponse;
        } else if (pageReq > clientPages.getTotalPages()) {
            throw new RuntimeException("No existe la pagina " + pageReq);
        } else {
            //  AGREGAR CUSTOM EXCEPTION
            throw new RuntimeException("No hay clientes para mostrar.");
        }
    }

    private String constructPrevPageUri(UriComponentsBuilder uriBuilder, int pageReq) {
        return uriBuilder.replaceQueryParam("page", pageReq - 1).build().encode().toUriString();
    }

    private String constructNextPageUri(UriComponentsBuilder uriBuilder, int pageReq) {
        return uriBuilder.replaceQueryParam("page", pageReq + 1).build().encode().toUriString();
    }
}
