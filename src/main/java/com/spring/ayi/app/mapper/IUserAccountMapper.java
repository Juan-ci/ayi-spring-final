package com.spring.ayi.app.mapper;

import com.spring.ayi.app.dto.request.userAccount.UserAccountRequest;
import com.spring.ayi.app.dto.response.userAccount.UserAccountResponse;

public interface IUserAccountMapper {
    com.spring.ayi.app.entity.UserAccount convertDtoToEntity(UserAccountRequest request);

    UserAccountResponse convertEntityToDto(com.spring.ayi.app.entity.UserAccount entity);
}
