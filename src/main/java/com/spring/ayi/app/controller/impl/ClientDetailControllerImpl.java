package com.spring.ayi.app.controller.impl;

import com.spring.ayi.app.controller.IClientDetailController;
import com.spring.ayi.app.dto.request.ClientDetailRequest;
import com.spring.ayi.app.dto.response.ClientDetailResponse;
import com.spring.ayi.app.dto.response.GenericListPaginationResponse;
import com.spring.ayi.app.service.IClientDetailService;
import io.swagger.annotations.Api;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.util.UriComponentsBuilder;

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

    @Override
    public ResponseEntity<?> getAllClientDetailsForPage(Integer page, Integer size, UriComponentsBuilder uriBuilder) {
        GenericListPaginationResponse<ClientDetailResponse> response = clientDetailService.getAllClientDetail("/client-detail/", page, size, uriBuilder);
        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    @Override
    public ResponseEntity<?> getClientDetailById(Long idClientDetail) {
        ClientDetailResponse response = clientDetailService.getOneClientDetailById(idClientDetail);
        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    @Override
    public ResponseEntity<?> updateClientDetail(Long idClientDetail, ClientDetailRequest request) {
        ClientDetailResponse response = clientDetailService.updateClientDetail(idClientDetail, request);
        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    @Override
    public ResponseEntity deleteClientDetailById(Long idClientDetail) {
        clientDetailService.deleteClientDetailById(idClientDetail);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }
}
