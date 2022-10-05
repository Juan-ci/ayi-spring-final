package com.spring.ayi.app.controller.impl;

import com.spring.ayi.app.controller.IAddressController;
import com.spring.ayi.app.dto.request.AddressRequest;
import com.spring.ayi.app.dto.response.AddressResponse;
import com.spring.ayi.app.dto.response.GenericListPaginationResponse;
import com.spring.ayi.app.service.IAddressService;
import io.swagger.annotations.Api;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.util.UriComponentsBuilder;

import java.util.NoSuchElementException;

@AllArgsConstructor
@RestController
@Api(value = "Address Api", tags = "{Address Service}")
@RequestMapping(value = "/address")
public class AddressController implements IAddressController {

    private IAddressService addressService;

    @Override
    public ResponseEntity<?> createAddress(AddressRequest request) {
        AddressResponse addressResponse = addressService.createAddress(request);
        return new ResponseEntity<>(addressResponse, HttpStatus.CREATED);
    }

    @Override
    public ResponseEntity<?> getAllAddressForPage(Integer page, Integer size, UriComponentsBuilder uriBuilder) {
        GenericListPaginationResponse response = addressService.getAllAddress("/address/", page, size, uriBuilder);
        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    @Override
    public ResponseEntity<?> getAddressById(Long idAddress) throws NoSuchElementException {
        AddressResponse response = addressService.getOneAddressById(idAddress);
        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    @Override
    public ResponseEntity<?> updateAddress(Long idAddress, AddressRequest request) throws NoSuchElementException {
        AddressResponse response = addressService.updateAddress(idAddress, request);
        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    @Override
    public ResponseEntity deleteAddreessById(Long idAddress) throws NoSuchElementException {
        addressService.deleteAddressById(idAddress);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }
}
