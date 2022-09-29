package com.spring.ayi.app.controller.impl;

import com.spring.ayi.app.controller.IClientDetailController;
import com.spring.ayi.app.dto.request.ClientDetailRequest;
import com.spring.ayi.app.dto.response.ClientDetailResponse;
import com.spring.ayi.app.service.IClientDetailService;
import io.swagger.annotations.Api;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@AllArgsConstructor
@RestController
@Api(value = "Client Detail Api", tags = "{Client Detail Service}")
@RequestMapping(value = "/client-detail")
public class ClientDetailControllerImpl implements IClientDetailController {

    private IClientDetailService clientDetailService;

    @Override
    public ResponseEntity<?> createClientDetail(ClientDetailRequest request) {
        ClientDetailResponse clientDetailResponse = clientDetailService.createClientDetail(request);
        return new ResponseEntity<>(clientDetailResponse, HttpStatus.CREATED);
    }
}
