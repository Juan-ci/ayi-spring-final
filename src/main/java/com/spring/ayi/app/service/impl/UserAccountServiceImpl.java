package com.spring.ayi.app.service.impl;

import com.spring.ayi.app.constants.exception.messages.ExceptionMessages;
import com.spring.ayi.app.dto.request.userAccount.UserAccountRequest;
import com.spring.ayi.app.dto.response.userAccount.UserAccountResponse;
import com.spring.ayi.app.dto.response.pagination.GenericListPaginationResponse;
import com.spring.ayi.app.entity.Address;
import com.spring.ayi.app.entity.Client;
import com.spring.ayi.app.entity.UserAccount;
import com.spring.ayi.app.exception.custom.DocumentNumberAlreadyExistException;
import com.spring.ayi.app.exception.custom.MailAlreadyExistException;
import com.spring.ayi.app.exception.custom.UserAccountNotFoundException;
import com.spring.ayi.app.exception.custom.DocumentNumberNotFoundException;
import com.spring.ayi.app.exception.custom.EmptyListException;
import com.spring.ayi.app.exception.custom.PageDoesNotExistException;
import com.spring.ayi.app.mapper.IUserAccountMapper;
import com.spring.ayi.app.repository.IUserAccountRepository;
import com.spring.ayi.app.service.IUserAccountService;
import com.spring.ayi.app.service.IClientService;
import org.springframework.context.annotation.Lazy;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.web.util.UriComponentsBuilder;

import javax.transaction.Transactional;
import java.util.List;
import java.util.stream.Collectors;

import static com.spring.ayi.app.constants.exception.messages.ExceptionMessages.EMPTY_LIST_EXCEPTION;
import static com.spring.ayi.app.constants.exception.messages.ExceptionMessages.PAGE_DOES_NOT_EXIST;
import static com.spring.ayi.app.constants.exception.messages.ExceptionMessages.USER_ACCOUNT_ID_NOT_FOUND;
import static com.spring.ayi.app.constants.exception.messages.ExceptionMessages.USER_ACCOUNT_MAIL_NOT_FOUND;
import static java.text.MessageFormat.format;

@Service
public class UserAccountServiceImpl implements IUserAccountService {

    private IUserAccountRepository userAccountRepository;

    private IUserAccountMapper userAccountMapper;

    private IClientService clientService;

    private static final String LIST_TYPE_EXCEPTION = "USERS ACCOUNTS";

    public UserAccountServiceImpl(IUserAccountRepository userAccountRepository,
                                  IUserAccountMapper userAccountMapper,
                                  @Lazy IClientService clientService) {
        this.userAccountRepository = userAccountRepository;
        this.userAccountMapper = userAccountMapper;
        this.clientService = clientService;
    }

    /**
     * It is allowed to create a user account
     * without a client. If you want to set the
     * user account to an existing client, you
     * have to create the user account and then
     * update the client
     * @param request
     * @return
     * @throws DocumentNumberNotFoundException
     * @throws MailAlreadyExistException
     */
    @Override
    @Transactional
    public UserAccountResponse createUserAccount(UserAccountRequest request)
            throws DocumentNumberAlreadyExistException, MailAlreadyExistException {
        UserAccount userAccount = userAccountMapper.convertDtoToEntity(request);
        Client client = userAccount.getClient();
        String mail = userAccount.getMail();
        boolean mailExist = userAccountRepository.existsByMail(mail);

        if (!mailExist) {
            if (client != null && !clientService.existsClientByDocumentNumber(client.getDocumentNumber())) {
                client.setUserAccount(userAccount);

                List<Address> addresses = client.getAddresses();
                if (addresses != null) {
                    addresses.forEach(address -> {
                        address.setClient(client);
                    });

                    client.setAddresses(addresses);
                }
            }

            //Agregar hasheo de password
            UserAccount userAccountCreated = userAccountRepository.save(userAccount);

            return userAccountMapper.convertEntityToDto(userAccountCreated);
        } else {
            throw new MailAlreadyExistException(format(ExceptionMessages.MAIL_ALREADY_EXIST, mail));
        }
    }

