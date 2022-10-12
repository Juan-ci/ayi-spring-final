package com.spring.ayi.app.service.impl;

import com.spring.ayi.app.dto.request.client.ClientRequest;
import com.spring.ayi.app.dto.response.client.ClientResponse;
import com.spring.ayi.app.dto.response.pagination.GenericListPaginationResponse;
import com.spring.ayi.app.entity.Address;
import com.spring.ayi.app.entity.Client;
import com.spring.ayi.app.entity.UserAccount;
import com.spring.ayi.app.exception.custom.ClientNotFoundException;
import com.spring.ayi.app.exception.custom.DocumentNumberAlreadyExistException;
import com.spring.ayi.app.exception.custom.DocumentNumberNotFoundException;
import com.spring.ayi.app.exception.custom.EmptyListException;
import com.spring.ayi.app.exception.custom.PageDoesNotExistException;
import com.spring.ayi.app.exception.custom.UserAccountNotFoundException;
import com.spring.ayi.app.mapper.IClientMapper;
import com.spring.ayi.app.repository.IClientRepository;
import com.spring.ayi.app.service.IClientService;
import com.spring.ayi.app.service.IUserAccountService;
import org.springframework.context.annotation.Lazy;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.web.util.UriComponentsBuilder;

import javax.transaction.Transactional;
import java.util.List;
import java.util.stream.Collectors;

import static com.spring.ayi.app.constants.exception.messages.ExceptionMessages.CLIENT_ID_NOT_FOUND;
import static com.spring.ayi.app.constants.exception.messages.ExceptionMessages.DOCUMENT_ALREADY_EXIST;
import static com.spring.ayi.app.constants.exception.messages.ExceptionMessages.DOCUMENT_NUMBER_NOT_FOUND;
import static com.spring.ayi.app.constants.exception.messages.ExceptionMessages.EMPTY_LIST_EXCEPTION;
import static com.spring.ayi.app.constants.exception.messages.ExceptionMessages.PAGE_DOES_NOT_EXIST;
import static java.text.MessageFormat.format;

@Service
public class ClientServiceImpl implements IClientService {

    private IClientRepository clientRepository;

    private IUserAccountService userAccountService;

    private IClientMapper clientMapper;

    private static final String LIST_TYPE_EXCEPTION = "CLIENTS";

    public ClientServiceImpl(IClientRepository clientRepository,
                             @Lazy IUserAccountService userAccountService,
                             IClientMapper clientMapper) {
        this.clientRepository = clientRepository;
        this.userAccountService = userAccountService;
        this.clientMapper = clientMapper;
    }

    @Override
    @Transactional
    public ClientResponse createClient(ClientRequest request)
            throws DocumentNumberAlreadyExistException, UserAccountNotFoundException {
        Client client = clientMapper.convertDtoToEntity(request);
        String documentNumber = client.getDocumentNumber();
        List<Address> address = client.getAddresses();
        boolean existClient = !clientRepository.existsByDocumentNumber(documentNumber);
        String mailAccount = request.getMailAccount();
        UserAccount userAccount;

        if (existClient) {
            if (address != null && address.isEmpty()) {
                Client finalClient = client;
                address.forEach(addr -> addr.setClient(finalClient));
            }

            if (mailAccount != null) {
                userAccount = userAccountService.getUserAccountByMail(mailAccount);

                if (userAccount != null) {
                    userAccount.setClient(client);
                    client.setUserAccount(userAccount);
                }
            }
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

        if (!clientPages.isEmpty() && !(pageReq > clientPages.getTotalPages() - 1)) {
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

            /*
              If page is equal to 0, then
              there is no previous page
             */
            if (pageable.getPageNumber() == 0) {
                prevPage = null;
            }

            /*
              If page is equal to the last page, then
               there is no next page
             */
            if (pageable.getPageNumber() == clientPages.getTotalPages() - 1) {
                nextPage = null;
            }

            clientPagesResponse.setPrevPage(prevPage);
            clientPagesResponse.setNextPage(nextPage);

            return clientPagesResponse;
        } else if (clientPages.getTotalElements() == 0) {
            throw new EmptyListException(format(EMPTY_LIST_EXCEPTION, LIST_TYPE_EXCEPTION));
        } else {
            throw new PageDoesNotExistException(format(PAGE_DOES_NOT_EXIST, pageReq, size));
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
    public ClientResponse updateClient(Long id, ClientRequest request)
            throws ClientNotFoundException, UserAccountNotFoundException {
        Client dataToUpdate = clientMapper.convertDtoToEntity(request);
        Client clientToUpdate = this.getClientById(id);
        String mailAccount = request.getMailAccount();
        UserAccount userAccount;

        if (mailAccount != null) {
            userAccount = userAccountService.getUserAccountByMail(mailAccount);

            if (userAccount != null) {
                userAccount.setClient(clientToUpdate);
                clientToUpdate.setUserAccount(userAccount);
            }
        }

        clientToUpdate.setDocumentNumber(dataToUpdate.getDocumentNumber());
        clientToUpdate.setName(dataToUpdate.getName());
        clientToUpdate.setLastname(dataToUpdate.getLastname());

        List<Address> newAddresses = dataToUpdate.getAddresses();
        if (newAddresses != null && !newAddresses.isEmpty()) {
            /*
              Get the old addresses
              */
            List<Address> currentAddresses = clientToUpdate.getAddresses();

            Client finalClientToUpdate = clientToUpdate;
            newAddresses.forEach(address -> {
                /*
                   Add the new addresses
                 */
                address.setClient(finalClientToUpdate);
                currentAddresses.add(address);
            });
            /*
               Set the new list of addresses
             */
            clientToUpdate.setAddresses(currentAddresses);
        }
        clientToUpdate = clientRepository.save(clientToUpdate);

        return clientMapper.convertEntityToDto(clientToUpdate);
    }

    /**
     * It is a softDelete, when it is deleted
     * all the addresses in it are deleted too
     *
     * @param idClient
     * @throws ClientNotFoundException
     */
    @Override
    @Transactional
    public void deleteClientById(Long idClient) throws ClientNotFoundException {
        Client clientToDelete = this.getClientById(idClient);
        clientToDelete.setSoftDelete(Boolean.TRUE);

        List<Address> addresses = clientToDelete.getAddresses();
        if (addresses != null) {
            addresses.forEach(address -> address.setSoftDelete(Boolean.TRUE));
        }

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
