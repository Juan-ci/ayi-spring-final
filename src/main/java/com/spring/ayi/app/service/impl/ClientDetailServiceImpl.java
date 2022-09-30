package com.spring.ayi.app.service.impl;

import com.spring.ayi.app.dto.request.ClientDetailRequest;
import com.spring.ayi.app.dto.response.ClientDetailResponse;
import com.spring.ayi.app.entity.Client;
import com.spring.ayi.app.entity.ClientDetail;
import com.spring.ayi.app.mapper.IClientDetailMapper;
import com.spring.ayi.app.repository.IClientDetailRepository;
import com.spring.ayi.app.service.IClientDetailService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;

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
}
