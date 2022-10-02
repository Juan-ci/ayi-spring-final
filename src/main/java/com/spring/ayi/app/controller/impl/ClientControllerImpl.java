package com.spring.ayi.app.controller.impl;

import com.spring.ayi.app.controller.IClientController;
import com.spring.ayi.app.dto.request.ClientRequest;
import com.spring.ayi.app.dto.response.ClientResponse;
import com.spring.ayi.app.dto.response.GenericListPaginationResponse;
import com.spring.ayi.app.service.IClientService;
import io.swagger.annotations.Api;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.util.UriComponentsBuilder;

@RestController
@AllArgsConstructor
@Api(value = "Client Api", tags = "{Client Service}")
@RequestMapping(path = "/client")
public class ClientControllerImpl implements IClientController {

    private IClientService clientService;

    @Override
    public ResponseEntity<?> createClient(@RequestBody ClientRequest request) {
        ClientResponse response = clientService.createClient(request);
        return new ResponseEntity<>(response, HttpStatus.CREATED);
    }

    @Override
    public ResponseEntity<?> getAllClientsForPage(Integer page, Integer size, UriComponentsBuilder uriBuilder) {
        GenericListPaginationResponse<ClientResponse> response = clientService.getClientPage("/client/",page,size,uriBuilder);
        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    @Override
    public ResponseEntity<?> updateClient(Long idPerson, ClientRequest request) {
        ClientResponse response = clientService.updateClient(idPerson, request);
        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    @Override
    public ResponseEntity deleteClientById(Long id) {
        clientService.deleteClientById(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }
}
