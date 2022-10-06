package com.spring.ayi.app.controller.impl;

import com.spring.ayi.app.controller.IAddressController;
import com.spring.ayi.app.dto.request.AddressRequest;
import com.spring.ayi.app.dto.response.AddressResponse;
import com.spring.ayi.app.dto.response.GenericListPaginationResponse;
import com.spring.ayi.app.exception.AddressNotFoundException;
import com.spring.ayi.app.exception.DocumentNumberNotFoundException;
import com.spring.ayi.app.exception.EmptyListException;
import com.spring.ayi.app.exception.PageDoesNotExistException;
import com.spring.ayi.app.service.IAddressService;
import io.swagger.annotations.Api;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.util.UriComponentsBuilder;

@AllArgsConstructor
@RestController
@Api(value = "Address Api", tags = "{Address Service}")
@RequestMapping(value = "/address")
public class AddressControllerImpl implements IAddressController {

    private IAddressService addressService;

    @Override
    public ResponseEntity<AddressResponse> createAddress(AddressRequest request) throws DocumentNumberNotFoundException {
        AddressResponse addressResponse = addressService.createAddress(request);
        return new ResponseEntity<>(addressResponse, HttpStatus.CREATED);
    }

    @Override
    public ResponseEntity<GenericListPaginationResponse<AddressResponse>> getAllAddressForPage
            (
                    Integer page,
                    Integer size,
                    UriComponentsBuilder uriBuilder
            ) throws PageDoesNotExistException, EmptyListException {
        GenericListPaginationResponse response = addressService.getAllAddress("/address/", page, size, uriBuilder);
        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    @Override
    public ResponseEntity<AddressResponse> getAddressById(Long idAddress) throws AddressNotFoundException {
        AddressResponse response = addressService.getOneAddressById(idAddress);
        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    @Override
    public ResponseEntity<AddressResponse> updateAddress(Long idAddress, AddressRequest request)
            throws AddressNotFoundException {
        AddressResponse response = addressService.updateAddress(idAddress, request);
        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    @Override
    public ResponseEntity<Void> deleteAddressById(Long idAddress) throws AddressNotFoundException {
        addressService.deleteAddressById(idAddress);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }
}
