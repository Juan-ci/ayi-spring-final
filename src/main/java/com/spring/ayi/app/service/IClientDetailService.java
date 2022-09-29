package com.spring.ayi.app.service;

import com.spring.ayi.app.dto.request.ClientDetailRequest;
import com.spring.ayi.app.dto.response.ClientDetailResponse;
import org.springframework.stereotype.Service;

@Service
public interface IClientDetailService {
    ClientDetailResponse createClientDetail(ClientDetailRequest request);
}
