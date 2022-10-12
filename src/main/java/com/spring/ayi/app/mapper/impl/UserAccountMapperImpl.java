package com.spring.ayi.app.mapper.impl;

import com.spring.ayi.app.dto.request.userAccount.UserAccountRequest;
import com.spring.ayi.app.dto.response.userAccount.UserAccountResponse;
import com.spring.ayi.app.entity.UserAccount;
import com.spring.ayi.app.mapper.IUserAccountMapper;
import lombok.AllArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Component;

@Component
@AllArgsConstructor
public class UserAccountMapperImpl implements IUserAccountMapper {

    private final ModelMapper modelMapper;

    @Override
    public UserAccount convertDtoToEntity(UserAccountRequest request) {
        return modelMapper.map(request, UserAccount.class);
    }

    @Override
    public UserAccountResponse convertEntityToDto(UserAccount entity) {
        return modelMapper.map(entity, UserAccountResponse.class);
    }
}
