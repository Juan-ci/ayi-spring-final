package com.spring.ayi.app.service.impl;

import com.spring.ayi.app.dto.request.ClientRequest;
import com.spring.ayi.app.dto.response.ClientResponse;
import com.spring.ayi.app.dto.response.GenericListPaginationResponse;
import com.spring.ayi.app.entity.Address;
import com.spring.ayi.app.entity.Client;
import com.spring.ayi.app.entity.ClientDetail;
import com.spring.ayi.app.exception.ClientNotFoundException;
import com.spring.ayi.app.exception.DocumentNumberAlreadyExistException;
import com.spring.ayi.app.exception.DocumentNumberNotFoundException;
import com.spring.ayi.app.exception.EmptyListException;
import com.spring.ayi.app.exception.PageDoesNotExistException;
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

import static com.spring.ayi.app.constants.ExceptionMessages.ExceptionMessages.CLIENT_ID_NOT_FOUND;
import static com.spring.ayi.app.constants.ExceptionMessages.ExceptionMessages.DOCUMENT_ALREADY_EXIST;
import static com.spring.ayi.app.constants.ExceptionMessages.ExceptionMessages.DOCUMENT_NUMBER_NOT_FOUND;
import static com.spring.ayi.app.constants.ExceptionMessages.ExceptionMessages.EMPTY_LIST_EXCEPTION;
import static com.spring.ayi.app.constants.ExceptionMessages.ExceptionMessages.PAGE_DOES_NOT_EXIST;
import static java.text.MessageFormat.format;

@Service
@AllArgsConstructor
public class ClientServiceImpl implements IClientService {

    private IClientRepository clientRepository;

    private IClientMapper clientMapper;

    private static final String LIST_TYPE_EXCEPTION = "CLIENTS";

    @Override
    @Transactional
    public ClientResponse createClient(ClientRequest request) throws DocumentNumberAlreadyExistException {
        Client client = clientMapper.convertDtoToEntity(request);
        ClientDetail clientDetail = client.getClientDetail();
        String documentNumber = client.getDocumentNumber();
        List<Address> address = client.getAddresses();

        if (!clientRepository.existsByDocumentNumber(documentNumber)) {
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
        } else {
            throw new DocumentNumberAlreadyExistException(format(DOCUMENT_ALREADY_EXIST, documentNumber));
        }
    }

    @Override
    @Transactional
    public GenericListPaginationResponse<ClientResponse> getClientPage(String uri,
                                                                       int pageReq,
                                                                       Integer size,
                                                                       UriComponentsBuilder uriBuilder)
            throws PageDoesNotExistException, EmptyListException {
        GenericListPaginationResponse<ClientResponse> clientPagesResponse = new GenericListPaginationResponse<>();
        Pageable pageable = PageRequest.of(pageReq, size);
        Page<Client> clientPages = clientRepository.findAll(pageable);

        if (clientPages != null && !clientPages.isEmpty() && !(pageReq > clientPages.getTotalPages() - 1)) {
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
            if (pageable.getPageNumber() == 0) {
                prevPage = null;
            }

            /**
             * If page is equal to the last page, then
             *  there is no next page
             */
            if (pageable.getPageNumber() == clientPages.getTotalPages() - 1) {
                nextPage = null;
            }

            clientPagesResponse.setPrevPage(prevPage);
            clientPagesResponse.setNextPage(nextPage);

            return clientPagesResponse;
        } else if (pageReq > clientPages.getTotalPages() - 1) {
            throw new PageDoesNotExistException(format(PAGE_DOES_NOT_EXIST, pageReq, size));
        } else {
            throw new EmptyListException(format(EMPTY_LIST_EXCEPTION, LIST_TYPE_EXCEPTION));
        }
    }

    @Override
    @Transactional
    public ClientResponse getOneClientById(Long idClient) throws ClientNotFoundException {
        Client client = this.getClientById(idClient);

        return clientMapper.convertEntityToDto(client);
    }

    @Override
    @Transactional
    public ClientResponse updateClient(Long id, ClientRequest request) throws ClientNotFoundException {
        /**
         * No se agregan facturas ya que se actualiza el cliente cuando se crean nuevas facturas
         */
        Client dataToUpdate = clientMapper.convertDtoToEntity(request);
        Client clientToUpdate = this.getClientById(id);

        if (request.getClientDetail() != null) {
            /**
             * Updated Client detail
             */
            ClientDetail clientDetailUpdated = dataToUpdate.getClientDetail();
            clientDetailUpdated.setIdClientDetail(clientToUpdate.getClientDetail().getIdClientDetail());
            clientDetailUpdated.setClient(clientToUpdate);

            clientToUpdate.setClientDetail(clientDetailUpdated);
        }

        clientToUpdate.setClientDetail(dataToUpdate.getClientDetail());
        clientToUpdate.setDocumentNumber(dataToUpdate.getDocumentNumber());
        clientToUpdate.setName(dataToUpdate.getName());
        clientToUpdate.setLastname(dataToUpdate.getLastname());

        List<Address> newAddresses = dataToUpdate.getAddresses();
        if (newAddresses != null && !newAddresses.isEmpty()) {
            /**
             * Get the old addresses
              */
            List<Address> currentAddresses = clientToUpdate.getAddresses();

            Client finalClientToUpdate = clientToUpdate;
            newAddresses.forEach(address -> {
                /**
                 *  Add the new addresses
                 */
                address.setClient(finalClientToUpdate);
                currentAddresses.add(address);
            });
            /**
             *  Set the new list of addresses
             */
            clientToUpdate.setAddresses(currentAddresses);
        }
        clientToUpdate = clientRepository.save(clientToUpdate);

        return clientMapper.convertEntityToDto(clientToUpdate);
    }

    @Override
    @Transactional
    public void deleteClientById(Long idClient) throws ClientNotFoundException {
        Client clientToDelete = this.getClientById(idClient);

        clientToDelete.setSoftDelete(Boolean.TRUE);

        clientRepository.save(clientToDelete);
    }

    @Override
    @Transactional
    public Client getClientByDocumentNumber(String documentNumber) throws DocumentNumberNotFoundException {
        return clientRepository
                .findByDocumentNumber(documentNumber)
                .orElseThrow(
                        () -> new DocumentNumberNotFoundException(format(DOCUMENT_NUMBER_NOT_FOUND, documentNumber))
                );
    }

    private Client getClientById(Long idClient) throws ClientNotFoundException {
        return clientRepository
                .findById(idClient)
                .orElseThrow(
                        () -> new ClientNotFoundException(format(CLIENT_ID_NOT_FOUND, idClient))
                );
    }

    private String constructPrevPageUri(UriComponentsBuilder uriBuilder, int pageReq) {
        return uriBuilder.replaceQueryParam("page", pageReq - 1).build().encode().toUriString();
    }

    private String constructNextPageUri(UriComponentsBuilder uriBuilder, int pageReq) {
        return uriBuilder.replaceQueryParam("page", pageReq + 1).build().encode().toUriString();
    }
}
