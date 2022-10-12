package com.spring.ayi.app.mapper;

import com.spring.ayi.app.dto.request.userAccount.UserAccountRequest;
import com.spring.ayi.app.dto.response.userAccount.UserAccountResponse;
import com.spring.ayi.app.entity.UserAccount;

public interface IUserAccountMapper {

    UserAccount convertDtoToEntity(UserAccountRequest request);

    UserAccountResponse convertEntityToDto(UserAccount entity);
}