    @Override
    @Transactional
    public GenericListPaginationResponse<UserAccountResponse> getAllUserAccount
            (
                    String uri,
                    int pageReq,
                    Integer size,
                    UriComponentsBuilder uriBuilder
            ) throws PageDoesNotExistException, EmptyListException {
        GenericListPaginationResponse<UserAccountResponse> userAccountPagesResponse = new GenericListPaginationResponse<>();
        Pageable pageable = PageRequest.of(pageReq, size);
        Page<UserAccount> userAccountPage = userAccountRepository.findAll(pageable);

        if (!userAccountPage.isEmpty() && !(pageReq > userAccountPage.getTotalPages() - 1)) {
            List<UserAccountResponse> userAccountContent = userAccountPage.map(
                            userAccount -> userAccountMapper.convertEntityToDto(userAccount)
                    ).stream()
                    .collect(Collectors.toList());

            userAccountPagesResponse.setPages(userAccountContent);
            userAccountPagesResponse.setSize(userAccountPage.getSize());
            userAccountPagesResponse.setCurrentPage(userAccountPage.getNumber() + 1);
            userAccountPagesResponse.setTotalPages(userAccountPage.getTotalPages());
            userAccountPagesResponse.setTotalElements((int) userAccountPage.getTotalElements());

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
            if (pageable.getPageNumber() == userAccountPage.getTotalPages() - 1) {
                nextPage = null;
            }

            userAccountPagesResponse.setPrevPage(prevPage);
            userAccountPagesResponse.setNextPage(nextPage);

            return userAccountPagesResponse;
        } else if (userAccountPage.getTotalElements() == 0) {
            throw new EmptyListException(format(EMPTY_LIST_EXCEPTION, LIST_TYPE_EXCEPTION));
        } else {
            throw new PageDoesNotExistException(format(PAGE_DOES_NOT_EXIST, pageReq, size));
        }
    }

    @Override
    @Transactional
    public UserAccountResponse getOneUserAccountById(Long idUserAccount) throws UserAccountNotFoundException {
        UserAccount userAccount = this.getUserAccountById(idUserAccount);
        return userAccountMapper.convertEntityToDto(userAccount);
    }

    @Override
    @Transactional
    public UserAccountResponse updateUserAccount(Long idUserAccount, UserAccountRequest request)
            throws UserAccountNotFoundException, MailAlreadyExistException {
        UserAccount userAccountToUpdate = this.getUserAccountById(idUserAccount);
        //si viene mail, chequear que no se repita en BD

        UserAccount userAccountUpdated = userAccountRepository.save(userAccountToUpdate);

        return userAccountMapper.convertEntityToDto(userAccountUpdated);
    }

    /**
     * When a user is deleted,
     * all the entities related
     * will be deleted
     * @param idUserAccount
     * @throws UserAccountNotFoundException
     */
    @Override
    @Transactional
    public void deleteUserAccountById(Long idUserAccount) throws UserAccountNotFoundException {
        UserAccount userAccount = this.getUserAccountById(idUserAccount);

        userAccount.setSoftDelete(Boolean.TRUE);
        userAccount.getClient().setSoftDelete(Boolean.TRUE);

        List<Address> addresses = userAccount.getClient().getAddresses();
        if (!addresses.isEmpty()) {
            addresses.forEach(address -> address.setSoftDelete(Boolean.TRUE));
        }

        userAccountRepository.save(userAccount);
    }

    @Override
    public UserAccount getUserAccountByMail(String mailAccount) throws UserAccountNotFoundException {
        return userAccountRepository
                .findByMail(mailAccount)
                .orElseThrow(
                        () -> new UserAccountNotFoundException(format(USER_ACCOUNT_MAIL_NOT_FOUND, mailAccount))
                );
    }

    private UserAccount getUserAccountById(Long idUserAccount) throws UserAccountNotFoundException {
        return userAccountRepository
                .findById(idUserAccount)
                .orElseThrow(
                        () -> new UserAccountNotFoundException(format(USER_ACCOUNT_ID_NOT_FOUND, idUserAccount))
                );
    }

    private String constructPrevPageUri(UriComponentsBuilder uriBuilder, int pageReq) {
        return uriBuilder.replaceQueryParam("page", pageReq - 1).build().encode().toUriString();
    }

    private String constructNextPageUri(UriComponentsBuilder uriBuilder, int pageReq) {
        return uriBuilder.replaceQueryParam("page", pageReq + 1).build().encode().toUriString();
    }
}
