package com.spring.ayi.app.service;

import com.spring.ayi.app.dto.request.userAccount.UserAccountRequest;
import com.spring.ayi.app.dto.response.userAccount.UserAccountResponse;
import com.spring.ayi.app.dto.response.pagination.GenericListPaginationResponse;
import com.spring.ayi.app.entity.UserAccount;
import com.spring.ayi.app.exception.custom.DocumentNumberAlreadyExistException;
import com.spring.ayi.app.exception.custom.MailAlreadyExistException;
import com.spring.ayi.app.exception.custom.UserAccountNotFoundException;
import com.spring.ayi.app.exception.custom.DocumentNumberNotFoundException;
import com.spring.ayi.app.exception.custom.EmptyListException;
import com.spring.ayi.app.exception.custom.PageDoesNotExistException;
import org.springframework.stereotype.Service;
import org.springframework.web.util.UriComponentsBuilder;

import javax.transaction.Transactional;

@Service
public interface IUserAccountService {

    @Transactional
    UserAccountResponse createUserAccount(UserAccountRequest request)
            throws DocumentNumberAlreadyExistException, MailAlreadyExistException;

    @Transactional
    GenericListPaginationResponse<UserAccountResponse> getAllUserAccount
            (
                    String uri,
                    int pageReq,
                    Integer size,
                    UriComponentsBuilder uriBuilder
            ) throws PageDoesNotExistException, EmptyListException;

    @Transactional
    UserAccountResponse getOneUserAccountById(Long idUserAccount) throws UserAccountNotFoundException;

    @Transactional
    UserAccountResponse updateUserAccount(Long idUserAccount, UserAccountRequest request)
            throws UserAccountNotFoundException, MailAlreadyExistException;

    @Transactional
    void deleteUserAccountById(Long idUserAccount) throws UserAccountNotFoundException;

    UserAccount getUserAccountByMail(String mailAccount) throws UserAccountNotFoundException;
}
